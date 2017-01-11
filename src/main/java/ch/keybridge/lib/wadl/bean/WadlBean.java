/* 
 * Copyright (C) 2017 Key Bridge LLC
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ch.keybridge.lib.wadl.bean;

import ch.keybridge.lib.xml.JaxbUtility;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.JAXBException;
import net.java.dev.wadl.*;

/**
 * JSF backing bean supporting WADL rendering and auto-documentation.
 * <p>
 * Configuration: This RequestScoped Named bean requires an entry in the Faces
 * resource bundle {@code link.properties} to define the fully qualified WADL
 * location. In the faces-config.xml file you should have:
 * <pre>
 * &lt;resource-bundle&gt;
 *   &lt;base-name&gt;faces.link&lt;/base-name&gt;
 *   &lt;var&gt;link&lt;/var&gt;
 * &lt;/resource-bundle&gt;
 * </pre> and in the link.properties file you should have:
 * <pre>
 * wadl=[uri]/application.wadl
 * </pre>
 *
 * @author Key Bridge LLC
 * @since 01/10/17 created as an alternative to Swagger.io
 */
@Named(value = "wadlBean")
@RequestScoped
public class WadlBean {

  private static final Logger LOGGER = Logger.getLogger(WadlBean.class.getName());

  /**
   * The unmarshaled WADL application.
   */
  private Application application;

  /**
   * A map of WADL properties and their corresponding description.
   * <p>
   * Developer note: LABELS will be eventually be replaced when the WADL
   * generator is enriched to self-document using {@code Doc} instances.
   * <p>
   */
  private Map<String, String> labels;

  /**
   * Creates a new instance of WadlBean
   *
   * @throws java.lang.Exception if the link.properties file does not contain a
   *                             WADL entry.
   */
  public WadlBean() throws Exception {
    initializeWADL();
    initializeLabels();
  }

