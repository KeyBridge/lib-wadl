package net.java.dev.wadl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.*;
import javax.xml.namespace.QName;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "option")
@XmlRootElement(name = "option")
public class Option {

  protected List<Doc> doc;
  @XmlAnyElement(lax = true)
  protected List<Object> any;
  @XmlAttribute(name = "value", required = true)
  protected String value;
  @XmlAttribute(name = "mediaType")
  protected String mediaType;
  @XmlAnyAttribute
  private final Map<QName, String> otherAttributes = new HashMap<>();

  public List<Doc> getDoc() {
    if (doc == null) {
      doc = new ArrayList<>();
    }
    return this.doc;
  }

  public List<Object> getAny() {
    if (any == null) {
      any = new ArrayList<>();
    }
    return this.any;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getMediaType() {
    return mediaType;
  }

  public void setMediaType(String value) {
    this.mediaType = value;
  }

  public Map<QName, String> getOtherAttributes() {
    return otherAttributes;
  }

}
