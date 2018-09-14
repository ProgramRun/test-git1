package com.zad.jedis;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 * JedisKeyDemo
 *
 * @author zad
 * @create 2018-09-14 12:34
 */
@Slf4j
public class JedisKeyApi {
    private static Jedis jedis = JedisConnection.getConnection();

    public static void main(String[] args) throws InterruptedException {
        // 判断某个键是否存在
        Boolean name = jedis.exists("name");
        log.info("key \"name\" 是否存在 -> {}", name);

        // 新增键值对
        String set = jedis.set("name", "zad");
        log.info("新增键值对 -> {}", set);

        // 获取所有键值对
        Set<String> keys = jedis.keys("*");
        log.info("所有的key为 -> {}", keys);

        // 删除键,返回结果代表影响条数
        Long name1 = jedis.del("name1");
        log.info("删除条数为 -> {}", name1);

        // 设置key过期时间
        Long name11 = jedis.expire("name", 10);
        log.info(name11.toString());

        TimeUnit.SECONDS.sleep(2L);

        // 获取键的剩余存活时间 ttl time to live
        Long name2 = jedis.ttl("name");
        log.info("key 剩余存活时间为 -> {}", name2);

        // 移出key的生存时间限制,返回结果为影响条数
        Long name3 = jedis.persist("name0");
        log.info("影响条数为 -> {}", name3);

        // 查看key对应的value类型
        String name4 = jedis.type("name");
        log.info("key对应的类型为 -> {}", name4);
    }
}
