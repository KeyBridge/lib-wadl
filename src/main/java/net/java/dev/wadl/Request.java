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
 * 2.9 Request
 * <p>
 * A request element describes the input to be included when applying an HTTP
 * method to a resource.
 *
 * @author Key Bridge LLC
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "request")
@XmlRootElement(name = "request")
public class Request {

  /**
   * Zero or more doc elements - see section 2.3 Documentation.
   * <p>
   * The doc element has mixed content and may contain text and zero or more
   * child elements that form the body of the documentation.
   */
  protected List<Doc> doc;
  /**
   * Zero or more param elements (see section 2.12) with one of the following
   * values for their style attribute:
   * <dl>
   * <dt><code>query</code></dt>
   * <dd>Specifies a URI query parameter for all methods that apply to this
   * resource, see section <a href="#x3-190002.9.1">2.9.1</a></dd>
   * <dt><code>header</code></dt>
   * <dd>Specifies a HTTP header for use in the request</dd>
   * </dl>
   * Child param elements (see section 2.12 ) of a resource or request with a
   * style value of 'query' represent URI query parameters as described in
   * section 17.13 of HTML 4.01[3]. The runtime values of query parameters are
   * sent as URI query parameters when the HTTP method is invoked.
   */
  protected List<Param> param;
  /**
   * Zero or more representation elements - see section 2.11.
   * <p>
   * Note that use of representation elements is confined to HTTP methods that
   * accept an entity body in the request (e.g., PUT or POST). Sibling
   * representation elements represent logically equivalent alternatives, e.g.,
   * a particular resource might support multiple XML grammars for a particular
   * request.
   */
  protected List<Representation> representation;

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

}
