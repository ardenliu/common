package com.github.ardenliu.common.jackson;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}