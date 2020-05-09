package com.github.ardenliu.common.googlehttpclient;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.io.FileUtils;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;

//TODO: for JUNIT:
//https://developers.google.com/api-client-library/java/google-http-java-client/unit-testing
//https://www.testcontainers.org/modules/mockserver/
//https://thecarlhall.wordpress.com/2010/03/25/unit-testing-with-httpclients-localtestserver/

public class GoogleHttpUtils {
    private static final Logger logger = LogManager.getLogger(GoogleHttpUtils.class);

    private GoogleHttpUtils() {
    }

    public static InputStream getContentAsInputStream(String urlString) {
        return GoogleHttpUtils.getContentAsInputStream(new GenericUrl(urlString));
    }

    public static InputStream getContentAsInputStream(URL url) {
        return GoogleHttpUtils.getContentAsInputStream(new GenericUrl(url));
    }

    private static InputStream getContentAsInputStream(GenericUrl genericUrl) {
        return GoogleHttpUtils.getContentAsInputStream(genericUrl, 0);
    }

    private static InputStream getContentAsInputStream(GenericUrl genericUrl, int timeout) {
        // TODO: handle local file
//      String protocol = genericUrl.toURL().getProtocol();
//      if ("file".equals(protocol)) {
//          
//      }
//      
        try {
            HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();

            HttpRequest request = requestFactory.buildGetRequest(genericUrl);
            request.setConnectTimeout(timeout);
            request.setReadTimeout(timeout);

            HttpResponse response = request.execute();
            return response.getContent();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static String getContentAsString(URL url) {
        return GoogleHttpUtils.getContentAsString(new GenericUrl(url));
    }

    public static String getContentAsString(String urlString) {
        return GoogleHttpUtils.getContentAsString(new GenericUrl(urlString));
    }

    private static String getContentAsString(GenericUrl genericUrl) {
        try {
            HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
            HttpRequest request = requestFactory.buildGetRequest(genericUrl);
            HttpResponse response = request.execute();
            return response.parseAsString();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static void saveUrlIntoFile(URL url, Path targetFilePath) {
        GoogleHttpUtils.saveUrlIntoFile(new GenericUrl(url), targetFilePath.toString());
    }

    public static void saveUrlIntoFile(String url, String targetFilePath) {
        GoogleHttpUtils.saveUrlIntoFile(new GenericUrl(url), targetFilePath);
    }

    private static void saveUrlIntoFile(GenericUrl genericUrl, String targetFilePath) {
        try (InputStream inputStreamFromUrl = GoogleHttpUtils.getContentAsInputStream(genericUrl)) {
            File targetFile = new File(targetFilePath);
            FileUtils.copyInputStreamToFile(inputStreamFromUrl, targetFile);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}