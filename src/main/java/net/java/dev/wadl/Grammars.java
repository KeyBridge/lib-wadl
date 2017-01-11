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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 2.4 Grammars
 * <p>
 * The grammars element acts as a container for definitions of the format of
 * data exchanged during execution of the protocol described by the WADL
 * document. Such definitions may be included inline or by reference using the
 * include element (see section 2.4.1 ). No particular data format definition
 * language language is mandated; sections 3 and 4 describe use of RelaxNG and
 * W3C XML Schema with WADL, respectively.
 * <p>
 * It is permissible to include multiple definitions of a particular format:
 * such definitions are assumed to be equivalent and consumers of a WADL
 * description are free to choose amongst the alternatives or even combine them
 * if they support that capability.
 *
 * @author Key Bridge LLC
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "grammars")
@XmlRootElement(name = "grammars")
public class Grammars {

  /**
   * Zero or more doc elements - see section 2.3 .
   * <p>
   * Provides a short plain text description of the element being documented,
   * the value SHOULD be suitable for use as a title for the contained
   * documentation.
   */
  protected List<Doc> doc;
  /**
   * The include element allows the definitions of one or more data format
   * descriptions to be included by reference. The href attribute provides a URI
   * for the referenced definitions and is of type xsd:anyURI.
   */
  protected List<Include> include;

  public List<Doc> getDoc() {
    if (doc == null) {
      doc = new ArrayList<>();
    }
    return this.doc;
  }

  public List<Include> getInclude() {
    if (include == null) {
      include = new ArrayList<>();
    }
    return this.include;
  }

}
