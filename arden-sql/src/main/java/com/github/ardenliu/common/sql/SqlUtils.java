package com.github.ardenliu.common.sql;

import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;

public class SqlUtils {

    /**
     * Replace the schema place holder in SQL with schema value.
     * 
     * @param sqlTemplateString the SQL template string
     * @param schemaMap         Map.Key is schema place holder; Map.Value is schema value
     * @return the SQL string with schema
     */
    public static String insertSchemaMap(String sqlTemplateString, Map<String, String> schemaMap) {
        Map<String, String> schemaMapWithDot = schemaMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> getSchemaWithDot(e.getValue())));
        StringSubstitutor sub = new StringSubstitutor(schemaMapWithDot);
        return sub.replace(sqlTemplateString);
    }

    private static String getSchemaWithDot(String schema) {
        if (StringUtils.isBlank(schema)) {
            return "";
        } else {
            return schema + ".";
        }
    }
}