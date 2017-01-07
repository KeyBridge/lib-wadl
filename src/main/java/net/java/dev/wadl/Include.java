package net.java.dev.wadl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.*;
import javax.xml.namespace.QName;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "include")
@XmlRootElement(name = "include")
public class Include {

  protected List<Doc> doc;
  @XmlAttribute(name = "href")
  @XmlSchemaType(name = "anyURI")
  protected String href;
  @XmlAnyAttribute
  private final Map<QName, String> otherAttributes = new HashMap<>();

  public List<Doc> getDoc() {
    return doc;
  }

  public void setDoc(List<Doc> doc) {
    this.doc = doc;
  }

  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public Map<QName, String> getOtherAttributes() {
    return otherAttributes;
  }

}
