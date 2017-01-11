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
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * 2.12.4 Link
 * <p>
 * A link element is used to identify links to resources within representations.
 * A link element is a child of a param element whose path attribute identifies
 * the portion of its parent representation that contains a link URI.
 *
 * @author Key Bridge LLC
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "link")
@XmlRootElement(name = "link")
public class Link {

  /**
   * A link element contains zero or more doc elements (see section 2.3).
   */
  protected List<Doc> doc;
  @XmlAnyElement(lax = true)
  protected List<Object> any;
  /**
   * An optional cross reference (see section 2.1) to a resource_type element
   * that defines the capabilities of the resource that the link identifies.
   */
  @XmlAttribute(name = "resource_type")
  @XmlSchemaType(name = "anyURI")
  protected String resourceType;
  /**
   * An optional token that identifies the relationship of the resource
   * identified by the link to the resource whose representation the link is
   * embedded in. The value is scoped by the value of the ancestor
   * representation element's profile attribute.
   */
  @XmlAttribute(name = "rel")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected String rel;
  /**
   * An optional token that identifies the relationship of the resource whose
   * representation the link is embedded in to the resource identified by the
   * link. This is the reverse relationship to that identified by the rel
   * attribute. The value is scoped by the value of the ancestor representation
   * element's profile attribute.
   */
  @XmlAttribute(name = "rev")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected String rev;

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  public List<Doc> getDoc() {
    return doc;
  }

  public void setDoc(List<Doc> doc) {
    this.doc = doc;
  }

  public List<Object> getAny() {
    return any;
  }

  public void setAny(List<Object> any) {
    this.any = any;
  }

  public String getResourceType() {
    return resourceType;
  }

  public void setResourceType(String resourceType) {
    this.resourceType = resourceType;
  }

  public String getRel() {
    return rel;
  }

  public void setRel(String rel) {
    this.rel = rel;
  }

  public String getRev() {
    return rev;
  }

  public void setRev(String rev) {
    this.rev = rev;
  }//</editor-fold>

}
