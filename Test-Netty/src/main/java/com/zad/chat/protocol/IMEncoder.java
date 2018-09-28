package com.zad.chat.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * 描述:
 * 将消息转为字节流
 * @author zad
 * @create 2018-09-26 13:18
 */
public class IMEncoder extends MessageToByteEncoder<IMMessage> {
    @Override
    protected void encode(ChannelHandlerContext ctx, IMMessage msg, ByteBuf out)
            throws Exception {
        out.writeBytes(new MessagePack().write(msg));
    }

    public String encode(IMMessage msg) {
        if (null == msg) {
            return "";
        }
        String prex = "[" + msg.getCmd() + "]" + "[" + msg.getTime() + "]";
        if (IMP.LOGIN.getName().equals(msg.getCmd()) ||
                IMP.CHAT.getName().equals(msg.getCmd()) ||
                IMP.FLOWER.getName().equals(msg.getCmd())) {
            prex += ("[" + msg.getSender() + "]");
        } else if (IMP.SYSTEM.getName().equals(msg.getCmd())) {
            prex += ("[" + msg.getOnline() + "]");
        }
        if (!(null == msg.getContent() || "".equals(msg.getContent()))) {
            prex += (" - " + msg.getContent());
        }
        return prex;
    }
}
