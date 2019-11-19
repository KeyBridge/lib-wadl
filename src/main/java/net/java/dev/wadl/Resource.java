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
package net.java.dev.wadl;

import ch.keybridge.wadl.PathProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

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
public class Resource implements PathProvider, Comparable<Resource> {

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
   * <dl>
   * <dt><code>template</code></dt>
   * <dd>Provides additional information about an embedded template parameter,
   * see above. Child <code>param</code> elements whose <code>name</code>
   * attribute value does not match the <code>name</code> of an embedded
   * template parameter are ignored.</dd>
   * <dt><code>matrix</code></dt>
   * <dd>Specifies a matrix URI parameter</dd>
   * <dt><code>query</code></dt>
   * <dd>Specifies a global URI query parameter for all child
   * <code>method</code> elements of the resource. Does not apply to methods
   * inherited from a <code>resource_type</code> specified using the
   * <code>type</code> attribute.</dd>
   * <dt><code>header</code></dt>
   * <dd>Specifies a global HTTP header for use in the request part of all child
   * <code>method</code> elements of the resource. Does not apply to methods
   * inherited from a <code>resource_type</code> specified using the
   * <code>type</code> attribute.</dd>
   * </dl>
   * <p>
   * Additional information about embedded template parameters can be conveyed
   * using a child param element with a style attribute value 'template' whose
   * name attribute value matches the name of the parameter embedded in the
   * template. E.g., in the following the type of the widgetId template
   * parameter is specified by the child param element.
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
   * <p>
   * The value of the path attribute may be static or may contain embedded
   * template parameters. At runtime, the values of template parameters are
   * substituted into the resource identifier when the resource is used, see
   * section 2.6.1 for a detailed example.
   */
  @XmlAttribute(name = "path")
  protected String path;

  /**
   * The parent Resources instance.
   */
  @XmlTransient
  private PathProvider parent;

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
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

  /**
   * Get the attribute of type xsd:ID that identifies the resource element.
   * <p>
   * If the ID is not set then a random UUID is provided.
   *
   * @return a non-null unique identifier
   */
  public String getId() {
    if (id == null) {
      id = UUID.randomUUID().toString();
    }
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

  /**
   * {@inheritDoc }
   */
  @Override
  public String getPath() {
    return path;
  }

  public void setPath(String value) {
    this.path = value;
  }//</editor-fold>

  /**
   * Get the parent Resources instance.
   *
   * @return the parent Resources instance
   */
  public PathProvider getParent() {
    return parent;
  }

  /**
   * Set the parent Resources instance.
   *
   * @param parent the parent Resources instance
   */
  public void setParent(PathProvider parent) {
    this.parent = parent;
  }

  /**
   * {@inheritDoc }
   */
  @Override
  public String buildPath() {
    return parent != null
           ? parent.buildPath() + (path.isEmpty() ? "" : "/" + path) // avoid double slash
           : path;
  }

  /**
   * Call PostLoad on all children.
   */
  public void postLoad() {
    for (Resource resource : getResources()) {
      resource.setParent(this);
      resource.postLoad();
    }
    for (Method method : getMethods()) {
      method.setParent(this);
    }
  }

  /**
   * Comparison is based on path.
   *
   * @param o the other resource
   * @return the sort order
   */
  @Override
  public int compareTo(Resource o) {
    try {
      return this.path.compareTo(o.getPath());
    } catch (Exception e) {
      return -1;
    }
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
    return Objects.equals(this.path, other.path);
  }

  @Override
  public String toString() {
    return path;
  }

}
