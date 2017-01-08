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

@XmlType(name = "ParamStyle")
@XmlEnum
public enum ParamStyle {

  @XmlEnumValue("plain")
  PLAIN("plain"),
  @XmlEnumValue("query")
  QUERY("query"),
  @XmlEnumValue("matrix")
  MATRIX("matrix"),
  @XmlEnumValue("header")
  HEADER("header"),
  @XmlEnumValue("template")
  TEMPLATE("template");
  private final String value;

  ParamStyle(String v) {
    value = v;
  }

  public String getValue() {
    return value;
  }

  public static ParamStyle fromValue(String v) {
    for (ParamStyle c : ParamStyle.values()) {
      if (c.value.equals(v)) {
        return c;
      }
    }
    throw new IllegalArgumentException(v);
  }

}
