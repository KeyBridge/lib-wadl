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

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Table 1: Values of style attribute and context for use.
 * <p>
 * Indicates the parameter style, table 1 on page 25 lists the allowed values
 * and shows the context(s) in which each value may be used.
 *
 * @author Key Bridge LLC
 */
@XmlType(name = "ParamStyle")
@XmlEnum
public enum ParamStyle {
  /**
   * Parent: representation.
   * <p>
   * Specifies a component of the representation formatted as a string encoding
   * of the parameter value according to the rules of the media type.
   */
  @XmlEnumValue("plain")
  PLAIN("plain"),
  /**
   * Parent: representation.
   * <p>
   * Specifies a component of the representation as a name value pair formatted
   * according to the rules of the media type. Typically used with media type
   * 'application/x-www-form-urlencoded' or 'multipart/form-data'.
   */
  @XmlEnumValue("query")
  QUERY("query"),
  /**
   * Parent: resource.
   * <p>
   * Specifies a matrix URI component.
   */
  @XmlEnumValue("matrix")
  MATRIX("matrix"),
  /**
   * Parent: resource, resource_type, request or response.
   * <p>
   * Specifies a HTTP header that pertains to the HTTP request (resource or
   * request) or HTTP response (response)
   */
  @XmlEnumValue("header")
  HEADER("header"),
  /**
   * Parent: resource.
   * <p>
   * The parameter is represented as a string encoding of the parameter value
   * and is substituted into the value of the path attribute of the resource
   * element as described in section 2.6.1.
   */
  @XmlEnumValue("template")
  TEMPLATE("template");

  /**
   * The XML text value
   */
  private final String value;

  ParamStyle(String v) {
    value = v;
  }

  /**
   * Get the XML text value.
   *
   * @return the XML text value.
   */
  public String getValue() {
    return value;
  }

  /**
   * Get an enumerated instance from its XML text value.
   *
   * @param v the XML text value.
   * @return the corresponding enumerated instance
   */
  public static ParamStyle fromValue(String v) {
    for (ParamStyle c : ParamStyle.values()) {
      if (c.value.equals(v)) {
        return c;
      }
    }
    throw new IllegalArgumentException(v);
  }

  @Override
  public String toString() {
    return value;
  }

}
