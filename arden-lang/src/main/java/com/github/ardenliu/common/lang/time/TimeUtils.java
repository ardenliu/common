package com.github.ardenliu.common.lang.time;

import java.time.LocalDateTime;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TimeUtils {
    private static final Logger logger = LogManager.getLogger(TimeUtils.class);

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

    /**
     * <p>
     * Gets the month-of-year field from 1 to 12.
     * </p>
     * <p>
     * This is a <code>null</code> safe version of :
     * </p>
     * 
     * <pre>
     * monthDay.getMonthValue()
     * </pre>
     *
     * @param monthDay
     * @return the month-of-year(from 1 to 12) or null (if input monthDay is null)
     */
    public static Integer getMonthValue(MonthDay monthDay) {
        if (monthDay == null) {
            logger.warn("monthDay is null");
            return null;
        } else {
            return monthDay.getMonthValue();
        }
    }

    /**
     * <p>
     * This method returns the {@code Integer} value for the day-of-month.
     * </p>
     * <p>
     * This is a <code>null</code> safe version of :
     * </p>
     * 
     * <pre>
     * monthDay.getDayOfMonth()
     * </pre>
     * 
     * @param monthDay input monthDay
     * @return the day-of-month (from 1 to 31) or null (if input monthDay is null)
     */
    public static Integer getDayOfMonth(MonthDay monthDay) {
        if (monthDay == null) {
            logger.warn("monthDay is null");
            return null;
        } else {
            return monthDay.getDayOfMonth();
        }
    }
}