package net.java.dev.wadl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "grammars")
@XmlRootElement(name = "grammars")
public class Grammars {

  protected List<Doc> doc;
  protected List<Include> include;
  @XmlAnyElement(lax = true)
  protected List<Object> any;

  public List<Doc> getDoc() {
    if (doc == null) {
      doc = new ArrayList<>();
    }
    return this.doc;
  }

  public List<Include> getInclude() {
    if (include == null) {
      include = new ArrayList<>();
    }
    return this.include;
  }

  public List<Object> getAny() {
    if (any == null) {
      any = new ArrayList<>();
    }
    return this.any;
  }

}
