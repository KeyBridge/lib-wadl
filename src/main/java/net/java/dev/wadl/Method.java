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

import ch.keybridge.lib.wadl.PathProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * 2.8 Method
 * <p>
 * A method element describes the input to and output from an HTTP protocol
 * method that may be applied to a resource. A method element can either be a
 * method definition or a reference to a method defined elsewhere.
 *
 * @author Key Bridge LLC
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "method")
@XmlRootElement(name = "method")
public class Method implements Comparable<Method> {

  /**
   * Zero or more doc elements - see section 2.3 Documentation.
   * <p>
   * The doc element has mixed content and may contain text and zero or more
   * child elements that form the body of the documentation.
   */
  protected List<Doc> doc;
  /**
   * Describes the input to the method as a collection of parameters and an
   * optional resource representation - see section 2.9 . A request element
   * describes the input to be included when applying an HTTP method to a
   * resource.
   */
  protected Request request;
  /**
   * Zero or more response elements that describe the possible outputs of the
   * method - see section 2.10. A response element describes the output that
   * results from performing an HTTP method on a resource.
   */
  protected List<Response> response;
  /**
   * An identifier for the method, required for globally defined methods, not
   * allowed on locally embedded methods. Methods are identified by an XML ID
   * and are referred to using a URI reference.
   */
  @XmlAttribute(name = "id")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  @XmlSchemaType(name = "ID")
  protected String id;
  /**
   * Indicates the HTTP method used.
   * <p>
   * It is permissible to have multiple child method elements that have the same
   * value of the name attribute for a given resource; such siblings represent
   * distinct variations of the same HTTP method and will typically have
   * different input data.
   */
  @XmlAttribute(name = "name")
  protected HTTPMethods name;
  /**
   * 2.8.1 Method Reference
   * <p>
   * A method reference element is a child of a resource element that has an
   * href attribute whose type is xsd:anyURI. The value of the href attribute is
   * a cross reference (see section 2.1 ) to a method definition element. A
   * method reference element MUST NOT have any other WADL-defined attributes or
   * contain any WADL-defined child elements.
   */
  @XmlAttribute(name = "href")
  @XmlSchemaType(name = "anyURI")
  protected String href;

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

  public Request getRequest() {
    return request;
  }

  public void setRequest(Request value) {
    this.request = value;
  }

  public boolean isSetRequest() {
    return this.request != null;
  }

  public List<Response> getResponse() {
    if (response == null) {
      response = new ArrayList<>();
    }
    return this.response;
  }

  public String getId() {
    return id;
  }

  public void setId(String value) {
    this.id = value;
  }

  public HTTPMethods getName() {
    return name;
  }

  public void setName(HTTPMethods value) {
    this.name = value;
  }

  public String getHref() {
    return href;
  }

  public void setHref(String value) {
    this.href = value;
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
   * Get the fully qualified URI pattern to call this methods.
   *
   * @return the URI pattern for this method.
   */
  public String getPath() {
    return parent != null ? parent.buildPath() : "";
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 97 * hash + Objects.hashCode(this.id);
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
    final Method other = (Method) obj;
    if (!Objects.equals(this.id, other.id)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return name + " " + id;
  }

  @Override
  public int compareTo(Method o) {
    return this.id.equals(o.getId())
           ? this.name.compareTo(o.getName())
           : this.id.compareTo(o.getId());
  }

}
