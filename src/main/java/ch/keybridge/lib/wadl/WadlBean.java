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
package ch.keybridge.lib.wadl;

import ch.keybridge.lib.xml.JaxbUtility;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.JAXBException;
import net.java.dev.wadl.*;

/**
 * JSF backing bean supporting WADL rendering and auto-documentation.
 * <p>
 * To use: first initialize this bean on the base by EITHER uncommenting the
 * postConstruct method and calling the page with a {@code wadl} query parameter
 * OR initializing this bean by calling the {@code onSetWadl(String wadl)}
 * method on page load.
 *
 *
 * @author Key Bridge LLC
 * @since v0.3.0 created 01/10/17 as an alternative to Swagger.io
 */
@Named(value = "wadlBean")
@RequestScoped
public class WadlBean implements LabelProvider {

  private static final Logger LOGGER = Logger.getLogger(WadlBean.class.getName());

  /**
   * The current WADL URL.
   */
  private String wadl;

  /**
   * The unmarshaled WADL application.
   */
  private Application application;

  /**
   * Creates a new instance of WadlBean
   */
  public WadlBean() {
  }

  /**
   * Load read the WADL and marshal the application.
   */
//  @PostConstruct
//  protected void postConstruct() {
//    this.wadl = FacesUtil.getRequestParameter("wadl");
//    if (wadl != null) {
//      try {
//        downloadWADL(wadl);
//      } catch (Exception exception) {
//        LOGGER.severe(exception.getMessage());
//      }
//    }
//  }
  /**
   * Get the WADL url.
   *
   * @return the url
   */
  public String getWadl() {
    return wadl;
  }

  public void setWadl(String wadl) {
    this.wadl = wadl;
  }

  /**
   * AJAX method typically called on page load to initialize this WADL bean and
   * update the page content.
   *
   * @param wadlUrl the fully qualified WADL URL
   */
  public void onSetWadl(String wadlUrl) {
    try {
      downloadWADL(wadlUrl);
    } catch (Exception exception) {
      LOGGER.severe(exception.getMessage());
    }
  }

  /**
   * Internal method called when this class is constructed. This reads and
   * parses the WADL file.
   *
   * @throws java.lang.Exception if the {@code link.properties} file does not
   *                             contain a WADL entry.
   */
  private void downloadWADL(String wadl) throws Exception {
    /**
     * Set the WADL.
     */
    this.wadl = wadl;
    /**
     * Download and parse the WADL file.
     */
    try {
      /**
       * Read the WADL URI from the FacesContext. The WADL URI should be
       * configured in the link.properties file as a 'wadl=' entry.
       */
//      FacesContext context = FacesContext.getCurrentInstance();
//      String uri = context.getApplication().evaluateExpressionGet(context, "#{link.wadl}", String.class);
      /**
       * If the URI is not declared in the link file then try to get it as a
       * query param.
       */
//      System.out.println("DEBUG init wadl from link.properties " + uri);
//      if (uri == null || uri.isEmpty()) {
//      uri = context.getApplication().evaluateExpressionGet(context, "#{param['wadl']}", String.class);
//      System.out.println("DEBUG read WADL URI from query parameter " + uri);
//      }
      if (wadl == null || wadl.isEmpty()) {
        LOGGER.severe("Null or empty wadl URL.");
        throw new Exception("Null or empty wadl URL.");
      }
      String wadlFile;
      URL url = new URL(wadl);
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
      LOGGER.log(Level.SEVERE, null, ex);
    } catch (IOException | JAXBException ex) {
      LOGGER.log(Level.SEVERE, null, ex);
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
   * Get the base resources for the indicated WADL
   *
   * @return the WADL base resources
   */
  public List<Resource> getBaseResources() {
    return application != null
           ? application.getBaseResource().getResource()
           : new ArrayList<>();
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
    /**
     * If the application is not set the return an empty resource.
     */
    return application != null
           ? application.findResources(base)
           : new Resources();
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
   * @param resource the Resource
   * @return a non-null ArrayList.
   */
  public List<Method> findMethods(Resource resource) {
    /**
     * Failsafe in case the application did not load.
     */
    if (application == null) {
      return new ArrayList<>();
    }

    /**
     * Initialize and recursively populate the methods array. Recurse if the
     * found resource is not null.
     */
    ArrayList methods = new ArrayList();
    if (resource != null) {
      methods.addAll(findMethodsRecursive(resource));
    }
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
    try {
      if (HTTPMethods.PUT.equals(method.getName()) || HTTPMethods.POST.equals(method.getName())) {
        method.getRequest().getRepresentation().stream().forEach((Representation representation) -> {
          if (representation.getElement() != null) {
            elementMediaTypes.add(representation.getElement().getLocalPart(), representation);
          }
        });
      }
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, "findMethodRequestElements ERROR for method {0}.  {1}", new Object[]{method.getId(), e.getMessage()});
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
    try {
      response.getRepresentation().stream().forEach((representation) -> {
        elementMediaTypes.add(representation.getElement() != null
                              ? representation.getElement().getLocalPart()
                              : "Response",
                              representation);
      });
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, "findMethodResponseElements ERROR.  {0}", e.getMessage());
    }
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
    try {
      if (method.isSetRequest()) {
        parameterList.addAll(method.getRequest().getParam());
      }
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, "findMethodParameters ERROR for method {0}.  {1}", new Object[]{method.getId(), e.getMessage()});
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
      case "HEAD":
      case MediaType.APPLICATION_XML:
        return "success";
      case "POST":
      case "PUT":
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

  /**
   * {@inheritDoc}
   */
  @Override
  public String getLabel(String key) {
//     Helper method to log a warning if a requested name is not found. This helps
//    when developing a WADL labels file.

    System.out.println("DEBUG getLabel " + key);
//    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    return key;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getDescription(String key) {
    System.out.println("DEBUG getLabel " + key);
// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    return "<p>" + key + "</p>";
  }

}
