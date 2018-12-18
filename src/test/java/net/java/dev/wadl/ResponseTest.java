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

import ch.keybridge.lib.xml.JaxbUtility;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import org.junit.Test;

/**
 *
 * @author Key Bridge LLC
 */
public class ResponseTest {

  @Test
  public void testResponse() throws JAXBException {
    Response response = new Response();
    Doc doc = new Doc();
    doc.setTitle("Response documentation Title");
    doc.getContent().add("Response documentation long form description");
    response.getDoc().add(doc);

    response.getStatus().add(200l);
    response.getStatus().add(201l);
    response.getStatus().add(202l);

    Representation representationXml = new Representation();
    representationXml.setElement(new QName("GISFeature"));
    representationXml.setMediaType(MediaType.APPLICATION_XML);
    response.getRepresentation().add(representationXml);

    Representation representationJson = new Representation();
    representationXml.setElement(new QName("GISFeature"));
    representationJson.setMediaType(MediaType.APPLICATION_JSON);
    response.getRepresentation().add(representationJson);

    System.out.println("Response Build");

    System.out.println(JaxbUtility.marshal(response));

  }

}
