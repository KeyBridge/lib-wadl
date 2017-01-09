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

import java.util.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * 2.7 Resource Type
 * <p>
 * A resource_type element describes a set of methods that, together, define the
 * behavior of a type of resource. A resource_type may be used to define
 * resource behavior that is expected to be supported by multiple resources.
 *
 * @author Key Bridge LLC
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resource_type")
@XmlRootElement(name = "resource_type")
public class ResourceType {

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
   * <dt><code>query</code></dt>
   * <dd>Specifies a URI query parameter for all child <code>method</code>
   * elements of the resource type.</dd>
   * <dt><code>header</code></dt>
   * <dd>Specifies a HTTP header for use in the request part of all child
   * <code>method</code> elements of the resource type.</dd>
   * </dl>
   */
  protected List<Param> param;
  /**
   * Zero or more method (see section 2.8 ) elements, each of which describes an
   * HTTP protocol method that can be applied to a resource of this type.
   */
  protected List<Object> method;
  /**
   * Zero or more resource elements that describe sub-resources of resources of
   * this type. The URI of such sub-resources is provided by the path attribute
   * of the resource element and is relative to that of the parent resource.
   */
  protected List<Object> resource;
  /**
   * A required attribute of type xsd:ID that identifies the resource_type
   * element.
   */
  @XmlAttribute(name = "id")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  @XmlSchemaType(name = "ID")
  protected String id;

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

  public String getId() {
    return id;
  }

  public void setId(String value) {
    this.id = value;
  }//</editor-fold>

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 37 * hash + Objects.hashCode(this.id);
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
    final ResourceType other = (ResourceType) obj;
    return Objects.equals(this.id, other.id);
  }

  @Override
  public String toString() {
    return id;
  }

}
