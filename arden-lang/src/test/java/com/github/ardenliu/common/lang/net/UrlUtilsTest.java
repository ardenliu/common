package com.github.ardenliu.common.lang.net;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URL;

import org.junit.jupiter.api.Test;

class UrlUtilsTest {

    @Test
    void getUrl() {
        URL url = UrlUtils.getUrl("http://www.google.com:80/test");
        
        assertEquals("www.google.com", url.getHost());
        assertEquals(80, url.getPort());
        assertEquals("http", url.getProtocol());
        assertEquals("/test", url.getPath());
    }

    @Test
    void getUrl_wrongvalue() {
        URL url = UrlUtils.getUrl(null);
        assertNull(url);

        //bad port
        url = UrlUtils.getUrl("http://www.google.com:abc/test");
        assertNull(url);
    }
}
