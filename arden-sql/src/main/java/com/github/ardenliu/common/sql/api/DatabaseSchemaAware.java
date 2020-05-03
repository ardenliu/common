package com.github.ardenliu.common.sql.api;

import java.util.Map;

public interface DatabaseSchemaAware {
    Map<String, String> getSchemaMap();

    String convertSql(String sql);
}