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
