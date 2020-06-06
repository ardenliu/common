package com.github.ardenliu.common.lang.time;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.MonthDay;

import org.junit.jupiter.api.Test;

class TimeUtilsTest {

    @Test
    void getLocalDate() {
        assertNull(TimeUtils.getLocalDate(null));
        assertNull(TimeUtils.getLocalDate(""));
        
        assertEquals(LocalDate.parse("2007-12-03"), TimeUtils.getLocalDate("2007-12-03"));
    }
    
    @Test
    void getMonthValue() {
        assertNull(TimeUtils.getMonthValue(null));
        
        assertEquals(1, TimeUtils.getMonthValue(MonthDay.of(1, 2)));
    }
    
    @Test
    void getDayOfMonth() {
        assertNull(TimeUtils.getDayOfMonth(null));
        
        assertEquals(2, TimeUtils.getDayOfMonth(MonthDay.of(1, 2)));
    }
    
    @Test
    void getMonthDay() {
        assertNull(TimeUtils.getMonthDay(null, 1));
        assertNull(TimeUtils.getMonthDay(1, null));
        assertNull(TimeUtils.getMonthDay(null, null));
        
        assertEquals(MonthDay.of(1, 2), TimeUtils.getMonthDay(1, 2));
    }
    
    @Test
    void getMonthDay_String() {
        assertNull(TimeUtils.getMonthDay(null));
        assertNull(TimeUtils.getMonthDay(""));
        
        assertEquals(MonthDay.of(1, 2), TimeUtils.getMonthDay("--01-02"));
    }
}