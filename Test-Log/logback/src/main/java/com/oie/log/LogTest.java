package com.oie.log;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author waiter
 * @Date 2020/7/22 0022 18:26
 * @Version 1.0
 * @Description
 */
@Slf4j
public class LogTest {

    public static void main(String[] args) {
        log.trace("trace log");
        log.debug("debug log");
        log.info("info log");
        log.warn("warn log");
        log.error("error log");
        //fluentLog();
    }


    public static void fluentLog() {
        int newT = 15;
        int oldT = 16;

        // using traditional API
        log.debug("Temperature set to {}. Old temperature was {}.", newT, oldT);

        // using fluent API, add arguments one by one and then log message
        log.atDebug().addArgument(newT).addArgument(oldT).log("Temperature set to {}. Old temperature was {}.");

        // using fluent API, log message with arguments
        log.atDebug().log("Temperature set to {}. Old temperature was {}.", newT, oldT);

        // using fluent API, add one argument and then log message providing one more argument
        log.atDebug().addArgument(newT).log("Temperature set to {}. Old temperature was {}.", oldT);

        // using fluent API, add one argument with a Supplier and then log message with one more argument.
        // Assume the method t16() returns 16.
        log.atDebug().addArgument(LogTest::t16).log( "Temperature set to {}. Old temperature was {}.", oldT);
    }


    public static int t16() {
        return 16;
    }
}
