package com.github.ardenliu.common.lang.xml;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlUtils {
    private static final Logger logger = LogManager.getLogger(XmlUtils.class);

    public static Document getDocumentByResource(String name) {
        return XmlUtils.getDocumentByResource(name, false);
    }

    public static Document getNsDocumentByResource(String name) {
        return XmlUtils.getDocumentByResource(name, true);
    }

    public static Document getDocumentByResource(String name, boolean namespaceAware) {
        return getDocumentByInputStream(XmlUtils.class.getClassLoader().getResourceAsStream(name), namespaceAware);
    }

    public static Document getDocumentByInputStream(InputStream inputStream) {
        return XmlUtils.getDocumentByInputStream(inputStream, false);
    }

    public static Document getNsDocumentByInputStream(InputStream inputStream) {
        return XmlUtils.getDocumentByInputStream(inputStream, true);
    }

    public static Document getDocumentByInputStream(InputStream inputStream, boolean namespaceAware) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setNamespaceAware(namespaceAware);

            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            return documentBuilder.parse(inputStream);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static Element getNsRootElementByInputStream(InputStream inputStream) {
        Document document = getDocumentByInputStream(inputStream, true);
        return document.getDocumentElement();
    }
}