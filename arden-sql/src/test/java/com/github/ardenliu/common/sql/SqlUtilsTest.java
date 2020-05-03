package com.github.ardenliu.common.sql;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class SqlUtilsTest {

    @Test
    void insertSchema() {
        String sqlTemplateString = "select * from ${schema}table_a where id = ?";

        assertEquals("select * from test.table_a where id = ?", SqlUtils.insertSchema(sqlTemplateString, "test"));
        assertEquals("select * from table_a where id = ?", SqlUtils.insertSchema(sqlTemplateString, "  "));
        assertEquals("select * from table_a where id = ?", SqlUtils.insertSchema(sqlTemplateString, ""));
        assertEquals("select * from table_a where id = ?", SqlUtils.insertSchema(sqlTemplateString, null));
    }

    @Test
    void insertSchemaMap() {
        String sqlTemplateString = "select * from ${schema0}table_a a, ${schema1}table_b b where a.id = b.id";

        Map<String, String> schemaMap = Stream.of(new String[][] { { "schema0", "test0" }, { "schema1", "test1" }, }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

        assertEquals("select * from test0.table_a a, test1.table_b b where a.id = b.id", SqlUtils.insertSchemaMap(sqlTemplateString, schemaMap));

        schemaMap = new HashMap<String, String>();
        schemaMap.put("schema0", "test0");
        schemaMap.put("schema1", null);

        assertEquals("select * from test0.table_a a, table_b b where a.id = b.id", SqlUtils.insertSchemaMap(sqlTemplateString, schemaMap));
    }
}