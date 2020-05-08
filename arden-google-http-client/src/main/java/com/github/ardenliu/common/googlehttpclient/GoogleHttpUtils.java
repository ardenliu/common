package com.github.ardenliu.common.googlehttpclient;

import java.io.IOException;
import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    
    public static InputStream getContentAsInputStream(GenericUrl genericUrl) {
        return GoogleHttpUtils.getContentAsInputStream(genericUrl, 0);
    }

    public static InputStream getContentAsInputStream(GenericUrl genericUrl, int timeout) {
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

    public static String getContentAsString(GenericUrl genericUrl) {
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
}