package com.zad.jedis;

import com.google.common.collect.Maps;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 描述:
 * HashApi
 *
 * @author zad
 * @create 2018-09-14 15:22
 */
public class HashApi {
    private static Jedis jedis = JedisConnection.getConnection();

    public static void main(String[] args) {
        Person z1 = Person.builder().age(10).name("z1").gender(Gender.MAN).build();
        Person z2 = Person.builder().age(11).name("z2").gender(Gender.MAN).build();
        Person z3 = Person.builder().age(12).name("z3").gender(Gender.MAN).build();
        HashMap<String, String> map = Maps.newHashMap();

        map.put("z1", "111");
        map.put("z2", "222");
        map.put("z3", "333");

        // 添加一个hash
        String hash = jedis.hmset("hash", map);

        // 添加一个元素
        Long hset = jedis.hset("hash", "md", "nnd");
        System.out.println(hset);

        // 获得hash中的所有元素
        Map<String, String> hash1 = jedis.hgetAll("hash");
        System.out.println(hash1);

        // 获得hash中的所有key
        Set<String> hash2 = jedis.hkeys("hash");
        System.out.println(hash2);

        // 获得hash中所有的value
        List<String> hash3 = jedis.hvals("hash");
        System.out.println(hash3);

        // hash中对应的key添加value,仅针对数字
        Long aLong = jedis.hincrBy("hash", "z1", 5);
        System.out.println(aLong);

        // 删除一个或多个元素
        Long hdel = jedis.hdel("hash", "z1", "z2");
        System.out.println(hdel);

        // 获取hash中元素个数
        Long hash4 = jedis.hlen("hash");
        System.out.println(hash4);

        // hash中是否存在key
        Boolean hexists = jedis.hexists("hash", "z1");
        System.out.println(hexists);

        List<String> hmget = jedis.hmget("hash", "z1", "md");
        System.out.println(hmget);
    }
}
