package com.zad.rpc.consumer.proxy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 描述:
 *
 * @author zad
 * @create 2018-09-27 19:34
 */
public class ProxyHandler extends ChannelInboundHandlerAdapter {

    private Object result;

    public Object getResult() {
        return this.result;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        this.result = msg;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }
}
