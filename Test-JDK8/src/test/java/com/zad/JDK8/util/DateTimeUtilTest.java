package com.zad.JDK8.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Slf4j
public class DateTimeUtilTest {
    private static Date date;

    @BeforeEach
    public void init() {
        date = new Date();
    }

    @Test
    public void t1() {
        // 格式化输出
        String d1 = DateTimeUtil.format(date, "yyyy/MM/dd HH:mm:ss");
        log.info("yyyy/MM/dd hh:mm:ss --> {}", d1);

        String d2 = DateTimeUtil.format(date, "yyyy/MM/dd");
        log.info("yyyy/MM/dd hh:mm:ss --> {}", d2);

        String d3 = DateTimeUtil.format(date, "HHmm");
        log.info("yyyy/MM/dd hh:mm:ss --> {}", d3);


        // String --> Date
        Date date = DateTimeUtil.parseDate("1990/02/07 12:12:12", "yyyy/MM/dd HH:mm:ss");
        log.info("StringToDate --> {}", date);
        // 解析年月日时分秒
        DateTimeFormatter DATE_TIME_PATTERN = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        // 解析年月日
        DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        // 解析时分秒
        DateTimeFormatter TIME_PATTERN = DateTimeFormatter.ofPattern("HHmm");

        log.info("解析LocalDate+LocalTime --> {}", LocalDateTime.parse("1990/02/07 12:12:12", DATE_TIME_PATTERN));

        log.info("解析LocalDate --> {}", LocalDate.parse("1990/02/07", DATE_PATTERN));

        log.info("解析LocalTime --> {}", LocalTime.parse("2012", TIME_PATTERN));

    }

}
