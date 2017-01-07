package net.java.dev.wadl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.*;
import javax.xml.namespace.QName;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "response")
@XmlRootElement(name = "response")
public class Response {

  protected List<Doc> doc;
  protected List<Param> param;
  protected List<Representation> representation;
  @XmlAnyElement(lax = true)
  protected List<Object> any;
  @XmlAttribute(name = "status")
  protected List<Long> status;
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

  public List<Representation> getRepresentation() {
    if (representation == null) {
      representation = new ArrayList<>();
    }
    return this.representation;
  }

  public List<Object> getAny() {
    if (any == null) {
      any = new ArrayList<>();
    }
    return this.any;
  }

  public List<Long> getStatus() {
    if (status == null) {
      status = new ArrayList<>();
    }
    return this.status;
  }

  public Map<QName, String> getOtherAttributes() {
    return otherAttributes;
  }

}
