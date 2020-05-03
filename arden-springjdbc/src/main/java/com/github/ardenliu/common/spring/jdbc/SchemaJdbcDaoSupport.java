package com.github.ardenliu.common.spring.jdbc;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.github.ardenliu.common.sql.SqlUtils;
import com.github.ardenliu.common.sql.api.DatabaseSchemaAware;

public abstract class SchemaJdbcDaoSupport extends JdbcDaoSupport implements DatabaseSchemaAware {

    private String schemaName;

    @Override
    public String getSchemaName() {
        return this.schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    @Override
    public String convertSql(String sql) {
        return SqlUtils.insertSchema(sql, getSchemaName());
    }
}