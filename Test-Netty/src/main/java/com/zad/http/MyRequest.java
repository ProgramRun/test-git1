package com.zad.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 描述:
 * zaa
 *
 * @author zad
 * @create 2018-09-24 7:35
 */
public class MyRequest {
    private ChannelHandlerContext ctx;
    private HttpRequest request;


    public MyRequest(ChannelHandlerContext ctx, HttpRequest request) {
        this.ctx = ctx;
        this.request = request;
    }

    public String getUri() {
        return request.uri();
    }

    public String getMethod() {
        return request.method().name();
    }

    public Map<String, List<String>> getParameters() {
        QueryStringDecoder decoder = new QueryStringDecoder(request.uri());
        return decoder.parameters();
    }

    public String getParameter(String name) {
        Map<String, List<String>> map = getParameters();
        List<String> list = map.get(name);
        if (Objects.isNull(list)) {
            return null;
        }
        return list.get(0);
    }
}
