package com.zad.jedis;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * 描述:
 * jedis string api
 *
 * @author zad
 * @create 2018-09-14 13:01
 */
@Slf4j
public class StringApi {
    private static Jedis jedis = JedisConnection.getConnection();

    public static void main(String[] args) throws InterruptedException {
        // 增加或覆盖key,如果键存在则覆盖,否则为添加
        String set = jedis.set("name", "zad");
        log.info("set 返回结果为->{}", set);

        // 不重复添加
        Long setnx = jedis.setnx("name", "zad");
        log.info("添加是否成功 -> {}", setnx);

        // 增加数据项并设置过期时间
        jedis.setex("name2", 5, "zad2");

        /*
        // 测试失效
        TimeUnit.SECONDS.sleep(2);
        System.out.println(jedis.exists("name2"));

        TimeUnit.SECONDS.sleep(2);
        System.out.println(jedis.exists("name2"));

        TimeUnit.SECONDS.sleep(2);
        System.out.println(jedis.exists("name2"));*/

        // 字符串后添加,返回结果表示value长度
        Long name = jedis.append("name5", "111");
        log.info("append是否成功 -> {}", name);

        // 多个添加
        String mset = jedis.mset("name", "zad", "name1", "zad1");
        log.info("多个添加是否成功 -> {}", mset);

        // 直接读取多个value
        List<String> mget = jedis.mget("name", "name1");
        log.info("读取的value值为 -> {}", mget);

        // 删除多个key
        Long del = jedis.del("name2", "name5");
        log.info("删除项数为 -> {}", del);

        // 更新key对应的value,并返回原数据
        String name1 = jedis.getSet("name", "111");
        log.info("原来的数据为 -> {}", name1);

        // 获取key指定位置字节
        String name2 = jedis.getrange("name", 0, -1);
        String name3 = jedis.getrange("name", 0, 1);
        log.info("{} {}", name2, name3);

        // 对应的value加1
        Long name4 = jedis.incr("name");
        log.info("增加后结果为 -> {}", name4);

        // 对应的value加n
        jedis.incrBy("name", 5);

        // 对应的value -n
        jedis.decrBy("name", 5);

    }
}
