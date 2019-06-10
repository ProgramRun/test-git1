package com.zad.jdk8.datetime;

import java.util.HashMap;
import java.util.Map;

public class DateTime {
    public static void main(String[] args) {
        Map<String, Integer> req = new HashMap<>();
        for (int i = 0; i < 200; i++) {
            req.put(i + "", i);
        }

        String[] res = new String[req.size()];
        int index = 0;
        for (String id : req.keySet()) {
            res[index++] = id;
        }
        System.out.println("length: " + res.length);

        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }
}
