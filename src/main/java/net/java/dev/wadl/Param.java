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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;

/**
 * 2.12 Parameter
 * <p>
 * A param element describes a parameterized component of its parent element. A
 * param element can either be a parameter definition or a reference to a
 * parameter defined elsewhere.
 * <p>
 * 2.12.2 Parameter Definition
 * <p>
 * A param definition element describes a parameterized component of its parent
 * element and may be a child of a resource (see section 2.6 ), application (see
 * section 2.2 ), request (see section 2.9 ), response (see section 2.10 ), or a
 * representation (see section 2.11 ) element. A param definition element has
 * zero or more doc child elements (see section 2.3 ), zero or more option child
 * elements (see section 2.12.3 ), an optional link child element (see section
 * 2.12.4 ) and has the following attributes:
 * <p>
 * Note that some combinations of the Parameter attributes might not make sense
 * in all cases. E.g. matrix URI parameters are normally optional so a param
 * element with a style value of 'matrix' and a required value of 'true' might
 * be unwise.
 *
 * @author Key Bridge LLC
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "param")
@XmlRootElement(name = "param")
public class Param {

  /**
   * Child doc elements that can be used to document the element. The doc
   * element has mixed content and may contain text and zero or more child
   * elements that form the body of the documentation.
   */
  protected List<Doc> doc;
  /**
   * An option element defines one of a set of possible values for the parameter
   * represented by its parent param element.
   */
  protected List<Option> option;
  /**
   * A link element is used to identify links to resources within
   * representations. A link element is a child of a param element whose path
   * attribute identifies the portion of its parent representation that contains
   * a link URI.
   */
  protected Link link;
  /**
   * A param reference element is a param element that has an href attribute
   * whose type is xsd:anyURI. The value of the href attribute is a cross
   * reference (see section 2.1 ) to a param definition element. A param
   * reference element MUST NOT have any other WADL-defined attributes or
   * contain any WADL-defined child elements.
   */
  @XmlAttribute(name = "href")
  @XmlSchemaType(name = "anyURI")
  protected String href;
  /**
   * The name of the parameter as an xsd:NMTOKEN. Required.
   */
  @XmlAttribute(name = "name")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "NMTOKEN")
  protected String name;
  /**
   * Indicates the parameter style, table 1 on page 25 lists the allowed values
   * and shows the context(s) in which each value may be used.
   */
  @XmlAttribute(name = "style")
  protected ParamStyle style;
  /**
   * An optional identifier that may be used to refer to a parameter definition
   * using a URI reference.
   */
  @XmlAttribute(name = "id")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  @XmlSchemaType(name = "ID")
  protected String id;
  /**
   * Optionally indicates the type of the parameter as an XML qualified name,
   * defaults to xsd:string.
   */
  @XmlAttribute(name = "type")
  protected QName type;
  /**
   * Optionally provides a value that is considered identical to an unspecified
   * parameter value.
   */
  @XmlAttribute(name = "default")
  protected String _default;
  /**
   * Optionally indicates whether the parameter is required to be present or
   * not, defaults to false (parameter not required).
   */
  @XmlAttribute(name = "required")
  protected Boolean required;
  /**
   * Optionally indicates whether the parameter is single valued or may have
   * multiple values, defaults to false (parameter is single valued).
   */
  @XmlAttribute(name = "repeating")
  protected Boolean repeating;
  /**
   * Optionally provides a fixed value for the parameter.
   */
  @XmlAttribute(name = "fixed")
  protected String fixed;
  /**
   * When the parent element is a representation element, this attribute
   * optionally provides a path to the value of the parameter within the
   * representation. For XML representations, use of XPath 1.0 is recommended.
   */
  @XmlAttribute(name = "path")
  protected String path;

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  public List<Doc> getDoc() {
    if (doc == null) {
      doc = new ArrayList<>();
    }
    return this.doc;
  }

  public List<Option> getOption() {
    if (option == null) {
      option = new ArrayList<>();
    }
    return this.option;
  }

  public Link getLink() {
    return link;
  }

  public void setLink(Link value) {
    this.link = value;
  }

  public String getHref() {
    return href;
  }

  public void setHref(String value) {
    this.href = value;
  }

  public String getName() {
    return name;
  }

  public void setName(String value) {
    this.name = value;
  }

  public ParamStyle getStyle() {
    return style;
  }

  public void setStyle(ParamStyle value) {
    this.style = value;
  }

  public String getId() {
    return id;
  }

  public void setId(String value) {
    this.id = value;
  }

  public QName getType() {
    if (type == null) {
      return new QName("http://www.w3.org/2001/XMLSchema", "string", "xs");
    } else {
      return type;
    }
  }

  public void setType(QName value) {
    this.type = value;
  }

  public String getDefault() {
    return _default;
  }

  public void setDefault(String value) {
    this._default = value;
  }

  public boolean isRequired() {
    if (required == null) {
      return false;
    } else {
      return required;
    }
  }

  public void setRequired(Boolean value) {
    this.required = value;
  }

  public boolean isRepeating() {
    if (repeating == null) {
      return false;
    } else {
      return repeating;
    }
  }

  public void setRepeating(Boolean value) {
    this.repeating = value;
  }

  public String getFixed() {
    return fixed;
  }

  public void setFixed(String value) {
    this.fixed = value;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String value) {
    this.path = value;
  }//</editor-fold>

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 41 * hash + Objects.hashCode(this.name);
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
    final Param other = (Param) obj;
    return Objects.equals(this.name, other.name);
  }

  @Override
  public String toString() {
    return name;
  }

}
