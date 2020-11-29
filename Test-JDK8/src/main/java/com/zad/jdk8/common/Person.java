package com.zad.jdk8.common;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 描述:
 * 测试使用
 *
 * @author zad
 * @create 2018-09-09 22:31
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

	private String givenName;

	private String surName;

	private int age;

	private Gender gender;

	private String eMail;

	private String phone;

	private String address;

	private Date birthday;

}
