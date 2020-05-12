package com.github.ardenliu.common.jdom2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

public class JdomUtils {
    private static final Logger logger = LogManager.getLogger(JdomUtils.class);

    public static Document getDocumentByResource(String name) {
        if (StringUtils.isEmpty(name)) {
            logger.debug("name is empty.");
            return null;
        }

        logger.debug("name[{}]", name);
        try {
            ClassPathResource classPathResource = new ClassPathResource(name);
            SAXBuilder saxBuilder = new SAXBuilder();
            return (Document) saxBuilder.build(classPathResource.getInputStream());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}