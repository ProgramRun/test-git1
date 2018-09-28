package com.zad.rpc.consumer;

import com.zad.rpc.api.IRpcCal;
import com.zad.rpc.api.IRpcHello;
import com.zad.rpc.consumer.proxy.RpcProxy;

/**
 * 描述:
 * a
 *
 * @author zad
 * @create 2018-09-27 18:11
 */
public class RpcConsumer {
    public static void main(String[] args) {
        IRpcHello rpcHello = RpcProxy.create(IRpcHello.class);
        String zad = rpcHello.hello("zad");
        System.out.println(zad);
        System.out.println(rpcHello);

        IRpcCal rpcCal = RpcProxy.create(IRpcCal.class);
        int a = 8, b = 4;
        System.out.println(a + "+" + b + " = "+rpcCal.add(a, b));
        System.out.println(a + "-" + b + " = "+rpcCal.sub(a, b));
        System.out.println(a + "*" + b + " = "+rpcCal.multi(a, b));
        System.out.println(a + "/" + b + " = "+rpcCal.div(a, b));
    }
}
