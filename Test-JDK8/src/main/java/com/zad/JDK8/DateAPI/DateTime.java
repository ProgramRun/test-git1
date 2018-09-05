package com.zad.JDK8.DateAPI;

import lombok.extern.slf4j.Slf4j;

import java.time.*;

@Slf4j
public class DateTime {

    public static void main(String[] args) {
        //Instant——它代表的是时间戳
        Instant now = Instant.now();
        log.info("Instant:{}",now.toString());
        //LocalDate——不包含具体时间的日期，比如2014-01-14。它可以用来存储生日，周年纪念日，入职日期等
        log.info("LocalDate:{}", LocalDate.now().toString());
        //LocalTime——它代表的是不含日期的时间
        log.info("LocalTime:{}", LocalTime.now().toString());
        //LocalDateTime——它包含了日期及时间，不过还是没有偏移信息或者说时区
        log.info("LocalDateTime:{}", LocalDateTime.now().toString());
        //ZonedDateTime——这是一个包含时区的完整的日期时间，偏移量是以UTC/格林威治时间为基准的
        log.info("ZonedDateTime:{}", ZonedDateTime.now().toString());

        log.info("年year:{}",LocalDate.now().getYear());
        log.info("英文月份month:{}",LocalDate.now().getMonth());
        log.info("一个月的第几天DayOfMonth:{}",LocalDate.now().getDayOfMonth());
        log.info("英文周几DayOfWeek:{}",LocalDate.now().getDayOfWeek());
        log.info("一年中第几天DayOfYear:{}",LocalDate.now().getDayOfYear());
        log.info("数字月份MonthValue:{}",LocalDate.now().getMonthValue());
    }
}
