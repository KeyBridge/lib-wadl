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

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "method")
@XmlRootElement(name = "method")
public class Method {

  protected List<Doc> doc;
  protected Request request;
  protected List<Response> response;
  @XmlAttribute(name = "id")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  @XmlSchemaType(name = "ID")
  protected String id;
  @XmlAttribute(name = "name")
  protected String name;
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

  public String getName() {
    return name;
  }

  public void setName(String value) {
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

}
