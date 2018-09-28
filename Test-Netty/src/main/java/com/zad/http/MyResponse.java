package com.zad.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.util.internal.StringUtil;

import java.io.UnsupportedEncodingException;

import static io.netty.handler.codec.http.HttpHeaderNames.*;

/**
 * 描述:
 *
 * @author zad
 * @create 2018-09-24 7:35
 */
public class MyResponse {
    private ChannelHandlerContext ctx;
    private HttpRequest r;


    public MyResponse(ChannelHandlerContext ctx, HttpRequest r) {
        this.ctx = ctx;
        this.r = r;
    }

    public void write(String out) throws UnsupportedEncodingException {
        try {

            if (StringUtil.isNullOrEmpty(out)) {
                return;
            }
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(out.getBytes("UTF-8")));

            response.headers().set(CONTENT_TYPE, "text/json");
            response.headers().set(CONTENT_LENGTH, response.content().readableBytes());
            response.headers().set(EXPIRES, 0);

            if (HttpUtil.isKeepAlive(r)) {
                response.headers().set(CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            }

            ctx.write(response);
        } finally {
            ctx.flush();
        }
    }

}
