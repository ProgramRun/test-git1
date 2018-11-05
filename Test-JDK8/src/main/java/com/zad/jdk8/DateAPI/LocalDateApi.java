package com.zad.jdk8.DateAPI;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.Month;

/**
 * 描述:
 * LocalDateApi
 *
 * @author zad
 * @create 2018-09-08 8:57
 */
@Slf4j
public class LocalDateApi {
    public static void main(String[] args) {
        // 系统当前时间
        LocalDate now = LocalDate.now();
        log.info("系统当前时间 : {}", now);
        // 指定年月日
        LocalDate birthday = LocalDate.of(1990, 02, 07);
        log.info("指定年月日 : {}", birthday);
        // 指定年月日
        LocalDate of = LocalDate.of(1990, Month.FEBRUARY, 7);
        log.info("指定年月日 : {}", of);
        // 指定年日
        LocalDate localDate = LocalDate.ofYearDay(1990, 38);
        log.info("指定年月日 : {}", localDate);
        // 标准格式化 String --> LocalDate
        LocalDate parse = LocalDate.parse("1990-02-07");
        log.info("格式化 : {}", parse);


    }
}
