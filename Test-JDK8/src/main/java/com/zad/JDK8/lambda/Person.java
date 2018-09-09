package com.zad.JDK8.lambda;

import lombok.Builder;
import lombok.Data;

/**
 * 描述:
 * 测试使用
 *
 * @author zad
 * @create 2018-09-09 22:31
 */
@Builder
@Data
class Person {
    private String givenName;

    private String surName;

    private int age;

    private Gender gender;

    private String eMail;

    private String phone;

    private String address;


}
