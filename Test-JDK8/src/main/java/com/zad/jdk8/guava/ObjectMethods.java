package com.zad.jdk8.guava;

import com.google.common.base.MoreObjects;
import com.zad.jdk8.common.Person;
import lombok.extern.slf4j.Slf4j;

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
        Person p = Person.builder().givenName("zzz").address("sfff").build();
        log.info(p.toString());
        String res = MoreObjects.toStringHelper(Person.class)
                .add("surName", "aaa").add("aa","33").add("dd",null).omitNullValues().toString();
        log.info(res);

    }
}
