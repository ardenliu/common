package com.github.ardenliu.common.sql;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;

public class SqlUtils {

    /**
     * Replace the schema place holder in SQL with schema value.
     * 
     * @param sqlTemplateString the SQL template string 
     * @param schema the name of database schema
     * @return the SQL string with schema
     */
    public static String insertSchema(String sqlTemplateString, String schema) {

        Map<String, String> valuesMap = new HashMap<String, String>();
        if (StringUtils.isBlank(schema)) {
            valuesMap.put("schema", "");
        } else {
            valuesMap.put("schema", schema + ".");
        }
        StringSubstitutor sub = new StringSubstitutor(valuesMap);

        return sub.replace(sqlTemplateString);
    }
}