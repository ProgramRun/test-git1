package com.zad.jedis;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * 描述:
 * Set api
 *
 * @author zad
 * @create 2018-09-14 14:54
 */
public class SetApi {
    private static Jedis jedis = JedisConnection.getConnection();

    public static void main(String[] args) {
        /*// 新建set
        Long sadd = jedis.sadd("set", "a", "b", "d", "c");
        System.out.println("添加结果为" + sadd);

        // 获得set所有value
        Set<String> set = jedis.smembers("set");
        System.out.println(set);

        // 删除某个value
        Long srem = jedis.srem("set", "a", "f");
        System.out.println("删了" + srem + "个");

        // 随机弹出一个
        String set1 = jedis.spop("set");
        System.out.println("弹出元素为: " + set1);

        // 获取set长度
        Long set1 = jedis.scard("set");
        System.out.println(set1);*/

        // 将元素从set1剪切到set2,返回值为成功剪切个数
        Long smove = jedis.smove("set", "set1", "d");
        System.out.println(smove);

        // 获取多个set的交集
        Set<String> sinter = jedis.sinter("set", "set1");
        System.out.println(sinter);

        // 获取多个set的并集
        Set<String> sunion = jedis.sunion("set", "set1");
        System.out.println(sunion);

        // set的差集,将其余set*中包含的set元素从set中剔除所得到的,及返回的必然是第一个set中的元素,0个或多个
        Set<String> sdiff = jedis.sdiff("set", "set1");
        System.out.println(sdiff);
    }
}
