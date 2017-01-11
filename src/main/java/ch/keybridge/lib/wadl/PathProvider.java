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
package ch.keybridge.lib.wadl;

import java.util.List;
import net.java.dev.wadl.Param;

/**
 * Interface for a class that provides a path fragment.
 * <p>
 * This applies to the {@code Resources} class that provides a base path, plus
 * the {@code Resource} path that provides a path fragment.
 *
 * @author Key Bridge LLC
 */
public interface PathProvider {

  /**
   * Get the path component. This may be a base path or path fragment.
   *
   * @return the path component.
   */
  public String getPath();

  /**
   * Recursively build a path by appending the parents
   *
   * @return a path configuration.
   */
  public String buildPath();

  /**
   * Get a list of parameters;
   *
   * @return a list of parameters.
   */
  public List<Param> getParam();
}
