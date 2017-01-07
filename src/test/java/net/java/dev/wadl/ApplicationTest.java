/*
 * Copyright 2017 Key Bridge LLC. All rights reserved.
 * Use is subject to license terms.
 *
 * Software Code is protected by Copyrights. Author hereby reserves all rights
 * in and to Copyrights and no license is granted under Copyrights in this
 * Software License Agreement.
 *
 * Key Bridge LLC generally licenses Copyrights for commercialization pursuant to
 * the terms of either a Standard Software Source Code License Agreement or a
 * Standard Product License Agreement. A copy of either Agreement can be
 * obtained upon request from: info@keybridgewireless.com
 */
package net.java.dev.wadl;

import ch.keybridge.lib.xml.JaxbUtility;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.xml.bind.JAXBException;
import org.junit.Test;

/**
 *
 * @author Key Bridge LLC
 */
public class ApplicationTest {

  @Test
  public void parseWADL() throws IOException, URISyntaxException, JAXBException {
    /**
     * Read the application.wadl file into a String.
     */
    URL url = getClass().getClassLoader().getResource("wadl/application.wadl");
    String wadlFile = new String(Files.readAllBytes(Paths.get(url.toURI())));
    /**
     * Unmarshal the string to an Application.
     */
    Application application = JaxbUtility.unmarshal(wadlFile, Application.class);
    assert application != null;
    System.out.println("Unmarshal WADL XML to Application Entity OK");
    /**
     * Marshal the Application to a string.
     */
    String applicationXml = JaxbUtility.marshal(application);
    assert applicationXml != null;

    System.out.println("Marshal Application Entity to WADL XML OK");

  }

}
