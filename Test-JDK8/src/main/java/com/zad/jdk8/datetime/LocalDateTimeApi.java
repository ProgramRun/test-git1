package com.zad.jdk8.datetime;

import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * 描述:
 * LocalDateTimeApi
 *
 * @author zad
 * @create 2018-09-08 8:57
 */
@Slf4j
public class LocalDateTimeApi {
    public static void main(String[] args) {
        // LocalDateTime 输出格式
        LocalDateTime now = LocalDateTime.now();
        log.info("LocalDateTime 输出格式 : {}", now);
        // LocalDate与LocalTime拼接成LocalDateTime
        LocalDateTime of = LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 02, 00, 111));
        log.info("LocalDate + LocalTime : {}", of);
        // 指定年月日时分
        LocalDateTime of1 = LocalDateTime.of(1990, 02, 07, 12, 12);
        log.info("指定年月日时分 : {}", of1);
        // 指定年月日时分秒
        LocalDateTime of2 = LocalDateTime.of(1990, 02, 07, 12, 12, 12);
        log.info("指定年月日时分秒 : {}", of2);
        // 指定年月日时分秒纳秒
        LocalDateTime of3 = LocalDateTime.of(1990, 02, 07, 12, 12, 12, 111);
        log.info("指定年月日时分秒纳秒 : {}", of3);
        // 从标准时间指定LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(1111111111L, 1111, ZoneOffset.UTC);
        log.info("从标准时间指定LocalDateTime : {}", localDateTime);
        // 通过Instant和ZoneId获得LocalDateTime
        LocalDateTime localDateTime1 = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        log.info("通过Instant和ZoneId获得LocalDateTime : {}", localDateTime1);
        // String->LocalDateTime
        LocalDateTime parse = LocalDateTime.parse("1990-02-07T12:12:12");
        log.info("String->LocalDateTime : {}", parse);
        log.info("获得年:{},月(英):{},月:{},日:{},周几:{},第几日:{},小时:{},分钟:{},秒:{},纳秒:{}", parse.getYear(), parse.getMonth(), parse.getMonthValue(), parse.getDayOfMonth(), parse.getDayOfWeek(), parse.getDayOfYear(), parse.getHour(), parse.getMinute(), parse.getSecond(), parse.getNano());
        String format = parse.format(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss"));
        String format1 = parse.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        log.info("格式化输出 --> {}  标准格式化 --> {}", format, format1);
    }
}
