package com.zad.jdk8.datetime;

import com.zad.jdk8.util.DateTimeUtil;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class DateTime {
    public static void main(String[] args) {
        System.out.println(DateTimeUtil.dateToLocalDate(Date.from(Instant.now())));
        System.out.println(DateTimeUtil.dateToLocalDateTime(Date.from(Instant.now())));
        System.out.println(DateTimeUtil.dateToLocalTime(Date.from(Instant.now())));
        System.out.println(DateTimeUtil.localDateTimeToDate(LocalDateTime.now()));
        System.out.println(DateTimeUtil.localDateToDate(LocalDate.now()));
        System.out.println(DateTimeUtil.localTimeToDate(LocalTime.now()));
        System.out.println(DateTimeUtil.stringToDate("1990-02-07"));
        System.out.println(DateTimeUtil.stringToDateTime("1990-02-07 12:12:10"));
    }
}
