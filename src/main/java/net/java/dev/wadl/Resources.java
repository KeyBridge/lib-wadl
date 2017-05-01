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
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.*;

/**
 * 2.5 Resources
 * <p>
 * The resources element acts as a container for the resources provided by the
 * application. A resources element has a base attribute of type xsd:anyURI that
 * provides the base URI for each child resource identifier. Descendent resource
 * elements (see section 2.6 ) describe the resources provided by the
 * application.
 *
 * @author Key Bridge LLC
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resources")
@XmlRootElement(name = "resources")
public class Resources implements PathProvider {

  /**
   * Zero or more doc elements - see section 2.3 Documentation.
   * <p>
   * The doc element has mixed content and may contain text and zero or more
   * child elements that form the body of the documentation.
   */
  protected List<Doc> doc;
  /**
   * A resource element describes a set of resources, each identified by a URI
   * that follows a common pattern.
   */
  @XmlElement(required = true)
  protected List<Resource> resource;
  /**
   * The base URI for each child resource identifier.
   */
  @XmlAttribute(name = "base")
  @XmlSchemaType(name = "anyURI")
  protected String base;

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  public List<Doc> getDoc() {
    if (doc == null) {
      doc = new ArrayList<>();
    }
    return this.doc;
  }

  public List<Resource> getResource() {
    if (resource == null) {
      resource = new ArrayList<>();
    }
    Collections.sort(resource); // alpha sort
    return this.resource;
  }

  public String getBase() {
    return base;
  }

  public void setBase(String value) {
    this.base = value;
  }//</editor-fold>

  /**
   * {@inheritDoc }
   */
  @Override
  public String getPath() {
    return base;
  }

  /**
   * {@inheritDoc }
   */
  @Override
  public String buildPath() {
    /**
     * Strip the trailing slash.
     */
    return base.endsWith("/") ? base.substring(0, base.length() - 1) : base;
  }

  /**
   * Call PostLoad on all children.
   */
  public void postLoad() {
    getResource().stream().forEach((r) -> {
      r.setParent(this);
      r.postLoad();
    });
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 97 * hash + Objects.hashCode(this.base);
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
    final Resources other = (Resources) obj;
    return Objects.equals(this.base, other.base);
  }

  @Override
  public String toString() {
    return base;
  }

  /**
   * {@inheritDoc }
   *
   * @deprecated NOT valid for Resources type.
   */
  @Override
  public List<Param> getParam() {
    return new ArrayList<>();
  }

}
