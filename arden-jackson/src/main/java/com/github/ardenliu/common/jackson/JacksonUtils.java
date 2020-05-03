package com.github.ardenliu.common.jackson;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtils {
    private static final Logger logger = LogManager.getLogger(JacksonUtils.class);

    /**
     * Create JSON String from one object
     * 
     * @param object Input Object
     * @return JSON string from input object
     */
    public static String objectToJsonString(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("Got JsonProcessingException", e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * Using input ObjectMapper to compares two JSON string, returning true if they are full (deep) value equality.
     * 
     * @param mapper
     * @param json1  the first JSON string
     * @param json2  the second JSON string
     * @return
     */
    public static boolean equals(ObjectMapper mapper, String json1, String json2) {
        try {
            return mapper.readTree(json1).equals(mapper.readTree(json2));
        } catch (JsonMappingException e) {
            logger.error("Got JsonMappingException", e);
            throw new RuntimeException(e.getMessage(), e);
        } catch (JsonProcessingException e) {
            logger.error("Got JsonProcessingException", e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * Compares two JSON string, returning true if they are full (deep) value equality.
     * 
     * @param json1 the first JSON string
     * @param json2 the second JSON string
     * @return
     */
    public static boolean equals(String json1, String json2) {
        ObjectMapper mapper = new ObjectMapper();
        return JacksonUtils.equals(mapper, json1, json2);
    }
}