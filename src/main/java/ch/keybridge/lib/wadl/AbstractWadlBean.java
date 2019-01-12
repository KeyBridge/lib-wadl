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

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
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
public abstract class AbstractWadlBean implements LabelProvider {

  private static final Logger LOG = Logger.getLogger(AbstractWadlBean.class.getName());

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
  public AbstractWadlBean() {
    /**
     * Disable SSL handshake.
     */
    System.setProperty("jsse.enableSNIExtension", "false");
  }

  /**
   * Load read the WADL and marshal the application.
   */
//  @PostConstruct  protected void postConstruct() {    autoload();  }
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
   * <p>
   * This method allows the user to specify the WADL location with a fully
   * qualified URL. e.g.
   * {@code http://localhost/application/rest/application.wadl}
   *
   * @param wadlUrl the fully qualified WADL URL
   */
  public void load(String wadlUrl) {
    try {
      downloadWADL(wadlUrl);
    } catch (Exception exception) {
      LOG.severe(exception.getMessage());
    }
  }

  /**
   * AJAX method typically called on page load to try to automatically
   * initialize this WADL bean.
   * <p>
   * This method searches the current application context root for a resource
   * named {@code application.wadl}. The search will look for one of two
   * application paths: {@code [context-root]/rest/} and
   * {@code [context-root][resources]}.
   * <p>
   */
  public void autoload() {
    /**
     * Get the current context path (i.e. the application context root). This is
     * copied from FacesUtil.getContextPath().
     */
    ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
    String contextPath = new StringBuilder()
      .append(context.getRequestScheme())
      .append("://")
      .append(context.getRequestServerName())
      .append((context.getRequestServerPort() != 80 && context.getRequestServerPort() != 443)
              ? ":" + context.getRequestServerPort()
              : "")
      .append(context.getRequestContextPath())
      .toString();
    /**
     * Try to load the WADL from various commonly used JAXRS contexts. If the
     * application declares a custom context is must be specified.
     */
    for (String restContext : new String[]{"api", "rest", "resource", "resources", "webresources"}) {
      load(contextPath + "/" + restContext + "/application.wadl");
      if (wadl != null) {
        break;
      }
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
     * Download and parse the WADL file.
     */
    try {
      /**
       * Read the WADL file.
       */
      if (wadl == null || wadl.isEmpty()) {
        LOG.severe("Null or empty wadl URL.");
        throw new Exception("Null or empty wadl URL.");
      }
      /**
       * Try to use an all-trusting trust manager that ignores all SSL errors.
       */
      Client client;
      try {
        SSLContext sslcontext = SSLContext.getInstance("TLS");
        sslcontext.init(null, new TrustManager[]{new X509TrustManager() {
          @Override
          public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
          }

          @Override
          public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
          }

          @Override
          public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
          }

        }}, new java.security.SecureRandom());
        client = ClientBuilder.newBuilder().sslContext(sslcontext).hostnameVerifier((s1, s2) -> true).build();
      } catch (NoSuchAlgorithmException | KeyManagementException noSuchAlgorithmException) {
        client = ClientBuilder.newClient();
      }
      /**
       * Read and unmarshal the application from the WADL file.
       */
      this.application = client.target(wadl).request().get(Application.class);
      /**
       * Call PostLoad to set the inter-object parent/child relationships.
       */
      this.application.postLoad();
      /**
       * Set the WADL IFF the application was successfully loaded and parsed.
       */
      this.wadl = wadl;
    } catch (MalformedURLException ex) {
      LOG.log(Level.SEVERE, null, ex);
    } catch (IOException | JAXBException ex) {
      LOG.log(Level.SEVERE, null, ex);
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
      LOG.log(Level.SEVERE, "findMethodRequestElements ERROR for method {0}.  {1}", new Object[]{method.getId(), e.getMessage()});
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
      LOG.log(Level.SEVERE, "findMethodResponseElements ERROR.  {0}", e.getMessage());
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
      LOG.log(Level.SEVERE, "findMethodParameters ERROR for method {0}.  {1}", new Object[]{method.getId(), e.getMessage()});
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
        return "secondary";
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
    /**
     * Abstract helper method to log if a requested name is not found. This
     * helps when developing a WADL labels file.
     */
    LOG.log(Level.INFO, "AbstractWadlBean getLabel {0}", key);
    return "<code>" + key + "</code>";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getLabel(String method, String parameter) {
    /**
     * Abstract helper method to log if a requested name is not found. This
     * helps when developing a WADL labels file.
     */
    LOG.log(Level.INFO, "AbstractWadlBean getLabel {0}-{1}", new Object[]{method, parameter});
    return "<code>" + method + "-" + parameter + "</code>";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getMethodDescription(String key) {
    /**
     * Abstract helper method to log if a requested name is not found. This
     * helps when developing a WADL labels file.
     */
    LOG.log(Level.INFO, "AbstractWadlBean getMethodDescription {0}", key);
    return "<p>" + key + "</p>";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String parseMethodId(String methodId) {
    return methodId;
  }

}
