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
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;

/**
 * 2.11 Representation
 * <p>
 * A representation element describes a representation of a resource's state. A
 * representation element can either be a representation definition or a
 * reference to a representation defined elsewhere.
 * <p>
 * 2.11.1 Representation Reference
 * <p>
 * A representation reference element can be a child of a request or response
 * element. It has a href attribute of type xsd:anyURI. The value of the href
 * attribute is a cross reference (see section 2.1 ) to a representation
 * definition element. A representation reference element MUST NOT have any
 * other WADL-defined attributes or contain any WADL-defined child elements.
 * <p>
 * 2.11.2 Representation Definition
 * <p>
 * A representation definition element can be a child of a request, response or
 * application element.
 *
 * @author Key Bridge LLC
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "representation")
@XmlRootElement(name = "representation")
public class Representation {

  /**
   * Zero or more doc elements - see section 2.3 .
   * <p>
   * Provides a short plain text description of the element being documented,
   * the value SHOULD be suitable for use as a title for the contained
   * documentation.
   */
  protected List<Doc> doc;
  /**
   * Zero or more param elements (see section 2.12 ). A child param element (see
   * section 2.12 ) is used to parameterize its parent representation element.
   * Representation parameters can have one of two different functions depending
   * on the media type of the representation:
   * <ol>
   * <li>Define the content of the representation. For
   * <code>representation</code> elements with a <code>mediaType</code>
   * attribute whose value is either 'application/x-www-form-urlencoded' or
   * 'multipart/form-data' the representation parameters define the content of
   * the representation which is formatted according to the media type. The same
   * may apply to other media types.</li>
   * <li>Provide a hint to processors about items of interest within a
   * representation. For XML based representations, representation parameters
   * can be used to identify items of interest with the XML. The
   * <code>path</code> attribute of a representation parameter indicates the
   * path to the value of the parameter within the representation. For XML-based
   * representations this is an XPath expression.</li>
   * </ol>
   */
  protected List<Param> param;
  /**
   * An identifier for the representation, required for globally defined
   * representations, not allowed on locally embedded representations.
   * Representations are identified by an XML ID and are referred to using a URI
   * reference.
   */
  @XmlAttribute(name = "id")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  @XmlSchemaType(name = "ID")
  protected String id;
  /**
   * For XML-based representations, specifies the qualified name of the root
   * element as described within the grammars section - see section 2.4 .
   */
  @XmlAttribute(name = "element")
  protected QName element;
  /**
   * Indicates the media type of the representation. Media ranges (e.g. text/*)
   * are acceptable and indicate that any media type in the specified range is
   * supported.
   */
  @XmlAttribute(name = "mediaType")
  protected String mediaType;
  /**
   * Representation Reference
   * <p>
   * A representation reference element can be a child of a request or response
   * element. It has a href attribute of type xsd:anyURI. The value of the href
   * attribute is a cross reference (see section 2.1 ) to a representation
   * definition element.
   */
  @XmlAttribute(name = "href")
  @XmlSchemaType(name = "anyURI")
  protected String href;
  /**
   * Similar to the HTML profile attribute, gives the location of one or more
   * meta data profiles, separated by white space. The meta-data profiles define
   * the meaning of the rel and rev attributes of descendent link elements (see
   * section 2.12.4 ).
   */
  @XmlAttribute(name = "profile")
  protected List<String> profile;

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  public List<Doc> getDoc() {
    if (doc == null) {
      doc = new ArrayList<>();
    }
    return this.doc;
  }

  public List<Param> getParam() {
    if (param == null) {
      param = new ArrayList<>();
    }
    return this.param;
  }

  public String getId() {
    return id;
  }

  public void setId(String value) {
    this.id = value;
  }

  public QName getElement() {
    return element;
  }

  public void setElement(QName value) {
    this.element = value;
  }

  public String getMediaType() {
    return mediaType;
  }

  public void setMediaType(String value) {
    this.mediaType = value;
  }

  public String getHref() {
    return href;
  }

  public void setHref(String value) {
    this.href = value;
  }

  public List<String> getProfile() {
    if (profile == null) {
      profile = new ArrayList<>();
    }
    return this.profile;
  }//</editor-fold>

}
