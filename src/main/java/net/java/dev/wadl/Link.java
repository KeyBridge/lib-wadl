package net.java.dev.wadl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "link")
@XmlRootElement(name = "link")
public class Link {

  protected List<Doc> doc;
  @XmlAnyElement(lax = true)
  protected List<Object> any;
  @XmlAttribute(name = "resource_type")
  @XmlSchemaType(name = "anyURI")
  protected String resourceType;
  @XmlAttribute(name = "rel")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected String rel;
  @XmlAttribute(name = "rev")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected String rev;
  @XmlAnyAttribute
  private final Map<QName, String> otherAttributes = new HashMap<>();

  public List<Doc> getDoc() {
    return doc;
  }

  public void setDoc(List<Doc> doc) {
    this.doc = doc;
  }

  public List<Object> getAny() {
    return any;
  }

  public void setAny(List<Object> any) {
    this.any = any;
  }

  public String getResourceType() {
    return resourceType;
  }

  public void setResourceType(String resourceType) {
    this.resourceType = resourceType;
  }

  public String getRel() {
    return rel;
  }

  public void setRel(String rel) {
    this.rel = rel;
  }

  public String getRev() {
    return rev;
  }

  public void setRev(String rev) {
    this.rev = rev;
  }

  public Map<QName, String> getOtherAttributes() {
    return otherAttributes;
  }

}
