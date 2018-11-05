package com.zad.jdk8.datetime;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * 描述:
 * DateTimeFormatterApi
 *
 * @author zad
 * @create 2018-09-08 21:37
 */
@Slf4j
public class DateTimeFormatterApi {
    public static void main(String[] args) {
        // 格式化日期
        DateTimeFormatter FULL_DATE = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        log.info("FULL_DATE_format --> {}", LocalDate.now().format(FULL_DATE));

        DateTimeFormatter LONG_DATE = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
        log.info("LONG_DATEformat --> {}", LocalDate.now().format(LONG_DATE));

        DateTimeFormatter MEDIUM_DATE = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
        log.info("MEDIUM_DATE_format --> {}", LocalDate.now().format(MEDIUM_DATE));

        DateTimeFormatter SHORT_DATE = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        log.info("SHORT_DATE_format --> {}", LocalDate.now().format(SHORT_DATE));


        // 格式化输出
        log.info("ISO_LOCAL_DATE_TIME --> {}", DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now()));
        log.info("ISO_LOCAL_DATE --> {}", DateTimeFormatter.ISO_LOCAL_DATE.format(LocalDate.now()));
        log.info("ISO_LOCAL_TIME --> {}", DateTimeFormatter.ISO_LOCAL_TIME.format(LocalTime.now()));
        log.info("ISO_INSTANT --> {}", DateTimeFormatter.ISO_INSTANT.format(Instant.now()));
        log.info("ISO_DATE --> {}", DateTimeFormatter.ISO_DATE.format(LocalDate.now()));
        log.info("BASIC_ISO_DATE --> {}", DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now()));

        // get
        log.info("getChronology --> {}", DateTimeFormatter.ISO_LOCAL_DATE_TIME.getChronology());
        log.info("getDecimalStyle --> {}", DateTimeFormatter.ISO_LOCAL_DATE_TIME.getDecimalStyle());
        log.info("getLocale --> {}", DateTimeFormatter.ISO_LOCAL_DATE_TIME.getLocale());
        log.info("getResolverFields --> {}", DateTimeFormatter.ISO_LOCAL_DATE_TIME.getResolverFields());
        log.info("getResolverStyle --> {}", DateTimeFormatter.ISO_LOCAL_DATE_TIME.getResolverStyle());
        log.info("getZone --> {}", DateTimeFormatter.ISO_LOCAL_DATE_TIME.getZone());
    }
}
