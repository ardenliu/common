package com.github.ardenliu.common.lang.time;

import java.time.LocalDateTime;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TimeUtils {
    private static final Logger logger = LogManager.getLogger(TimeUtils.class);

    /**
     * The format of isoTimeString should be java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME
     * <p>
     * The ISO date-time formatter that formats or parses a date-time with an offset, such as '2011-12-03T10:15:30+01:00'.
     * 
     * @param isoTimeString
     * @return
     */
    public static LocalDateTime getUtcFromIsoOffsetString(String isoTimeString) {
        return TimeUtils.getLocalDateTimeFromIsoOffsetStringByZoneOffset(isoTimeString, ZoneOffset.UTC);
    }
    
    /**
     * The format of isoTimeString should be java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME
     * <p>
     * The ISO date-time formatter that formats or parses a date-time with an offset, such as '2011-12-03T10:15:30+01:00'.
     * 
     * @param isoTimeString
     * @param zoneOffset
     * @return
     */
    public static LocalDateTime getLocalDateTimeFromIsoOffsetStringByZoneOffset(String isoTimeString, ZoneOffset zoneOffset) {
        return OffsetDateTime.parse(isoTimeString).toInstant().atOffset(zoneOffset).toLocalDateTime();
    }

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

    /**
     * The format of isoTimeString should be java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME
     * <p>
     * The ISO date-time formatter that formats or parses a date-time without an offset, such as '2011-12-03T10:15:30'.
     * 
     * @param isoTimeString input ISO time string
     * @return the LocalDateTime object
     */
    public static LocalDateTime getLocalDateTimeFromIsoLocalString(String isoTimeString) {
        return LocalDateTime.parse(isoTimeString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    /**
     * 
     * <p>
     * This is a <code>null</code> safe version of :
     * </p>
     * 
     * <pre>
     * MonthDay.of(int month, int dayOfMonth)
     * </pre>
     * 
     * @param month      the Integer value of month
     * @param dayOfMonth the Integer value of day of month
     * @return the MonthDay object; if inputs have null value, the return will be null.
     */
    public static MonthDay getMonthDay(Integer month, Integer dayOfMonth) {
        if (month == null || dayOfMonth == null) {
            logger.warn("month[" + month + "] or dayOfMonth[" + dayOfMonth + "] has null value");
            return null;
        } else {
            return MonthDay.of(month, dayOfMonth);
        }
    }
}