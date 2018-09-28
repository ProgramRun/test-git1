package com.zad.chat.handler;

import com.zad.chat.process.MsgProcessor;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * 描述:
 * a
 *
 * @author zad
 * @create 2018-09-26 12:58
 */
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private MsgProcessor processor = new MsgProcessor();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        processor.sendMsg(ctx.channel(), msg.text());
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception { // (2)
        Channel client = ctx.channel();
        String addr = processor.getAddress(client);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception { // (3)
        Channel client = ctx.channel();
        processor.logout(client);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
        Channel client = ctx.channel();
        String addr = processor.getAddress(client);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception { // (6)
        Channel client = ctx.channel();
        String addr = processor.getAddress(client);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        Channel client = ctx.channel();
        String addr = processor.getAddress(client);
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}
