package logback;


import lombok.extern.slf4j.Slf4j;

/**
 * @Author waiter
 * @Date 2020/7/21 0021 18:44
 * @Version 1.0
 * @Description
 */
@Slf4j
public class LogBackTest {

    public static void main(String[] args) {
        log.trace("log trace");
        log.debug("log debug");
        log.info("log info");
        log.warn("log warn");
        log.error("log error");
    }

}
