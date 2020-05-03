package com.github.ardenliu.common.jackson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class JacksonUtilsTest {
    @Test
    void objectToJsonString() {
        Map<String, String> testMap = new HashMap<String,String>();
        testMap.put("city", "new york");
        String jsonString = JacksonUtils.objectToJsonString(testMap);

        assertEquals("{\"city\":\"new york\"}", jsonString);
    }
    
    @Test
    void equals() throws IOException, URISyntaxException {

        String json1 = new String(Files.readAllBytes(Paths.get(JacksonUtilsTest.class.getClassLoader().getResource("com/github/ardenliu/common/jackson/test1.json").toURI())));
        String json2 = new String(Files.readAllBytes(Paths.get(JacksonUtilsTest.class.getClassLoader().getResource("com/github/ardenliu/common/jackson/test2.json").toURI())));
        assertTrue(JacksonUtils.equals(json1, json2));
    }
}