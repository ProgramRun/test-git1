package com.zad.jdk8.common;

import lombok.Builder;
import lombok.Data;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Objects;

/**
 * 描述:
 * 测试使用
 *
 * @author zad
 * @create 2018-09-09 22:31
 */
@Builder
@Data
@Log
public class Person implements Comparable<Person> {
    private String givenName;

    private String surName;

    private int age;

    private Gender gender;

    private String eMail;

    private String phone;

    private String address;

    private Date birthday;

    public Date getBirthday() {
        return (Date) birthday.clone();
    }

    @Override
    public int compareTo(Person o) {
        if (Objects.isNull(o)) {
            throw new NullPointerException("input is null");
        }

        if (!this.givenName.equals(o.givenName)) {
            return StringUtils.compare(this.givenName, o.givenName);
        }

        return 0;
    }

    public static void main(String[] args) {
        System.out.println(log.getClass());
        log.info("app");
    }
}
