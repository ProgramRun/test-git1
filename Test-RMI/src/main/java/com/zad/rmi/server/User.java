package com.zad.rmi.server;

import java.io.Serializable;

/**
 * 描述:
 * user
 *
 * @author zad
 * @create 2018-09-04 22:21
 */
public class User implements Serializable {
    private static final long serialVersionUID = -5424989669184969407L;
    private String name;
    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
