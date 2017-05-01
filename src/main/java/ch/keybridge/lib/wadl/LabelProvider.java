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

/**
 * A label provider.
 *
 * @author Key Bridge
 * @since 04/30/17 to support standard label discovery
 */
public interface LabelProvider {

  /**
   * Get a label corresponding to the provided key index. This is typically a
   * short or simple label.
   * <p>
   * Implementations MUST return a non-null value.
   *
   * @param key the key index
   * @return the corresponding label
   */
  String getLabel(String key);

  /**
   * Get an extended description. This is typically a paragraph description of a
   * API method.
   *
   * @param key the key index
   * @return the corresponding text description
   */
  String getDescription(String key);

}
