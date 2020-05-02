package com.github.ardenliu.common.sql.api;

public interface DatabaseSchemaAware {
    String getSchemaName();

    String convertSql(String sql);
}