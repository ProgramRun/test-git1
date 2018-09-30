package com.zad.jedis;

import redis.clients.jedis.Jedis;

/**
 * 描述:
 * redis connection
 *
 * @author zad
 * @create 2018-09-14 13:03
 */
public class JedisConnection {
    private JedisConnection() {
        throw new AssertionError("禁止实例化");
    }

    private static Jedis jedis = null;

    public static Jedis getConnection() {
        if (jedis == null) {
            synchronized (JedisConnection.class) {
                if (jedis == null) {
                    jedis = new Jedis("192.168.220.131", 6379);
                    // 访问密码
                    jedis.auth("111111");
                    return jedis;
                } else {
                    return jedis;
                }
            }
        }
        return null;
    }
}
