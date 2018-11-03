package com.zad.JDK8.reflect;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 描述:
 *
 * @author zad
 * @create 2018-11-03 9:49
 */
public class CGLibProxyDemo implements MethodInterceptor {
    private Object targetObject;

    public Object newProxy(Object obj) {
        this.targetObject = obj;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        enhancer.setCallback(this);
        Object proxyObj = enhancer.create();
        return proxyObj;
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args,
                            MethodProxy methodProxy) throws Throwable {

        checkParam();
        return method.invoke(targetObject, args);
    }

    private void checkParam() {
        System.out.println("param is OK");
    }
}
