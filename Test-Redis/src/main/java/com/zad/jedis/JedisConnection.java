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

    private static final Jedis jedis = null;

    public static Jedis getConnection() {
        if (jedis == null) {
            synchronized (JedisConnection.class) {
                if (jedis == null) {
                    return new Jedis("localhost", 6379);
                } else {
                    return jedis;
                }
            }
        }
        return null;
    }
}
