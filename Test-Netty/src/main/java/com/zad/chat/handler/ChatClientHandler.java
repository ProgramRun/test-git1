package com.zad.chat.handler;

import com.zad.chat.protocol.IMMessage;
import com.zad.chat.protocol.IMP;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Scanner;

/**
 * 聊天客户端逻辑实现
 *
 * @author Tom
 */
@Slf4j
public class ChatClientHandler extends ChannelInboundHandlerAdapter {

    private ChannelHandlerContext ctx;
    private String nickName;

    public ChatClientHandler(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 启动客户端控制台
     */
    private void session() throws IOException {
        new Thread() {
            @Override
            public void run() {
                IMMessage message = null;
                Scanner scanner = new Scanner(System.in);
                do {
                    if (scanner.hasNext()) {
                        String input = scanner.nextLine();
                        if ("exit".equals(input)) {
                            message = new IMMessage(IMP.LOGOUT.getName(), System.currentTimeMillis(), nickName);
                        } else {
                            message = new IMMessage(IMP.CHAT.getName(), System.currentTimeMillis(), nickName, input);
                        }
                    }
                } while (sendMsg(message));
                scanner.close();
            }
        }.start();
    }

    /**
     * tcp链路建立成功后调用
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;
        IMMessage message = new IMMessage(IMP.LOGIN.getName(), System.currentTimeMillis(), this.nickName);
        sendMsg(message);
        session();
    }

    /**
     * 发送消息
     *
     * @param msg
     * @return
     * @throws IOException
     */
    private boolean sendMsg(IMMessage msg) {
        ctx.channel().writeAndFlush(msg);
        return msg.getCmd().equals(IMP.LOGOUT) ? false : true;
    }

    /**
     * 收到消息后调用
     *
     * @throws IOException
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws IOException {
        IMMessage m = (IMMessage) msg;
    }

    /**
     * 发生异常时调用
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}
