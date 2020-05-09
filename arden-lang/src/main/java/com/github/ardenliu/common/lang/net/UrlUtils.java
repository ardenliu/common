package com.github.ardenliu.common.lang.net;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UrlUtils {
    private static final Logger logger = LogManager.getLogger(UrlUtils.class);

    public static URL getUrl(String urlString) {
        URL url = null;
        try {
            if (!StringUtils.isEmpty(urlString)) {
                return new URL(urlString);
            } else {
                logger.info("urlString is empty");
            }
        } catch (MalformedURLException exception) {
            logger.info("got MalformedURLException. urlString[" + urlString + "] is not valid.");
        }
        return url;
    }
}