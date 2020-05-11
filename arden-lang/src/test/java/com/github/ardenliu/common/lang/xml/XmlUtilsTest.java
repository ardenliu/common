package com.github.ardenliu.common.lang.xml;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class XmlUtilsTest {

    @Test
    void getDocumentByResource() {
        Document document = XmlUtils.getDocumentByResource("com/github/ardenliu/common/lang/xml/test.xml");

        Element rootElement = document.getDocumentElement();
        rootElement.normalize();
        
        assertEquals("test", rootElement.getNodeName());
        assertEquals("ddd", rootElement.getAttribute("attr"));
        assertEquals("", rootElement.getAttribute("NotHere"));
        
        NodeList nodeList = rootElement.getElementsByTagName("book");
        for (int ii = 0; ii < nodeList.getLength(); ii++) {
            Node node = nodeList.item(ii);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element bookElement = (Element) node;
                assertEquals("book", bookElement.getNodeName());
                assertEquals("bookvalue", bookElement.getTextContent());
            }
        }
    }

    @Test
    void getDocumentByResource_bad() {
        Document document = XmlUtils.getDocumentByResource("com/github/ardenliu/common/lang/xml/bad.xml");
        assertNull(document);
    }
    
    @Test
    void getDocumentByResource_namespace() {
        Document document = XmlUtils.getNsDocumentByResource("com/github/ardenliu/common/lang/xml/testNameSpace.xml");
        
        Element rootElement = document.getDocumentElement();
        rootElement.normalize();

        String namespaceURI = rootElement.getNamespaceURI();
        assertEquals("http://www.w3.org/TR/html4/", namespaceURI);
        
        assertEquals("h:table", rootElement.getNodeName());
        assertEquals("table", rootElement.getLocalName());
     
        NodeList nodeList = rootElement.getElementsByTagNameNS("http://www.w3.org/TR/html4/", "td");
        assertEquals(1, nodeList.getLength());
        
        Node node = nodeList.item(0);
        assertEquals(Node.ELEMENT_NODE, node.getNodeType());
        Element tdElement = (Element) node;
        assertEquals("Apples", tdElement.getTextContent());
    }
}
