package com.zad.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Objects;

/**
 * 描述:
 *
 * @author zad
 * @create 2018-09-29 15:07
 */
public class RedisManager {

    private static JedisPool jedisPool;

    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPool = new JedisPool(jedisPoolConfig, "192.168.220.131", 6379);
    }

    public static Jedis getJedis() throws Exception {
        if (!Objects.isNull(jedisPool)) {
            Jedis jedis = jedisPool.getResource();
            jedis.auth("111111");
            return jedis;
        }

        throw new Exception("Jedis was not init");
    }
}
