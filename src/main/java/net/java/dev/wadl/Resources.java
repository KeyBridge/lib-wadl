package net.java.dev.wadl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.*;
import javax.xml.namespace.QName;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resources")
@XmlRootElement(name = "resources")
public class Resources {

  protected List<Doc> doc;
  @XmlElement(required = true)
  protected List<Resource> resource;
  @XmlAnyElement(lax = true)
  protected List<Object> any;
  @XmlAttribute(name = "base")
  @XmlSchemaType(name = "anyURI")
  protected String base;
  @XmlAnyAttribute
  private final Map<QName, String> otherAttributes = new HashMap<>();

  public List<Doc> getDoc() {
    if (doc == null) {
      doc = new ArrayList<>();
    }
    return this.doc;
  }

  public List<Resource> getResource() {
    if (resource == null) {
      resource = new ArrayList<>();
    }
    return this.resource;
  }

  public List<Object> getAny() {
    if (any == null) {
      any = new ArrayList<>();
    }
    return this.any;
  }

  public String getBase() {
    return base;
  }

  public void setBase(String value) {
    this.base = value;
  }

  public Map<QName, String> getOtherAttributes() {
    return otherAttributes;
  }

}
