package com.zad.JDK8.reflect;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 描述:
 *
 * @author zad
 * @create 2018-11-03 9:54
 */
class ReflectTest {

    private PersonManager personManager;

    @BeforeEach
    public void init() {
        personManager = new PersonManagerImpl();
    }

    @Test
    void jdkProxyTest() {
        PersonManager proxy = (PersonManager) new JDKProxyDemo().newProxy(personManager);

        System.out.println("=========添加用户测试开始=========");
        proxy.addPerson("1", "111111");
        System.out.println("=========添加用户测试结束=========");
        System.out.println();

        System.out.println("=========删除用户测试开始=========");
        proxy.delPerson("1");
        System.out.println("=========删除用户测试结束=========");

    }

    @Test
    void cglibProxyTest() {
        PersonManager proxy = (PersonManager) new CGLibProxyDemo().newProxy(personManager);
        System.out.println("=========添加用户测试开始=========");
        proxy.addPerson("1", "111111");
        System.out.println("=========添加用户测试结束=========");
        System.out.println();

        System.out.println("=========删除用户测试开始=========");
        proxy.delPerson("1");
        System.out.println("=========删除用户测试结束=========");
    }


}
