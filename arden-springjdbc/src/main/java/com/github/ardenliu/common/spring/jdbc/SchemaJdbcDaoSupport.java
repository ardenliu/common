package com.github.ardenliu.common.spring.jdbc;

import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.github.ardenliu.common.sql.SqlUtils;
import com.github.ardenliu.common.sql.api.DatabaseSchemaAware;

public abstract class SchemaJdbcDaoSupport extends JdbcDaoSupport implements DatabaseSchemaAware {

    private Map<String, String> schemaMap;

    @Override
    public Map<String, String> getSchemaMap() {
        return this.schemaMap;
    }

    public void setSchemaName(Map<String, String> schemaMap) {
        this.schemaMap = schemaMap;
    }

    @Override
    public String convertSql(String sql) {
        return SqlUtils.insertSchemaMap(sql, getSchemaMap());
    }
}