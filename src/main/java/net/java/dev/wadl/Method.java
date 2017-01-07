package net.java.dev.wadl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "method")
@XmlRootElement(name = "method")
public class Method {

  protected List<Doc> doc;
  protected Request request;
  protected List<Response> response;
  @XmlAnyElement(lax = true)
  protected List<Object> any;
  @XmlAttribute(name = "id")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  @XmlSchemaType(name = "ID")
  protected String id;
  @XmlAttribute(name = "name")
  protected String name;
  @XmlAttribute(name = "href")
  @XmlSchemaType(name = "anyURI")
  protected String href;
  @XmlAnyAttribute
  private final Map<QName, String> otherAttributes = new HashMap<>();

  public List<Doc> getDoc() {
    if (doc == null) {
      doc = new ArrayList<>();
    }
    return this.doc;
  }

  public Request getRequest() {
    return request;
  }

  public void setRequest(Request value) {
    this.request = value;
  }

  public List<Response> getResponse() {
    if (response == null) {
      response = new ArrayList<>();
    }
    return this.response;
  }

  public List<Object> getAny() {
    if (any == null) {
      any = new ArrayList<>();
    }
    return this.any;
  }

  public String getId() {
    return id;
  }

  public void setId(String value) {
    this.id = value;
  }

  public String getName() {
    return name;
  }

  public void setName(String value) {
    this.name = value;
  }

  public String getHref() {
    return href;
  }

  public void setHref(String value) {
    this.href = value;
  }

  public Map<QName, String> getOtherAttributes() {
    return otherAttributes;
  }

}
