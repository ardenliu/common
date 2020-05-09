package com.github.ardenliu.common.googlehttpclient;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.testcontainers.containers.MockServerContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

// Currently, we are using https://www.testcontainers.org/modules/mockserver
//
// more approach:
// https://developers.google.com/api-client-library/java/google-http-java-client/unit-testing
// https://thecarlhall.wordpress.com/2010/03/25/unit-testing-with-httpclients-localtestserver/

@Testcontainers
class GoogleHttpUtilsTest {

    @Test
    void getContentAsString() throws MalformedURLException {
        String actualVersion = MockServerClient.class.getPackage().getImplementationVersion();
        try (MockServerContainer mockServer = new MockServerContainer(actualVersion)) {
            mockServer.start();
            assertTrue(mockServer.isRunning());

            String protocol = "http";
            String host = mockServer.getContainerIpAddress();
            int port = mockServer.getServerPort();
            String path = "/test";
            URL url = new URL (protocol, host, port, path);
            
            String bodyContent = "Test Body Content";
            
            try (MockServerClient mockServerClient = new MockServerClient(host, port)) {

                mockServerClient.
                when(new HttpRequest().withPath(path)).
                respond(new HttpResponse().withBody(bodyContent));

                String contentFromWeb = GoogleHttpUtils.getContentAsString(url);
                assertEquals(bodyContent, contentFromWeb);
            }
        }
    }
    
    @Test
    void saveUrlIntoFile(@TempDir Path tempDir) throws IOException {
        String actualVersion = MockServerClient.class.getPackage().getImplementationVersion();
        try (MockServerContainer mockServer = new MockServerContainer(actualVersion)) {
            mockServer.start();
            assertTrue(mockServer.isRunning());

            String protocol = "http";
            String host = mockServer.getContainerIpAddress();
            int port = mockServer.getServerPort();
            String path = "/test";
            URL url = new URL (protocol, host, port, path);
            
            String bodyContent = "Test Body Content";
            
            try (MockServerClient mockServerClient = new MockServerClient(host, port)) {

                mockServerClient.
                when(new HttpRequest().withPath(path)).
                respond(new HttpResponse().withBody(bodyContent));

                Path targetFile = Paths.get(tempDir.toString(), "testfile.txt");
                
                GoogleHttpUtils.saveUrlIntoFile(url, targetFile);

                assertEquals(bodyContent, new String(Files.readAllBytes(targetFile), StandardCharsets.UTF_8));
            }
        }
    }
    
    @Test
    void getContentAsInputStream() throws IOException {
        String actualVersion = MockServerClient.class.getPackage().getImplementationVersion();
        try (MockServerContainer mockServer = new MockServerContainer(actualVersion)) {
            mockServer.start();
            assertTrue(mockServer.isRunning());

            String protocol = "http";
            String host = mockServer.getContainerIpAddress();
            int port = mockServer.getServerPort();
            String path = "/test";
            URL url = new URL (protocol, host, port, path);
            
            String bodyContent = "Test Body Content";
            
            try (MockServerClient mockServerClient = new MockServerClient(host, port)) {

                mockServerClient.
                when(new HttpRequest().withPath(path)).
                respond(new HttpResponse().withBody(bodyContent));

                InputStream inputStream = GoogleHttpUtils.getContentAsInputStream(url);
                
                String stringFromInputStream = IOUtils.toString(inputStream, StandardCharsets.UTF_8); 
                assertEquals(bodyContent, stringFromInputStream);
            }
        }
    }
}
