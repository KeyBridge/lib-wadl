/*
 * Copyright 2017 Key Bridge. All rights reserved.
 * Use is subject to license terms.
 *
 * Software Code is protected by Copyrights. Author hereby reserves all rights
 * in and to Copyrights and no license is granted under Copyrights in this
 * Software License Agreement.
 *
 * Key Bridge generally licenses Copyrights for commercialization pursuant to
 * the terms of either a Standard Software Source Code License Agreement or a
 * Standard Product License Agreement. A copy of either Agreement can be
 * obtained upon request from: info@keybridgewireless.com
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
