package com.github.ardenliu.common.lang.time;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class TimeUtils {

    /**
     * get current LocalDateTime by Zone Id
     * 
     * @param zoneId Selected Time Zone Id
     * @return Current LocalDataTime of selected Time Zone
     */
    public static LocalDateTime getLocalDateTimeByZoneId(ZoneId zoneId) {
        return OffsetDateTime.now(zoneId).toLocalDateTime();
    }

    /**
     * get current UTC LocalDataTime
     * 
     * @return Current UTC LocalDataTime
     */
    public static LocalDateTime getUtcLocalDateTime() {
        return TimeUtils.getLocalDateTimeByZoneId(ZoneOffset.UTC);
    }
}