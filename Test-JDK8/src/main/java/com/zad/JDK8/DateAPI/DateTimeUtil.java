package com.zad.JDK8.DateAPI;


import org.apache.commons.lang3.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 描述:
 * DateTimeUtil
 *
 * @author zad
 * @create 2018-09-08 15:50
 */
public class DateTimeUtil {

    private static final ConcurrentMap<String, DateTimeFormatter> FORMATTER_CACHE = new ConcurrentHashMap<>();

    private static final int PATTERN_CACHE_SIZE = 30;

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private DateTimeUtil() {
        throw new AssertionError("Util禁止实例化");
    }


    /**
     * Date转换为格式化时间
     *
     * @param date    date
     * @param pattern 格式
     * @return
     */
    public static String format(Date date, String pattern) {
        return format(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()), pattern);
    }

    /**
     * localDateTime转换为格式化时间
     *
     * @param localDateTime localDateTime
     * @param pattern       格式
     * @return
     */
    public static String format(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter formatter = getFormatter(pattern);
        return localDateTime.format(formatter);
    }

    /**
     * 格式化字符串转为Date
     *
     * @param time    格式化时间
     * @param pattern 格式
     * @return
     */
    public static Date parseDate(String time, String pattern) {
        return Date.from(parseLocalDateTime(time, pattern).atZone(ZoneId.systemDefault()).toInstant());

    }

    /**
     * 格式化字符串转为LocalDateTime
     *
     * @param time    格式化时间
     * @param pattern 格式
     * @return
     */
    public static LocalDateTime parseLocalDateTime(String time, String pattern) {
        DateTimeFormatter formatter = getFormatter(pattern);
        return LocalDateTime.parse(time, formatter);
    }

    /**
     * 在缓存中创建DateTimeFormatter
     *
     * @param pattern 格式
     * @return
     */
    private static DateTimeFormatter getFormatter(String pattern) {
        if (StringUtils.isBlank(pattern)) {
            throw new IllegalArgumentException("Invalid pattern specification");
        }
        DateTimeFormatter formatter = FORMATTER_CACHE.get(pattern);
        if (formatter == null) {
            if (FORMATTER_CACHE.size() < PATTERN_CACHE_SIZE) {
                formatter = DateTimeFormatter.ofPattern(pattern);
                DateTimeFormatter oldFormatter = FORMATTER_CACHE.putIfAbsent(pattern, formatter);
                if (oldFormatter != null) {
                    formatter = oldFormatter;
                }
            }
        }

        return formatter;
    }

    /**
     * Date --> LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * Date --> LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate dateToLocalDate(Date date) {
        LocalDateTime localDateTime = LocalDateTime
                .ofInstant(date.toInstant(), ZoneId.systemDefault());
        return localDateTime.toLocalDate();
    }

    /**
     * Date --> LocalTime
     *
     * @param date
     * @return
     */
    public static LocalTime dateToLocalTime(Date date) {
        LocalDateTime localDateTime = LocalDateTime
                .ofInstant(date.toInstant(), ZoneId.systemDefault());
        return localDateTime.toLocalTime();
    }


    /**
     * LocalDateTime -->Date
     *
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        Instant instant = localDateTime
                .atZone(ZoneId.systemDefault())
                .toInstant();
        return Date.from(instant);
    }


    /**
     * LocalDate --> Date
     *
     * @param localDate
     * @return
     */
    public static Date localDateToDate(LocalDate localDate) {
        Instant instant = localDate
                .atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant();
        return Date.from(instant);
    }

    /**
     * LocalTime --> Date
     *
     * @param localTime
     * @return
     */
    public static Date localTimeToDate(LocalTime localTime) {
        Instant instant = LocalDateTime
                .of(LocalDate.now(), localTime)
                .atZone(ZoneId.systemDefault())
                .toInstant();
        return Date.from(instant);
    }

    /**
     * @param date
     * @return
     */

    public static Date stringToDate(String date) {
        Instant instant = LocalDate
                .parse(date, getFormatter(YYYY_MM_DD))
                .atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant();
        return Date.from(instant);
    }

    /**
     * @param dateTime
     * @return
     */
    public static Date stringToDateTime(String dateTime) {
        Instant instant = LocalDateTime
                .parse(dateTime, getFormatter(YYYY_MM_DD_HH_MM_SS))
                .atZone(ZoneId.systemDefault())
                .toInstant();
        return Date.from(instant);
    }
}
