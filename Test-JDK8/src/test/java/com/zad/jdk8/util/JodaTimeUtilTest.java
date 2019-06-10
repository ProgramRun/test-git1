package com.zad.jdk8.util;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static com.zad.jdk8.util.JodaTimeUtil.DEFAULT_DATE;

class JodaTimeUtilTest {

    @Test
    void strToDate() {
        System.out.println(JodaTimeUtil.strToDate("1990-02-01", "yyyy-MM-dd"));
    }

    @Test
    void dateToStr() {
        System.out.println(JodaTimeUtil.dateToStr(new Date(), "yyyy"));
    }

    @Test
    void strToDate1() {
        System.out.println(JodaTimeUtil.strToDate("1990-02-07 12:08:02"));
    }

    @Test
    void dateToStr1() {
        String req = "1990-02-07";

        Date d1 = JodaTimeUtil.strToDate(req, DEFAULT_DATE);

        Date d2 = JodaTimeUtil.plusDays(d1, 1);

        System.out.println(JodaTimeUtil.dateToStr(d2,DEFAULT_DATE));
    }
}