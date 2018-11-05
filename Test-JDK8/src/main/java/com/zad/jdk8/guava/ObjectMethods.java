package com.zad.jdk8.guava;

import com.google.common.base.MoreObjects;
import com.zad.jdk8.common.Person;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * 描述:
 * ObjectMethods
 *
 * @author zad
 * @create 2018-09-17 9:51
 */
@Slf4j
public class ObjectMethods {
    public static void main(String[] args) {
        // JDK7 以后在java.util包内集成了该功能
        boolean equals = Objects.equals("a", "a");
        log.info("校验对象是否相等 -> {}", equals);

        boolean equals1 = Objects.equals(null, null);

        log.info("校验对象是否相等 -> {}", equals1);


        String res = MoreObjects.toStringHelper(Person.builder().givenName("zzz").address("sfff").build())
                .add("surName", "aaa").toString();
        System.out.println(res);
    }
}
