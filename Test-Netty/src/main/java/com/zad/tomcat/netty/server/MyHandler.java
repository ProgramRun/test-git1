package com.zad.tomcat.netty.server;

import com.zad.http.MyRequest;
import com.zad.http.MyResponse;
import com.zad.servlet.MyServlet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;

/**
 * 描述:
 *
 * @author zad
 * @create 2018-09-23 19:58
 */
public class MyHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) msg;

            MyRequest myRequest = new MyRequest(ctx, request);


            MyResponse myResponse = new MyResponse(ctx, request);

            new MyServlet().doGet(myRequest, myResponse);
        }


    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        ctx.fireExceptionCaught(cause);
    }
}
