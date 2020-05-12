package com.github.ardenliu.common.jdom2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.junit.jupiter.api.Test;

class JdomUtilsTest {

    @Test
    void getDocumentByResource() {
        Document document = JdomUtils.getDocumentByResource("com/github/ardenliu/common/jdom2/test.xml");
        
        Element rootElement = document.getRootElement();
        
        assertEquals("test", rootElement.getName());
        assertEquals("ddd", rootElement.getAttribute("attr").getValue());
        assertNull(rootElement.getAttribute("NotHere"));
        
        List<Element> children = rootElement.getChildren("book");
        for (Element bookElement : children) {
            assertEquals("book", bookElement.getName());
            assertEquals("bookvalue", bookElement.getText());
        }
    }
    
    @Test
    void getDocumentByResource_bad() {
        Document document = JdomUtils.getDocumentByResource("com/github/ardenliu/common/jdom2/bad.xml");
        assertNull(document);
    }
    
    @Test
    void getDocumentByResource_namespace() {
        Document document = JdomUtils.getDocumentByResource("com/github/ardenliu/common/jdom2/testNameSpace.xml");

        Element rootElement = document.getRootElement();
        Namespace nsFromRootElement = rootElement.getNamespace();

        String namespaceURI = rootElement.getNamespaceURI();
        assertEquals("http://www.w3.org/TR/html4/", namespaceURI);
        
        assertEquals("h", rootElement.getNamespacePrefix());
        assertEquals("table", rootElement.getName());

        Element tdElement = rootElement.getChild("td", nsFromRootElement);
        assertNotNull(tdElement);

        assertEquals("Apples", tdElement.getText());
    }
}