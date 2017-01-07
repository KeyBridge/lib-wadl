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
@XmlType(name = "representation")
@XmlRootElement(name = "representation")
public class Representation {

  protected List<Doc> doc;
  protected List<Param> param;
  @XmlAnyElement(lax = true)
  protected List<Object> any;
  @XmlAttribute(name = "id")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  @XmlSchemaType(name = "ID")
  protected String id;
  @XmlAttribute(name = "element")
  protected QName element;
  @XmlAttribute(name = "mediaType")
  protected String mediaType;
  @XmlAttribute(name = "href")
  @XmlSchemaType(name = "anyURI")
  protected String href;
  @XmlAttribute(name = "profile")
  protected List<String> profile;
  @XmlAnyAttribute
  private final Map<QName, String> otherAttributes = new HashMap<>();

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
  }

  public Map<QName, String> getOtherAttributes() {
    return otherAttributes;
  }

}
