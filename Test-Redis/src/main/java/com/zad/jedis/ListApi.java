package com.zad.jedis;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * 描述:
 * List api
 *
 * @author zad
 * @create 2018-09-14 14:25
 */
@Slf4j
public class ListApi {
    private static Jedis jedis = JedisConnection.getConnection();

    public static void main(String[] args) {
        /*// 添加一个list,返回结果为操作后集合元素数目
        Long l1 = jedis.lpush("l1", "l0", "l1", "l2");
        log.info("l1中的元素数目为 -> {}", l1);

        // 向list中添加一个元素
        Long l11 = jedis.lpush("l1", "l3");
        log.info("l1中的元素数目为 -> {}", l11);

        // 获取集合中对应的元素,包含start和end元素
        List<String> l12 = jedis.lrange("l1", 0, 3);
        log.info("l1对应的元素为 -> {}", l12);

        // 删除指定count 个value,count为0表示全部删除,为+代表从头往后删,为-代表从尾往头删
        Long lrem = jedis.lrem("l1", 5, "l0");
        log.info("实际删除的数目为 -> {}", lrem);

        // 删除区间外的所有元素
        String l111 = jedis.ltrim("l1", 2, 4);
        log.info("删除是否成功 -> {}", l111);

        // 从list head 弹出元素
        String l1111 = jedis.lpop("l1");
        System.out.println(l1111);

        // 修改指定位置元素
        String l11111 = jedis.lset("l1", 0, "111");*/

        // list的长度
        Long l1 = jedis.llen("l1");
        log.info("list 长度为 -> {}", l1);

        // 获取指定位置元素

        String l11 = jedis.lindex("l1", 1);
        System.out.println(l11);

        // list排序,只能针对数字格式sort
        List<String> l12 = jedis.sort("l1");
        System.out.println(l12);
    }
}
