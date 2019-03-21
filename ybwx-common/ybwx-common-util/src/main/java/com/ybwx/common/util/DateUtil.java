package com.ybwx.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * DateUtil
 *
 * @author CuiShuai
 * @date 15/11/27
 */
@Slf4j
public class DateUtil {

    public static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String T_DATETIME = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String DATE1 = "yyyy-MM-dd";
    public static final String DATE2 = "yyyyMMdd";

    public static String formatDate(Date date) {
        return formatDate(date, DATE1);
    }

    public static String formatDateTime(Date date) {
        return formatDate(date, DATETIME);
    }

    public static String formatDate(Date date, String pattern) {
        if (date == null) {
            return null;
        }

        return DateFormatUtils.format(date, pattern, Locale.CHINA);
    }

    public static String formatDate(String dateStr, String srcPattern, String desPattern) {
        Date date = parseDate(dateStr, srcPattern);
        if (date == null) {
            return null;
        }
        return formatDate(date, desPattern);
    }

    public static Date parseDate(String dateStr) {
        return parseDate(dateStr, DATE1);
    }

    public static Date parseDateTime(String dateStr) {
        return parseDate(dateStr, new String[]{
                DATETIME,
                "yyyy-MM-dd HH:mm:ss.SSS",
        });
    }

    public static Date parseDate(String dateStr, String pattern) {
        return parseDate(StringUtils.trim(dateStr), new String[]{pattern});
    }

    public static Date parseDate(String dateStr, String[] patterns) {
        if (dateStr == null) {
            return null;
        }
        try {
            return DateUtils.parseDateStrictly(dateStr, patterns);
        } catch (Exception e) {
            log.error("日期转换错误, dateStr={}, pattern={}", dateStr, StringUtils.join(patterns, ","));
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static boolean test(String dateStr, String pattern) {
        return test(dateStr, new String[]{pattern});
    }

    public static boolean test(String dateStr, String[] patterns) {
        if (dateStr == null) {
            return false;
        }
        try {
            DateUtils.parseDateStrictly(dateStr, patterns);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 两个时间相隔天数 time1-time2
     *
     * @param time1
     * @param time2
     * @return
     */
    public static long diffDays(Date time1, Date time2) {
        if (time1 == null || time2 == null) {
            return 0;
        }
        return (time1.getTime() - time2.getTime()) / 1000 / 60 / 60 / 24;
    }

    public static Date addYears(Date date, Integer years) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, years);
        return calendar.getTime();
    }

    public static Date addDays(Date date, Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    public static Date addMinutes(Date date, Integer minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

    public static Date addSeconds(Date date, Integer seconds) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, seconds);
        return calendar.getTime();
    }

}
