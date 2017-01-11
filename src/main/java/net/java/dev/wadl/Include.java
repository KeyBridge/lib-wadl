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

import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.*;

/**
 * 2.4.1 Include
 * <p>
 * The include element allows the definitions of one or more data format
 * descriptions to be included by reference. Use of the include element is
 * logically equivalent to in-lining the referenced document within the WADL
 * grammars element.
 *
 * @author Key Bridge LLC
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "include")
@XmlRootElement(name = "include")
public class Include {

  /**
   * Zero or more doc elements - see section 2.3 .
   * <p>
   * Provides a short plain text description of the element being documented,
   * the value SHOULD be suitable for use as a title for the contained
   * documentation.
   */
  protected List<Doc> doc;
  /**
   * The href attribute provides a URI for the referenced definitions and is of
   * type xsd:anyURI.
   */
  @XmlAttribute(name = "href")
  @XmlSchemaType(name = "anyURI")
  protected String href;

  public List<Doc> getDoc() {
    return doc;
  }

  public void setDoc(List<Doc> doc) {
    this.doc = doc;
  }

  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 97 * hash + Objects.hashCode(this.href);
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
    final Include other = (Include) obj;
    return Objects.equals(this.href, other.href);
  }

  @Override
  public String toString() {
    return href;
  }

}
