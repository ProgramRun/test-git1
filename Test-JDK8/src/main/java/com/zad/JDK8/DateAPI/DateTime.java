package com.zad.JDK8.DateAPI;

import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Slf4j
public class DateTime {

    public static void main(String[] args) {
        //instantApi();
        localDateApi();
    }

    private static void instantApi() {
        Instant now = Instant.now();
        log.info("Instant输出格式 : {}", now.toString());
        Instant parse = Instant.parse("2018-09-05T04:28:17.265Z");
        log.info("String-->Instant : {}", parse);
    }

    private static void localDateApi() {
        // 系统当前时间
        LocalDate now = LocalDate.now();
        System.out.println(now);
        // 指定年月日
        LocalDate birthday = LocalDate.of(1990, 02, 07);
        System.out.println(birthday);
        // 指定年月日
        LocalDate of = LocalDate.of(1990, Month.FEBRUARY, 7);
        System.out.println(of);
        // 指定年日
        LocalDate localDate = LocalDate.ofYearDay(1990, 38);
        System.out.println(localDate);
        // 标准格式化 String --> LocalDate
        LocalDate parse = LocalDate.parse("1990-02-07");
        System.out.println(parse);
        // 指定格式化
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        System.out.println(LocalDate.parse("1990-02-07",dtf));
    }

    /**
     * Date --> LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime;
    }

    /**
     * Date --> LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate dateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();
        return localDate;
    }

    /**
     * Date --> LocalTime
     *
     * @param date
     * @return
     */
    public static LocalTime dateToLocalTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalTime localTime = localDateTime.toLocalTime();
        return localTime;
    }


    /**
     * LocalDateTime -->Date
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeTodate(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        Date date = Date.from(instant);
        return date;
    }


    /**
     * LocalDate --> Date
     *
     * @param localDate
     * @return
     */
    public static Date localDateToDate(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        Date date = Date.from(instant);
        return date;
    }

    /**
     * LocalTime --> Date
     *
     * @param localTime
     * @return
     */
    public static Date localTimeToDate(LocalTime localTime) {
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        Date date = Date.from(instant);
        return date;
    }
}
