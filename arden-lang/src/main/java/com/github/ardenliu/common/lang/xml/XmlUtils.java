package com.github.ardenliu.common.lang.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;

public class XmlUtils {
    private static final Logger logger = LogManager.getLogger(XmlUtils.class);

    public static Document getDocumentByResource(String name) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            return documentBuilder.parse(XmlUtils.class.getClassLoader().getResourceAsStream(name));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}