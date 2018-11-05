package com.zad.jdk8.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 描述:
 *
 * @author zad
 * @create 2018-11-02 23:35
 */
public class JDKProxyDemo implements InvocationHandler {

    private Object targetObj;

    public Object newProxy(Object obj) {
        targetObj = obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        checkParam();
        return method.invoke(targetObj, args);
    }

    private void checkParam() {
        System.out.println("param is OK");
    }
}
