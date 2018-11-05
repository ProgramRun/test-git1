package com.zad.jdk8.reflect;

/**
 * 描述:
 *
 * @author zad
 * @create 2018-11-02 23:33
 */
public class PersonManagerImpl implements PersonManager {
    @Override
    public void addPerson(String id, String password) {
        System.out.println("添加用户成功");
    }

    @Override
    public void delPerson(String id) {
        System.out.println("删除用户成功");
    }
}
