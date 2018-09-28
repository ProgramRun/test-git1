package com.zad.rpc.provider;

import com.zad.rpc.api.IRpcHello;

/**
 * 描述:
 *
 * @author zad
 * @create 2018-09-27 18:12
 */
public class RpcHello implements IRpcHello {
    @Override
    public String hello(String name) {
        return name + " says hello";
    }
}
