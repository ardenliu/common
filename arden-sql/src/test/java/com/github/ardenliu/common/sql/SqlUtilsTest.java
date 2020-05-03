package com.github.ardenliu.common.sql;

import static org.junit.jupiter.api.Assertions.*;

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
}