package com.zad.jdk8.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 *
 * @author zad
 * @create 2019-04-24 10:23
 */
public class MapTest {

    public static void main(String[] args) {
        /*Map<String, String> m1 = new ConcurrentHashMap<>();
        Map<String, String> m2 = new ConcurrentSkipListMap<>();
        List<String> l1 = new CopyOnWriteArrayList<>();
        Queue<String> q1 = new ConcurrentLinkedQueue<>();
        Queue<String> q2= new ArrayBlockingQueue<>(2);
        Queue<String> q3= new LinkedBlockingQueue<>();
        byte[] allocation1, allocation2;
        allocation1 = new byte[30900*1024];
        allocation2 = new byte[30900*1024];*/
        Map<String, Object> params = new HashMap<>();
        setMaxSize(params,2,Integer.MAX_VALUE);
        System.out.println(params);
    }

    private static void setMaxSize(Map<String, Object> params, int curPage, int pageSize) {
        int res = curPage * pageSize;
        if (res < 0) {
            params.put("maxSize", pageSize);
        } else {
            params.put("maxSize", res);
        }
    }
}
