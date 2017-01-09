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

/**
 * 2.3 Documentation
 * <p>
 * Each WADL-defined element can have one or more child doc elements that can be
 * used to document that element.
 *
 * @author Key Bridge LLC
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "doc")
@XmlRootElement(name = "doc")
public class Doc {

  /**
   * The doc element has mixed content and may contain text and zero or more
   * child elements that form the body of the documentation. It is RECOMMENDED
   * that the child elements be members of the text, list or table modules of
   * XHTML.
   */
  @XmlMixed
  @XmlAnyElement(lax = true)
  protected List<Object> content;
  /**
   * A short plain text description of the element being documented, the value
   * SHOULD be suitable for use as a title for the contained documentation.
   */
  @XmlAttribute(name = "title")
  protected String title;
  /**
   * Defines the language for the title attribute value and the contents of the
   * doc element. If an element contains more than one doc element then they
   * MUST have distinct values for their xml:lang attribute.
   */
  @XmlAttribute(name = "lang", namespace = "http://www.w3.org/XML/1998/namespace")
  protected String lang;

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  public List<Object> getContent() {
    if (content == null) {
      content = new ArrayList<>();
    }
    return this.content;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String value) {
    this.title = value;
  }

  public String getLang() {
    return lang;
  }

  public void setLang(String value) {
    this.lang = value;
  }//</editor-fold>

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 67 * hash + Objects.hashCode(this.title);
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
    final Doc other = (Doc) obj;
    return Objects.equals(this.title, other.title);
  }

  @Override
  public String toString() {
    return title;
  }

}
