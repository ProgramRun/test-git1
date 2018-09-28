package com.zad.rpc.provider;

import com.zad.rpc.api.IRpcCal;

/**
 * 描述:
 *
 * @author zad
 * @create 2018-09-28 6:47
 */
public class RpcCal implements IRpcCal {
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int sub(int a, int b) {
        return a - b;
    }

    @Override
    public int multi(int a, int b) {
        return a * b;
    }

    @Override
    public int div(int a, int b) {
        return a / b;
    }
}
