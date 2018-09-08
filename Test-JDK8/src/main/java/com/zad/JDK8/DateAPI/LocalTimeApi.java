package com.zad.JDK8.DateAPI;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;

/**
 * 描述:
 * LocalTimeApi
 *
 * @author zad
 * @create 2018-09-08 8:58
 */
@Slf4j
public class LocalTimeApi {
    public static void main(String[] args) {
        LocalTime now = LocalTime.now();
        log.info("LocalTime格式:{}", now);
    }
}
