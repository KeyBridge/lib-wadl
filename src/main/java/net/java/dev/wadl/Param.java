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

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "param")
@XmlRootElement(name = "param")
public class Param {

  protected List<Doc> doc;
  protected List<Option> option;
  protected Link link;
  @XmlAttribute(name = "href")
  @XmlSchemaType(name = "anyURI")
  protected String href;
  @XmlAttribute(name = "name")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "NMTOKEN")
  protected String name;
  @XmlAttribute(name = "style")
  protected ParamStyle style;
  @XmlAttribute(name = "id")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  @XmlSchemaType(name = "ID")
  protected String id;
  @XmlAttribute(name = "type")
  protected QName type;
  @XmlAttribute(name = "default")
  protected String _default;
  @XmlAttribute(name = "required")
  protected Boolean required;
  @XmlAttribute(name = "repeating")
  protected Boolean repeating;
  @XmlAttribute(name = "fixed")
  protected String fixed;
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
