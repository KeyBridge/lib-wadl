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

/**
 * 2.10 Response
 * <p>
 * A response element describes the output that results from performing an HTTP
 * method on a resource.
 *
 * @author Key Bridge LLC
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "response")
@XmlRootElement(name = "response")
public class Response {

  /**
   * Zero or more doc elements - see section 2.3 Documentation.
   * <p>
   * The doc element has mixed content and may contain text and zero or more
   * child elements that form the body of the documentation.
   */
  protected List<Doc> doc;
  /**
   * Zero or more param elements (see section 2.12 ) with a value of 'header'
   * for their style attribute, each of which specifies the details of a HTTP
   * header for the response
   */
  protected List<Param> param;
  /**
   * Zero or more representation elements (see section 2.11 ), each of which
   * describes a resource representation that may result from performing the
   * method. Sibling representation elements indicate logically equivalent
   * alternatives; normal HTTP content negotiation mechanisms may be used to
   * select a particular alternative.
   * <p>
   * A representation element describes a representation of a resource's state.
   * A representation element can either be a representation definition or a
   * reference to a representation defined elsewhere.
   */
  protected List<Representation> representation;
  /**
   * Optionally present on responses, provides a list of HTTP status codes
   * associated with a particular response.
   */
  @XmlAttribute(name = "status")
  protected List<Long> status;

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

  public List<Representation> getRepresentation() {
    if (representation == null) {
      representation = new ArrayList<>();
    }
    return this.representation;
  }

  public List<Long> getStatus() {
    if (status == null) {
      status = new ArrayList<>();
    }
    return this.status;
  }//</editor-fold>

}
