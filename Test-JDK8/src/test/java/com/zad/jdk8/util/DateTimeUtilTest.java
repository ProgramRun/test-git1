package com.zad.jdk8.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

@Slf4j
public class DateTimeUtilTest {
    private Date date;

    @BeforeEach
    void init() {
        date = new Date();
    }

    @Test
    void t1() {
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
        Map<String, Integer> map = new HashMap<>();
        map.merge("a", 1, Integer::sum);
        System.out.println(map.get("a"));

        Map<String,String> m2= new LinkedHashMap<>();

        m2.put("a","aa");
    }
}
