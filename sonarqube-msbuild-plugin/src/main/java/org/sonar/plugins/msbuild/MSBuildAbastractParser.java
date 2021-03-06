/*
 * Sonar MSBuild Plugin :: Squid
 * Copyright (C) 2015-2018 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.plugins.msbuild;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;


/**
 * Provides reusable code for Xml parsers.
 * 
 * Original from: https://github.com/SonarSource/sonar-xml
 * Modified to fit msbuild files
 */
public abstract class MSBuildAbastractParser {

  public static final Logger LOG = Loggers.get(MSBuildAbastractParser.class);
  protected static final SAXParserFactory SAX_FACTORY;

  /**
   * Build the SAXParserFactory.
   */
  static {

    SAX_FACTORY = SAXParserFactory.newInstance();

    try {
      SAX_FACTORY.setValidating(false);
      SAX_FACTORY.setNamespaceAware(false);
      SAX_FACTORY.setFeature("http://xml.org/sax/features/validation", false);
      SAX_FACTORY.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
      SAX_FACTORY.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
      SAX_FACTORY.setFeature("http://xml.org/sax/features/external-general-entities", false);
      SAX_FACTORY.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
    } catch (SAXNotRecognizedException | SAXNotSupportedException | ParserConfigurationException ex) {
      LOG.error("SAX_FACTORY error '{}'", ex.getMessage());
    }
  }

  protected SAXParser newSaxParser() throws SAXException, ParserConfigurationException {
      return SAX_FACTORY.newSAXParser();
  }
}