package com.zad.JDK8.DateTimeTest;

import com.zad.JDK8.DateAPI.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

@Slf4j
public class DateTimeUtilTest {
    private static Date date;

    @Before
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

        String d3 = DateTimeUtil.format(date, "HH:mm:ss");
        log.info("yyyy/MM/dd hh:mm:ss --> {}", d3);


        // String --> Date
        Date date = DateTimeUtil.parseDate("1990/02/07 12:12:12", "yyyy/MM/dd HH:mm:ss");
        log.info("StringToDate --> {}", date);

    }
}
