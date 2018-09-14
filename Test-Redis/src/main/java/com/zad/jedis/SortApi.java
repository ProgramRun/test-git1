package com.zad.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;

import java.util.List;

/**
 * 描述:
 * 排序操作
 *
 * @author zad
 * @create 2018-09-14 15:39
 */
public class SortApi {
    private static Jedis jedis = JedisConnection.getConnection();

    public static void main(String[] args) {
        SortingParams sp = new SortingParams();
        List<String> l1 = jedis.sort("l1", sp.alpha());
        System.out.println(l1);
    }

}
