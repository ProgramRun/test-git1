package com.zad.jdk8.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import java.util.Date;

/**
 * 描述:
 * 测试使用
 *
 * @author zad
 * @create 2018-09-09 22:31
 */
@Builder
@Log
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

	private static final int INIT = 0;
	private String givenName;

	private String surName;

	volatile int age = INIT;

	private Gender gender;

	private String eMail;

	private String phone;

	private String address;

	private Date birthday;

}
