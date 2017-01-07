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
@XmlType(name = "resource_type")
@XmlRootElement(name = "resource_type")
public class ResourceType {

  protected List<Doc> doc;
  protected List<Param> param;
  @XmlElements({
    @XmlElement(name = "method", type = Method.class),
    @XmlElement(name = "resource", type = Resource.class)
  })
  protected List<Object> methodOrResource;
  @XmlAnyElement(lax = true)
  protected List<Object> any;
  @XmlAttribute(name = "id")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  @XmlSchemaType(name = "ID")
  protected String id;
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

  public List<Object> getMethodOrResource() {
    if (methodOrResource == null) {
      methodOrResource = new ArrayList<>();
    }
    return this.methodOrResource;
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

  public Map<QName, String> getOtherAttributes() {
    return otherAttributes;
  }

}
