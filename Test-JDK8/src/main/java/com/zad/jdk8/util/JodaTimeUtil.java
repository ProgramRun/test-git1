package com.zad.jdk8.util;

/**
 * 描述:
 *
 * @author zad
 * @create 2018-10-30 15:15
 */


import lombok.NonNull;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public final class JodaTimeUtil {

    private JodaTimeUtil() {
        throw new AssertionError("Util禁止反射实例化");
    }

    public static final String DEFAULT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATE = "yyyy-MM-dd";
    public static final String DEFAULT_TIME = "HH:mm:ss";

    public static final String SLASH_DATE_TIME = "yyyy/MM/dd HH:mm:ss";
    public static final String SLASH_DATE = "yyyy/MM/dd";

    private static final int ONE_YEAR_MONTHS = 12;


    //====================== convert =========================== //

    /**
     * 将 dateTimeStr 按照 formatStr格式转为 Date
     * dateTimeStr和 formatStr格式需要统一
     *
     * @param dateTimeStr
     * @param formatStr
     * @return
     */
    public static Date strToDate(@NonNull String dateTimeStr, @NonNull String formatStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(formatStr);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    /**
     * 将 dateTimeStr 转为 Date
     * dateTimeStr格式必须符合默认时间格式
     *
     * @param dateTimeStr
     * @return
     */
    public static Date strToDate(@NonNull String dateTimeStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(DEFAULT_DATE_TIME);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    /**
     * 将 Date 按照 formatStr 格式输出
     * 当 formatStr为null时,按照jodatime的默认格式输出
     *
     * @param date
     * @param formatStr
     * @return
     */
    public static String dateToStr(@NonNull Date date, @NonNull String formatStr) {
        DateTime dateTime = new DateTime(date.getTime());
        return dateTime.toString(formatStr);
    }

    /**
     * 将 Date 按照 标准格式 格式输出
     *
     * @param date
     * @return
     */
    public static String dateToStr(@NonNull Date date) {
        DateTime dateTime = new DateTime(date.getTime());
        return dateTime.toString(DEFAULT_DATE_TIME);
    }


    // ========================= format ============================== //


    /**
     * 新建指定date
     *
     * @param year
     * @param monthOfYear
     * @param dayOfMonth
     * @param hourOfDay
     * @param minuteOfHour
     * @param secondOfMinute
     * @param millisOfSecond
     * @return
     */
    public static Date getSpecificDateTime(int year, int monthOfYear, int dayOfMonth, int hourOfDay,
                                           int minuteOfHour, int secondOfMinute, int millisOfSecond) {
        return new DateTime(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond).toDate();
    }

    /**
     * 将 时间归 0
     *
     * @param date
     * @return
     */
    public static Date getDay(@NonNull Date date) {
        return withTime(date, 0, 0, 0, 0);
    }

    /**
     * 将date 的年月日设置为指定年月日
     *
     * @param date
     * @param year
     * @param monthOfYear
     * @param dayOfMonth
     * @return
     */
    public static Date withDate(@NonNull Date date, int year, int monthOfYear, int dayOfMonth) {
        DateTime dateTime = new DateTime(date.getTime());
        return dateTime.withDate(year, monthOfYear, dayOfMonth).toDate();
    }

    /**
     * 将date 的时间设置为指定时间
     *
     * @param date
     * @param hourOfDay
     * @param minuteOfHour
     * @param secondsOfMinute
     * @param millisOfSecond
     * @return
     */
    public static Date withTime(@NonNull Date date, int hourOfDay, int minuteOfHour, int secondsOfMinute, int millisOfSecond) {
        DateTime dateTime = new DateTime(date.getTime());
        return dateTime.withTime(hourOfDay, minuteOfHour, secondsOfMinute, millisOfSecond).toDate();
    }

    /**
     * 指定年
     *
     * @param date
     * @param year
     * @return
     */
    public static Date withYear(@NonNull Date date, int year) {
        DateTime dateTime = new DateTime(date.getTime());
        return dateTime.withYear(year).toDate();
    }

    /**
     * 指定月
     *
     * @param date
     * @param monthOfYear
     * @return
     */
    public static Date withMonthOfYear(@NonNull Date date, int monthOfYear) {
        DateTime dateTime = new DateTime(date.getTime());
        return dateTime.withMonthOfYear(monthOfYear).toDate();
    }

    /**
     * 指定日(一个月的第几天)
     *
     * @param date
     * @param dayOfMonth
     * @return
     */
    public static Date withDayOfMonth(@NonNull Date date, int dayOfMonth) {
        DateTime dateTime = new DateTime(date.getTime());
        return dateTime.withDayOfMonth(dayOfMonth).toDate();
    }

    /**
     * 指定日(一周的第几天)
     *
     * @param date
     * @param dayOfWeek
     * @return
     */
    public static Date withDayOfWeek(@NonNull Date date, int dayOfWeek) {
        DateTime dateTime = new DateTime(date.getTime());
        return dateTime.withDayOfWeek(dayOfWeek).toDate();
    }

    /**
     * 指定小时
     *
     * @param date
     * @param hour
     * @return
     */
    public static Date withHourOfDay(@NonNull Date date, int hour) {
        DateTime dateTime = new DateTime(date.getTime());
        return dateTime.withHourOfDay(hour).toDate();
    }

    /**
     * 指定分钟
     *
     * @param date
     * @param minute
     * @return
     */
    public static Date withMinuteOfHour(@NonNull Date date, int minute) {
        DateTime dateTime = new DateTime(date.getTime());
        return dateTime.withMinuteOfHour(minute).toDate();
    }

    /**
     * 指定秒
     *
     * @param date
     * @param second
     * @return
     */
    public static Date withSecondOfMinute(@NonNull Date date, int second) {
        DateTime dateTime = new DateTime(date.getTime());
        return dateTime.withSecondOfMinute(second).toDate();
    }

    /**
     * 指定分钟和秒
     *
     * @param date
     * @param minute
     * @param second
     * @return
     */
    public static Date withMinuteAndSecond(@NonNull Date date, int minute, int second) {
        DateTime dateTime = new DateTime(date.getTime());
        return dateTime.withMinuteOfHour(minute).withSecondOfMinute(second).toDate();
    }


    // ========================= calculate =========================== //

    /**
     * date 添加指定年
     *
     * @param date
     * @param years
     * @return
     */
    public static Date plusYears(@NonNull Date date, int years) {
        return new DateTime(date.getTime()).plusYears(years).toDate();
    }

    /**
     * date减去指定年
     *
     * @param date
     * @param years
     * @return
     */
    public static Date minusYears(@NonNull Date date, int years) {
        return plusYears(date, -years);
    }

    /**
     * date加指定月
     *
     * @param date
     * @param months
     * @return
     */
    public static Date plusMonths(@NonNull Date date, int months) {
        return new DateTime(date.getTime()).plusMonths(months).toDate();
    }

    /**
     * date减指定月
     *
     * @param date
     * @param months
     * @return
     */
    public static Date minusMonths(@NonNull Date date, int months) {
        return plusMonths(date, -months);
    }

    /**
     * date加指定周
     *
     * @param date
     * @param weeks
     * @return
     */
    public static Date plusWeeks(@NonNull Date date, int weeks) {
        return new DateTime(date.getTime()).plusWeeks(weeks).toDate();
    }

    /**
     * date减指定周
     *
     * @param date
     * @param weeks
     * @return
     */
    public static Date minusWeeks(@NonNull Date date, int weeks) {
        return plusWeeks(date, -weeks);
    }

    /**
     * date加指定天
     *
     * @param date
     * @param days
     * @return
     */
    public static Date plusDays(@NonNull Date date, int days) {
        return new DateTime(date.getTime()).plusDays(days).toDate();
    }

    /**
     * date减指定天
     *
     * @param date
     * @param days
     * @return
     */
    public static Date minusDays(@NonNull Date date, int days) {
        return plusDays(date, -days);
    }

    /**
     * date 加指定小时
     *
     * @param date
     * @param hours
     * @return
     */
    public static Date plusHours(@NonNull Date date, int hours) {
        return new DateTime(date.getTime()).plusHours(hours).toDate();
    }

    /**
     * date 减指定小时
     *
     * @param date
     * @param hours
     * @return
     */
    public static Date minusHours(@NonNull Date date, int hours) {
        return plusHours(date, -hours);
    }

    /**
     * date 加指定分钟
     */
    public static Date plusMinutes(@NonNull Date date, int minutes) {
        return new DateTime(date.getTime()).plusMinutes(minutes).toDate();
    }

    /**
     * date 减指定分钟
     *
     * @param date
     * @param minutes
     * @return
     */
    public static Date minusMinutes(@NonNull Date date, int minutes) {
        return plusMinutes(date, -minutes);
    }

    /**
     * date 加指定秒
     *
     * @param date
     * @param seconds
     * @return
     */
    public static Date plusSeconds(@NonNull Date date, int seconds) {
        return new DateTime(date.getTime()).plusSeconds(seconds).toDate();
    }

    /**
     * date 减指定秒
     *
     * @param date
     * @param seconds
     * @return
     */
    public static Date minusSeconds(@NonNull Date date, int seconds) {
        return plusSeconds(date, -seconds);
    }

    /**
     * date 加指定毫秒
     *
     * @param date
     * @param millis
     * @return
     */
    public static Date plusMillis(@NonNull Date date, int millis) {
        return new DateTime(date.getTime()).plusMillis(millis).toDate();
    }

    /**
     * date 减指定毫秒
     *
     * @param date
     * @param millis
     * @return
     */
    public static Date minusMillis(@NonNull Date date, int millis) {
        return plusMillis(date, -millis);
    }

    /**
     * date 加指定 年月日时分秒毫秒
     *
     * @param date
     * @param years
     * @param months
     * @param days
     * @param hours
     * @param minutes
     * @param seconds
     * @param millis
     * @return
     */
    public static Date plusTime(@NonNull Date date, int years, int months, int days, int hours, int minutes, int seconds, int millis) {
        return new DateTime(date.getTime()).plusYears(years)
                .plusMonths(months)
                .plusDays(days)
                .plusHours(hours)
                .plusMinutes(minutes)
                .plusSeconds(seconds)
                .plusMillis(millis)
                .toDate();
    }

    /**
     * date 减指定 年月日时分秒毫秒
     *
     * @param date
     * @param years
     * @param months
     * @param days
     * @param hours
     * @param minutes
     * @param seconds
     * @param millis
     * @return
     */
    public static Date minusTime(@NonNull Date date, int years, int months, int days, int hours, int minutes, int seconds, int millis) {
        return plusTime(date, -years, -months, -days, -hours, -minutes, -seconds, -millis);
    }


    /**
     * 获取 date是一年中的第几天
     *
     * @param date
     * @return
     */
    public static int getDayOfYear(@NonNull Date date) {
        DateTime dateTime = new DateTime(date.getTime());
        return dateTime.getDayOfYear();
    }

    /**
     * 获取date 是一个月的第几天
     *
     * @param date
     * @return
     */
    public static int getDayOfMonth(@NonNull Date date) {
        DateTime dateTime = new DateTime(date.getTime());
        return dateTime.getDayOfMonth();
    }

    /**
     * 获取date 是一个周的第几天
     *
     * @param date
     * @return
     */
    public static int getDayOfWeek(@NonNull Date date) {
        DateTime dateTime = new DateTime(date.getTime());
        return dateTime.getDayOfWeek();
    }

    /**
     * 获取date 的年份
     *
     * @param date
     * @return
     */
    public static int getYear(@NonNull Date date) {
        DateTime dateTime = new DateTime(date.getTime());
        return dateTime.getYear();
    }

    /**
     * 获取date 的月份
     *
     * @param date
     * @return
     */
    public static int getMonthOfYear(@NonNull Date date) {
        DateTime dateTime = new DateTime(date.getTime());
        return dateTime.getMonthOfYear();
    }

    /**
     * 获取date 的小时
     *
     * @param date
     * @return
     */
    public static int getHourOfDay(@NonNull Date date) {
        DateTime dateTime = new DateTime(date.getTime());
        return dateTime.getHourOfDay();
    }

    /**
     * 获取date 的分钟
     *
     * @param date
     * @return
     */
    public static int getMinuteOfDay(@NonNull Date date) {
        DateTime dateTime = new DateTime(date.getTime());
        return dateTime.getMinuteOfDay();
    }

    /**
     * 获取date 的分钟
     *
     * @param date
     * @return
     */
    public static int getMinuteOfHour(@NonNull Date date) {
        DateTime dateTime = new DateTime(date.getTime());
        return dateTime.getMinuteOfHour();
    }

    /**
     * 获取date 的当天经过秒数
     *
     * @param date
     * @return
     */
    public static int getSecondOfDay(@NonNull Date date) {
        DateTime dateTime = new DateTime(date.getTime());
        return dateTime.getSecondOfDay();
    }

    /**
     * 获取date 的当前分钟经过秒数
     *
     * @param date
     * @return
     */
    public static int getSecondOfMinute(@NonNull Date date) {
        DateTime dateTime = new DateTime(date.getTime());
        return dateTime.getSecondOfMinute();
    }


    // ========================= compare ============================= //

    /**
     * 获取较大 date
     *
     * @param date1
     * @param date2
     * @return
     */
    public static Date max(@NonNull Date date1, @NonNull Date date2) {
        return getMaxOrMinDate(date1, date2, true);
    }

    /**
     * 获取较小 date
     *
     * @param date1
     * @param date2
     * @return
     */
    public static Date min(@NonNull Date date1, @NonNull Date date2) {
        return getMaxOrMinDate(date1, date2, false);
    }

    private static Date getMaxOrMinDate(@NonNull Date date1, @NonNull Date date2, boolean max) {
        if (max) {
            return date1.compareTo(date2) >= 0 ? date1 : date2;
        } else {
            return date1.compareTo(date2) >= 0 ? date2 : date1;
        }
    }

    /**
     * date 是否在 时间范围内
     * begin < date < end 是否为真
     *
     * @param date
     * @param begin
     * @param end
     * @return
     */
    public static boolean isBetween(@NonNull Date date, @NonNull Date begin, @NonNull Date end) {
        return date.after(begin) && end.after(date);
    }


    /**
     * 计算 minuend - subtrahend = 年数
     *
     * @param minuend
     * @param subtrahend
     * @return
     */
    public static int getDifferentYears(@NonNull Date minuend, @NonNull Date subtrahend) {
        if (minuend.after(subtrahend)) {
            return getInterval(subtrahend, minuend).toPeriod().getYears();
        } else {
            return -getInterval(minuend, subtrahend).toPeriod().getYears();
        }
    }

    /**
     * 计算 minuend - subtrahend =  月数
     *
     * @param minuend
     * @param subtrahend
     * @return
     */
    public static int getDifferentMonths(@NonNull Date minuend, @NonNull Date subtrahend) {
        Period p;
        if (minuend.after(subtrahend)) {
            p = getInterval(subtrahend, minuend).toPeriod();
            return p.getYears() * ONE_YEAR_MONTHS + p.getMonths();
        } else {
            p = getInterval(minuend, subtrahend).toPeriod();
            return -(p.getYears() * ONE_YEAR_MONTHS + p.getMonths());
        }
    }


    /**
     * later >= sooner
     *
     * @param sooner
     * @param later
     * @return
     */
    private static Interval getInterval(Date sooner, Date later) {
        return new Interval(new DateTime(sooner.getTime()), new DateTime(later.getTime()));
    }

    /**
     * 计算 minuend - subtrahend = 星期数
     *
     * @param minuend
     * @param subtrahend
     * @return
     */
    public static int getDifferentWeeks(@NonNull Date minuend, @NonNull Date subtrahend) {
        return (int) ((minuend.getTime() - subtrahend.getTime()) / TimeUnit.DAYS.toMillis(7));
    }

    /**
     * 计算 minuend - subtrahend = 天数
     *
     * @param minuend
     * @param subtrahend
     * @return
     */
    public static int getDifferentDays(@NonNull Date minuend, @NonNull Date subtrahend) {
        return (int) ((minuend.getTime() - subtrahend.getTime()) / TimeUnit.DAYS.toMillis(1));
    }

    /**
     * 计算 minuend - subtrahend = 小时数
     *
     * @param minuend
     * @param subtrahend
     * @return
     */
    public static int getDifferentHours(@NonNull Date minuend, @NonNull Date subtrahend) {
        return (int) ((minuend.getTime() - subtrahend.getTime()) / TimeUnit.HOURS.toMillis(1));
    }

    /**
     * 计算 minuend - subtrahend = 分钟数
     *
     * @param minuend
     * @param subtrahend
     * @return
     */
    public static long getDifferentMinutes(@NonNull Date minuend, @NonNull Date subtrahend) {
        return (minuend.getTime() - subtrahend.getTime()) / TimeUnit.MINUTES.toMillis(1);
    }

    /**
     * 计算 minuend - subtrahend = 秒数
     *
     * @param minuend
     * @param subtrahend
     * @return
     */
    public static long getDifferentSeconds(@NonNull Date minuend, @NonNull Date subtrahend) {
        return (minuend.getTime() - subtrahend.getTime()) / TimeUnit.SECONDS.toMillis(1);
    }

}

