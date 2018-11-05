package com.zad.jdk8.datetime;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;

/**
 * 描述:
 * InstantApi
 *
 * @author zad
 * @create 2018-09-08 8:57
 */
@Slf4j
public class InstantApi {
    public static void main(String[] args) {
        Instant now = Instant.now();
        log.info("Instant输出格式 : {}", now.toString());
        Instant parse = Instant.parse("2018-09-05T04:28:17.265Z");
        log.info("String-->Instant : {}", parse);
    }
}
