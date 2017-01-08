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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.*;
import javax.xml.namespace.QName;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resources")
@XmlRootElement(name = "resources")
public class Resources {

  protected List<Doc> doc;
  @XmlElement(required = true)
  protected List<Resource> resource;
  @XmlAnyElement(lax = true)
  protected List<Object> any;
  @XmlAttribute(name = "base")
  @XmlSchemaType(name = "anyURI")
  protected String base;
  @XmlAnyAttribute
  private final Map<QName, String> otherAttributes = new HashMap<>();

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
    return this.resource;
  }

  public List<Object> getAny() {
    if (any == null) {
      any = new ArrayList<>();
    }
    return this.any;
  }

  public String getBase() {
    return base;
  }

  public void setBase(String value) {
    this.base = value;
  }

  public Map<QName, String> getOtherAttributes() {
    return otherAttributes;
  }

}
