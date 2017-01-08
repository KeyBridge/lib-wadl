package net.java.dev.wadl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;

/**
 * The application element forms the root of a WADL description.
 *
 * @author Key Bridge LLC
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "application")
@XmlRootElement(name = "application")
public class Application {

  /**
   * Provides a short plain text description of the element being documented.
   */
  protected List<Doc> doc;
  /**
   * The grammars element acts as a container for definitions of the format of
   * data exchanged during execution of the protocol described by the WADL
   * document. Such definitions may be included inline or by reference using the
   * include element (see section 2.4.1 ). No particular data format definition
   * language language is mandated; sections 3 and 4 describe use of RelaxNG and
   * W3C XML Schema with WADL, respectively.
   */
  protected Grammars grammars;
  /**
   * The resources element acts as a container for the resources provided by the
   * application. A resources element has a base attribute of type xsd:anyURI
   * that provides the base URI for each child resource identifier. Descendent
   * resource elements (see section 2.6 ) describe the resources provided by the
   * application.
   */
  protected List<Resources> resources;
  /**
   * A resource_type element describes a set of methods that, together, define
   * the behavior of a type of resource. A resource_type may be used to define
   * resource behavior that is expected to be supported by multiple resources.
   */
  @XmlElement(name = "resource_type")
  protected List<ResourceType> resourceType;
  /**
   * A method element describes the input to and output from an HTTP protocol
   * method that may be applied to a resource. A method element can either be a
   * method definition or a reference to a method defined elsewhere.
   */
  @XmlElement(name = "method")
  protected List<Method> methods;
  /**
   * A representation element describes a representation of a resource's state.
   * A representation element can either be a representation definition or a
   * reference to a representation defined elsewhere.
   */
  @XmlElement(name = "representation")
  protected List<Representation> representations;
  /**
   * A param element describes a parameterized component of its parent element.
   * A param element can either be a parameter definition or a reference to a
   * parameter defined elsewhere.
   */
  @XmlElement(name = "param")
  protected List<Param> params;

  // the default compiled version
//  @XmlElements({
//    @XmlElement(name = "resource_type", type = ResourceType.class),
//    @XmlElement(name = "method", type = Method.class),
//    @XmlElement(name = "representation", type = Representation.class),
//    @XmlElement(name = "param", type = Param.class)
//  })
//  protected List<Object> elements;
  @XmlAnyElement(lax = true)
  protected List<Object> any;

  public List<Doc> getDoc() {
    if (doc == null) {
      doc = new ArrayList<>();
    }
    return this.doc;
  }

  public Grammars getGrammars() {
    return grammars;
  }

  public void setGrammars(Grammars value) {
    this.grammars = value;
  }

  public List<Resources> getResources() {
    if (resources == null) {
      resources = new ArrayList<>();
    }
    return this.resources;
  }

  public List<ResourceType> getResourceType() {
    if (resourceType == null) {
      resourceType = new ArrayList<>();
    }
    return resourceType;
  }

  public List<Method> getMethods() {
    if (methods == null) {
      methods = new ArrayList<>();
    }
    return methods;
  }

  public List<Representation> getRepresentations() {
    if (representations == null) {
      representations = new ArrayList<>();
    }
    return representations;
  }

  public List<Param> getParams() {
    if (params == null) {
      params = new ArrayList<>();
    }
    return params;
  }

  public List<Object> getAny() {
    if (any == null) {
      any = new ArrayList<>();
    }
    return this.any;
  }

  /**
   * Scan the {@code resources} list (generally only one entry) for a single
   * {@code resource} having the indicated path.
   *
   * @param path the path label
   * @return the matching resource description
   */
  public Resource findResource(String path) {
    /**
     * Generally there will be only one Resources entry in the application,
     * which declares the base path.
     * <p>
     * The 'resources' entry then contains a collection of 'resource' entries.
     */
    return getResources().iterator().next().getResource().stream()
            .filter(resource -> resource.getPath().equals(path))
            .reduce((a, b) -> {
              throw new IllegalStateException("Multiple resources match " + path);
            })
            .get();
  }

  /**
   * Scan the {@code methods} list and return the one (and only one) entry
   * matching the provided {@code href} label. This supports WADL instances
   * where the method description is separated from the resource description,
   * rather than embedded. See the "amazonsearch.wadl" example in the testing
   * resources for an example.
   *
   * @param href the method HREF label
   * @return the matching method description
   */
  public Method findMethod(String href) {
    String methodId = href.startsWith("#") ? href.substring(1, href.length()) : href;
    return getMethods().stream()
            .filter(method -> method.getId().equals(methodId))
            .reduce((a, b) -> {
              throw new IllegalStateException("Multiple methods match " + href);
            })
            .get();
  }

}
