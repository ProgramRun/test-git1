package com.zad.JDK8.DateTimeTest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
public class DateTime {

    @Test
    public void t1() {
        // String转Date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse("2018-09-01", dtf);
        log.info("StringToDate : {}", localDate.toString());

        //Date格式化输出
        LocalDate now = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        String nowStr = now.format(format);
        log.info("formatDate : {}",nowStr);
    }

}
