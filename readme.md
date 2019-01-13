# WADL and Daub

A REST API Processing and Documentation Utility

![Waddle and Daub](doc/wattle-and-daub.png)

A Web Application Description Language (WADL)-based API processing and automated
documentation utility. This library includes an WADL processing resource to facilitate
automated documentation of RESTFUL web services.

The Web Application Description Language (WADL) is designed to provide a machine
readable description of HTTP-based Web applications. WADL is basically SOAP WSDL for REST.
The WADL specification is hosted at W3C, and the latest version was published
way back in 31 August 2009: [http://www.w3.org/Submission/wadl/](http://www.w3.org/Submission/wadl/)

A normative XML schema for the WADL vocabulary can be found at
[wadl.xsd](https://www.w3.org/Submission/wadl/wadl.xsd)

## About (and justification)

Java Jersey does not implements the bare minimum required WADL specification
components. Important components such as _documentation_ are completely omitted,
while other components such as query and response object description would be
useful.

Frameworks such as [Swagger](http://swagger.io) and [RAML](http://raml.org)
have filled this gap by inventing, from whole cloth, new API generation, consuming
and documentation strategies. We think these are useful but superfluous for our needs.
Jackson is just fine but needs a little decoration.

In this utility we use Jackson to provide a standard WADL file (the "wattle"). We
then enhance the WADL information with supplemental documentation (the "daub")
to provide a complete RESTful API autodocumentation resource. Simple and easy.

This library includes:

1. a complete implementation of the WADL object model


The application element forms the root of a WADL description and contains the following:

  * Zero or more doc elements
  * An optional grammars element
  * Zero or more resources elements
  * Zero or more of the following:
      * resource_type elements
      * method elements
      * representation elements
      * param elements

W3C Member Submission 31 August 2009
Latest version: http://www.w3.org/Submission/wadl/

**XML Schema for WADL**

A normative XML schema for the WADL vocabulary can be found at
https://www.w3.org/Submission/wadl/wadl.xsd

## Basic Usage (the "wattle")

_Update for version 1.0.0_

JSF components have been removed to make this a pure data processing library.
The following components are no longer included. If desired you can sill find them in 
previous git snapshots.

    ~~2. a JSF Composite Component to pretty-print WADL methods in HTML~~  
    ~~3. a JSF managed bean supporting the composite component~~


See the unit tests. Basically you can use this data model to easily
parse any `application.wadl` file published by the Jersey REST library.

```java
// Identify the WADL file
URL url = getClass().getClassLoader().getResource("wadl/application.wadl");
// Read the WADL file into a String 
String wadlFile = new String(Files.readAllBytes(Paths.get(url.toURI())));
// Unmarshal the WADL file content to an Application instance.
Application application = XmlUtil.unmarshal(wadlFile, Application.class);
// Its good practice to call postLoad() on the application. This sets up
// internal parent/child links for easier software object traversal
application.postLoad();
// Now you can examine the Application description
// For exampe: parse a REST resource 
String path = "rest-path";
Resource resource = application.findResource(path);
... .

```


## License = GPL 3.0

Copyright (C) 2017-2019 Key Bridge LLC

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.

