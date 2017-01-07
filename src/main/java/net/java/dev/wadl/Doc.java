package net.java.dev.wadl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.*;
import javax.xml.namespace.QName;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "doc")
@XmlRootElement(name = "doc")
public class Doc {

  @XmlMixed
  @XmlAnyElement(lax = true)
  protected List<Object> content;
  @XmlAttribute(name = "title")
  protected String title;
  @XmlAttribute(name = "lang", namespace = "http://www.w3.org/XML/1998/namespace")
  protected String lang;
  @XmlAnyAttribute
  private final Map<QName, String> otherAttributes = new HashMap<>();

  public List<Object> getContent() {
    if (content == null) {
      content = new ArrayList<>();
    }
    return this.content;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String value) {
    this.title = value;
  }

  public String getLang() {
    return lang;
  }

  public void setLang(String value) {
    this.lang = value;
  }

  public Map<QName, String> getOtherAttributes() {
    return otherAttributes;
  }

}