  /**
   * Internal method called when this class is constructed. This reads and
   * parses the WADL file.
   *
   * @throws java.lang.Exception if the {@code link.properties} file does not
   *                             contain a WADL entry.
   */
  private void initializeWADL() throws Exception {
    /**
     * Download and parse the WADL file.
     */
    try {
      /**
       * Read the WADL URI from the FacesContext. The WADL URI should be
       * configured in the link.properties file as a 'wadl=' entry.
       */
      FacesContext context = FacesContext.getCurrentInstance();
      String uri = context.getApplication().evaluateExpressionGet(context, "#{link.wadl}", String.class);
      if (uri == null || uri.isEmpty()) {
        LOGGER.severe("'wadl=' is not set in the link.properties file.");
        throw new Exception("'wadl=' is not set in the link.properties file.");
      }
      String wadlFile;
      URL url = new URL(uri);
      try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
        StringBuilder sb = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
          sb.append(inputLine);
        }
        wadlFile = sb.toString();
      }
      /**
       * Unmarshal the application from the WADL file.
       */
      this.application = JaxbUtility.unmarshal(wadlFile, Application.class);
      /**
       * Call PostLoad to set the inter-object parent/child relationships.
       */
      this.application.postLoad();
    } catch (MalformedURLException ex) {
      Logger.getLogger(WadlBean.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException | JAXBException ex) {
      Logger.getLogger(WadlBean.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Internal method called when this class is constructed. This reads and
   * parses the {@code labels.xml} properties from the META-INF/resources
   * directory.
   * <p>
   * If the {@code labels.xml} properties file is not found then a warning is
   * issued and an empty HashMap is set.
   * <p>
   * Developer note: LABELS will be eventually be replaced when the WADL
   * generator is enriched to self-document using {@code Doc} instances.
   */
  private void initializeLabels() {
    /**
     * Read the labelProperties.xml file, which should be located in the
     * META-INF/resources directory.
     */
    labels = new HashMap<>();

    try {
      URL url = getClass().getClassLoader().getResource("META-INF/resources/labels.xml");
      if (url == null) {
        LOGGER.warning("labels.xml file not found in the META-INF/resources directory.");
        return;
      }
      /**
       * Read the properties file.
       */
      Properties properties = new Properties();
      properties.loadFromXML(url.openStream());
      /**
       * Push the properties into a HashMap, which is easier to access from JSF.
       */
      properties.stringPropertyNames().stream().forEach(name -> {
        labels.put(name, properties.getProperty(name));
      });
    } catch (Exception exception) {
      LOGGER.severe("ERROR reading labels.xml file in the META-INF/resources directory.");
      LOGGER.log(Level.SEVERE, null, exception);
    }
  }

  /**
   * Get the WADL top-level Application.
   *
   * @return the WADL top-level Application
   */
  public Application getApplication() {
    return application;
  }

  /**
   * Determine if the application is set. This should always return TRUE.
   *
   * @return TRUE if the application is set, otherwise false.
   */
  public boolean isSetApplication() {
    return application != null;
  }

  /**
   * Get the WADL labels.
   *
   * @return a non-null HashMap
   */
  public Map<String, String> getLabels() {
    return labels;
  }

  /**
   * Helper method to log a warning if a requested name is not found. This helps
   * when developing a WADL labels file.
   *
   * @param name the requested name
   * @return the corresponding label
   */
  public String findLabel(String name) {
    if (labels.containsKey(name)) {
      return labels.get(name);
    } else {
      LOGGER.log(Level.WARNING, "No label for WADL parameter {0}", name);
      return "";
    }
  }

  /**
   * Find a resources entry matching the provided URI base.
   * <p>
   * This is commonly used to find a (top level) Resources object to build a
   * menu of (second level) Resource entries under a common base path.
   *
   * @param base the URI base
   * @return the matched Resources instance
   */
  public Resources findResources(String base) {
    return application.findResources(base);
  }

  /**
   * Find a specific resource matching the indicated path.
   * <p>
   * This is commonly used to find a (second level) Resource entry when
   * displaying a page detail.
   *
   * @param path the resource path.
   * @return the matching Resource.
   */
  public Resource findResource(String path) {
    return application.findResource(path);
  }

  /**
   * Find all methods identified in the {@code Application} instance belonging
   * to the indicated Resource, which is identified by its Path.
   * <p>
   * This is commonly used to find a list of (lowest level) Method entries when
   * displaying a page detail.
   *
   * @param path the Resource path
   * @return a non-null ArrayList.
   */
  public List<Method> findMethods(String path) {
    Resource resource = application.findResource(path);
    /**
     * Initialize and recursively populate the methods array.
     */
    ArrayList methods = new ArrayList();
    methods.addAll(findMethodsRecursive(resource));
    /**
     * Sort the array in alphabetical order.
     */
    Collections.sort(methods);
    return methods;
  }

  /**
   * Recursively a {@code Resource} instance to identify and extract all
   * methods.
   *
   * @param resource a {@code Resource} instance
   * @return a list of all methods in the Resource tree
   */
  private List<Method> findMethodsRecursive(Resource resource) {
    List<Method> tempMethods = new ArrayList<>(resource.getMethods());
    resource.getResources().stream().forEach((res) -> {
      tempMethods.addAll(findMethodsRecursive(res));
    });
    return tempMethods;
  }

  /**
   * Build a map of the representation element names and their supported media
   * types.
   * <p>
   * Note that use of representation elements is confined to HTTP methods that
   * accept an entity body in the request (e.g., PUT or POST). Sibling
   * representation elements represent logically equivalent alternatives, e.g.,
   * a particular resource might support multiple XML grammars for a particular
   * request.
   *
   * @param method the method
   * @return a non-null MultivaluedMap of element names and supported media
   *         types
   */
  public MultivaluedMap<String, Representation> findMethodRequestElements(Method method) {
    MultivaluedMap<String, Representation> elementMediaTypes = new MultivaluedHashMap<>();
    if (HTTPMethods.PUT.equals(method.getName()) || HTTPMethods.POST.equals(method.getName())) {
      method.getRequest().getRepresentation().stream().forEach((representation) -> {
        elementMediaTypes.add(representation.getElement().getLocalPart(), representation);
      });
    }
    return elementMediaTypes;
  }

  /**
   * Build a map of the representation element names and their supported media
   * types.
   *
   * @param response one method response
   * @return a non-null MultivaluedMap of element names and supported media
   *         types
   */
  public MultivaluedMap<String, Representation> findMethodResponseElements(Response response) {
    MultivaluedMap<String, Representation> elementMediaTypes = new MultivaluedHashMap<>();
    response.getRepresentation().stream().forEach((representation) -> {
      elementMediaTypes.add(representation.getElement() != null
                            ? representation.getElement().getLocalPart()
                            : "Response",
                            representation);
    });
    return elementMediaTypes;
  }

  /**
   * Build a list of all parameters relevant to a method. This captures the
   * parent and immediate parameters.
   *
   * @param method the method
   * @return a non-null ArrayList
   */
  public List<Param> findMethodParameters(Method method) {
    List<Param> parameterList = new ArrayList<>(method.getParent().getParam());
    if (method.isSetRequest()) {
      parameterList.addAll(method.getRequest().getParam());
    }
    return parameterList;
  }

  /**
   * Determine if the method is either a PUT or POST type.
   *
   * @param method the method
   * @return TRUE if the method name is either PUT or POST, otherwise FALSE
   */
  public boolean isPutOrPost(Method method) {
    return HTTPMethods.PUT.equals(method.getName()) || HTTPMethods.POST.equals(method.getName());
  }

  /**
   * Get the label class type based upon the method name.
   *
   * @param methodName the method name
   * @return the label class
   */
  public String buildCSSType(String methodName) {
    switch (methodName) {
      case "GET":
      case MediaType.APPLICATION_JSON:
        return "info";
      case "POST":
      case MediaType.APPLICATION_XML:
        return "success";
      case "PUT":
        return "warning";
      case "HEAD":
        return "warning";
      case "DELETE":
        return "danger";
      default:
        return "default";
    }
  }

  /**
   * Wrap variables in a URI pattern in an html span and assign the
   * 'text-primary' class. Output from this method should not be escaped.
   *
   * @param uriPattern the URI pattern
   * @return the HTML-coded URI pattern.
   */
  public String buildFormattedURI(String uriPattern) {
    return uriPattern.replaceAll("\\{(\\w+)\\}", "<span class=\"text-primary\">{$1}</span>");
  }

  /**
   * Insert paragraph markers into the description text. This method replaces
   * all large whitespace blocks with a paragraph closing and opening HTML tag.
   * <p>
   * This method should only be used inside a HTML paragraph!
   *
   * @param description the description text
   * @return the description text with paragraph tags inserted
   */
  public String formatDescription(String description) {
    return description.replaceAll("\\s{10,}", "</p>\n<p>");
  }

}
