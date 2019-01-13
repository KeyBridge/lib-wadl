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
package net.java.dev.wadl;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author Key Bridge LLC
 */
public class MethodTest {

  public MethodTest() {
  }

  @Test
  public void testMethod() throws Exception {
    URL url = getClass().getClassLoader().getResource("wadl/application.wadl");
//    URL url = getClass().getClassLoader().getResource("wadl/amazonsearch.wadl");
    String wadlFile = new String(Files.readAllBytes(Paths.get(url.toURI())));
    Application application = XmlUtil.unmarshal(wadlFile, Application.class);
    application.postLoad();
    assert application != null;
    System.out.println("Unmarshal WADL XML to Application Entity OK");

    for (Method method : findMethods(application, "elevation")) {
      System.out.println("method " + method + " " + method.getPath() + " " + method.getDoc());

    }

  }

  /**
   * Find all methods identified in the {@code Application} instance belonging
   * to the indicated Resource, which is identified by its Path.
   * <p>
   * This is commonly used to find a list of (lowest level) Method entries when
   * displaying a page detail.
   *
   * @param path the Resource path
   * @return a non-null ArrayList.
   */
  public List<Method> findMethods(Application application, String path) {
    /**
     * Failsafe in case the application did not load.
     */
    if (application == null) {
      return new ArrayList<>();
    }

    /**
     * Initialize and recursively populate the methods array. Recurse if the
     * found resource is not null.
     */
    ArrayList methods = new ArrayList();
    Resource resource = application.findResource(path);
    if (resource != null) {
      methods.addAll(findMethodsRecursive(resource));
    }
    /**
     * Sort the array in alphabetical order.
     */
    Collections.sort(methods);
    return methods;
  }

  /**
   * Recursively a {@code Resource} instance to identify and extract all
   * methods.
   *
   * @param resource a {@code Resource} instance
   * @return a list of all methods in the Resource tree
   */
  private List<Method> findMethodsRecursive(Resource resource) {
    List<Method> tempMethods = new ArrayList<>(resource.getMethods());
    resource.getResources().stream().forEach((res) -> {
      tempMethods.addAll(findMethodsRecursive(res));
    });
    return tempMethods;
  }
}
