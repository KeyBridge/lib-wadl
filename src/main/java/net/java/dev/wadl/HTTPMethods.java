package net.java.dev.wadl;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "HTTPMethods")
@XmlEnum
public enum HTTPMethods {

  GET,
  POST,
  PUT,
  HEAD,
  DELETE;

}
