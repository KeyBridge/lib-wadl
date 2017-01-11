/* 
 * Copyright (C) 2017 Key Bridge LLC
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.java.dev.wadl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.*;

/**
 * 2.12.3 Option
 * <p>
 * An option element defines one of a set of possible values for the parameter
 * represented by its parent param element.
 *
 * @author Key Bridge LLC
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "option")
@XmlRootElement(name = "option")
public class Option {

  /**
   * An option element may have zero or more doc elements that document the
   * meaning of the value.
   */
  protected List<Doc> doc;
  /**
   * A required attribute that defines one of the possible values of the parent
   * parameter.
   */
  @XmlAttribute(name = "value", required = true)
  protected String value;
  /**
   * When present this indicates that the parent parameter acts as a media type
   * selector for responses. The value of the attribute is the media type that
   * is expected when the parameter has the value given in the value attribute.
   */
  @XmlAttribute(name = "mediaType")
  protected String mediaType;

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  public List<Doc> getDoc() {
    if (doc == null) {
      doc = new ArrayList<>();
    }
    return this.doc;
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
  }//</editor-fold>

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 29 * hash + Objects.hashCode(this.value);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Option other = (Option) obj;
    return Objects.equals(this.value, other.value);
  }

  @Override
  public String toString() {
    return value;
  }

}
