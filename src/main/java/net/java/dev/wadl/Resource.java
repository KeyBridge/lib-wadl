package net.java.dev.wadl;

import java.util.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;

/**
 * 2.6 Resource
 * <p>
 * A resource element describes a set of resources, each identified by a URI
 * that follows a common pattern.
 *
 * @author Key Bridge LLC
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resource")
@XmlRootElement(name = "resource")
public class Resource {

  /**
   * Zero or more doc elements - see section 2.3 .
   * <p>
   * Provides a short plain text description of the element being documented,
   * the value SHOULD be suitable for use as a title for the contained
   * documentation.
   */
  protected List<Doc> doc;
  /**
   * Zero or more param elements (see section 2.12 ) with one of the following
   * values for its style attribute:
   * <ul>
   * <li>template: Provides additional information about an embedded template
   * parameter, see above. Child param elements whose name attribute value does
   * not match the name of an embedded template parameter are ignored.</li>
   * <li>matrix: Specifies a matrix URI parameter</li>
   * <li>query: Specifies a global URI query parameter for all child method
   * elements of the resource. Does not apply to methods inherited from a
   * resource_type specified using the type attribute.</li>
   * <li>header: Specifies a global HTTP header for use in the request part of
   * all child method elements of the resource. Does not apply to methods
   * inherited from a resource_type specified using the type attribute.
   * </li></ul>
   */
  protected List<Param> param;
  /**
   * Zero or more method(see section 2.8 ) elements, each of which describes the
   * input to and output from an HTTP protocol method that can be applied to the
   * resource. Such locally-defined methods are added to any methods included in
   * resource_type elements referred to using the type attribute.
   */
  @XmlElement(name = "method")
  protected List<Method> methods;
  /**
   * Zero or more resource elements that describe sub-resources. Such
   * sub-resources inherit matrix and template parameters from the parent
   * resource since their URI is relative to that of the parent resource but
   * they do not inherit query or header parameters specified globally for the
   * parent resource.
   */
  @XmlElement(name = "resource")
  protected List<Resource> resources;

  @XmlAnyElement(lax = true)
  protected List<Object> any;

  /**
   * An optional attribute of type xsd:ID that identifies the resource element.
   */
  @XmlAttribute(name = "id")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  @XmlSchemaType(name = "ID")
  protected String id;
  /**
   * An optional attribute whose type is a space-separated list of of
   * xsd:anyURI. Each value in the list is a cross reference (see section 2.1 )
   * that identifies a resource_type element (see section 2.7 ) that defines a
   * set of methods supported by the resource.
   */
  @XmlAttribute(name = "type")
  protected List<String> type;
  /**
   * Defines the media type for the query component of the resource URI.
   * Defaults to 'application/x-www-form-urlencoded' if not specified which
   * results in query strings being formatted as specified in section 17.13 of
   * HTML 4.01[3].
   */
  @XmlAttribute(name = "queryType")
  protected String queryType;
  /**
   * An optional attribute of type xsd:string. If present, it provides a
   * relative URI template for the identifier of the resource. The resource's
   * base URI is given by the resource element's parent resource or resources
   * element.
   */
  @XmlAttribute(name = "path")
  protected String path;

  @XmlAnyAttribute
  private final Map<QName, String> otherAttributes = new HashMap<>();

  public List<Doc> getDoc() {
    if (doc == null) {
      doc = new ArrayList<>();
    }
    return this.doc;
  }

  public List<Param> getParam() {
    if (param == null) {
      param = new ArrayList<>();
    }
    return this.param;
  }

  public List<Method> getMethods() {
    if (methods == null) {
      methods = new ArrayList<>();
    }
    return methods;
  }

  public List<Resource> getResources() {
    if (resources == null) {
      resources = new ArrayList<>();
    }
    return resources;
  }

  public List<Object> getAny() {
    if (any == null) {
      any = new ArrayList<>();
    }
    return this.any;
  }

  public String getId() {
    return id;
  }

  public void setId(String value) {
    this.id = value;
  }

  public List<String> getType() {
    if (type == null) {
      type = new ArrayList<>();
    }
    return this.type;
  }

  public String getQueryType() {
    if (queryType == null) {
      return "application/x-www-form-urlencoded";
    } else {
      return queryType;
    }
  }

  public void setQueryType(String value) {
    this.queryType = value;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String value) {
    this.path = value;
  }

  public Map<QName, String> getOtherAttributes() {
    return otherAttributes;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 79 * hash + Objects.hashCode(this.path);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Resource other = (Resource) obj;
    if (!Objects.equals(this.path, other.path)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return path;
  }

}
