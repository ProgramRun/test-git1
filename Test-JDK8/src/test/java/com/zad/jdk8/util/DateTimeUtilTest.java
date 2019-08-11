package com.zad.jdk8.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

@Slf4j
public class DateTimeUtilTest {
    private Date date;

    @BeforeEach
    void init() {
        date = new Date();
    }

    @Test
    void t1() {
        log.info("everything is fine -> {}", "nnnnn");
        log.error("everything is fine -> {}", "nnnnn");

        PriorityQueue<LocalDate> pq = new PriorityQueue<>();

        pq.add(LocalDate.of(1990, 2, 5));
        pq.add(LocalDate.of(1990, 2, 6));
        pq.add(LocalDate.of(1990, 2, 7));
        pq.add(LocalDate.of(1990, 2, 8));

        System.out.println("====== start ======");
        for (LocalDate time : pq) {
            System.out.println(time);
        }

        System.out.println("===== remove =====");

        while (!pq.isEmpty()) {
            System.out.println(pq.remove());
        }

        System.out.println(pq.size());
    }


    @Test
    void t2() {
        Scanner s1 = new Scanner(System.in);
        while (s1.hasNext()) {
            String s = s1.next();
            if (StringUtils.equals(s, "stop")) {
                break;
            }
            System.err.println(s);
        }
        s1.close();
    }

    @Test
    void t3() {
        String s = "中国abc11111";

       /* for (int i = 0; i < s.length(); i++) {
            System.out.println('0' < s.charAt(i) && '9' > s.charAt(i));
        }*/

        int b = 0b111;
        int d = 111;
        int e = 0111;
        int x = 0x111;
        System.out.println(b);
        System.out.println(d);
        System.out.println(e);
        System.out.println(x);
    }

    @Test
    void t4() throws ParseException {
        Date d1 = DateUtils.parseDate("1990-02-07 12:00:00","yyyy-MM-dd HH:mm:ss");
        Date d2 = DateUtils.parseDate("1990-02-07 12:00:00","yyyy-MM-dd HH:mm:ss");
        Assertions.assertEquals(d1,d2);
        BigDecimal b1 = null;
        BigDecimal b2 = new BigDecimal(1);

        System.out.println(Objects.compare(b1, b2, new Comparator<BigDecimal>() {
            @Override
            public int compare(BigDecimal o1, BigDecimal o2) {
                return 0;
            }
        }));
    }
}
