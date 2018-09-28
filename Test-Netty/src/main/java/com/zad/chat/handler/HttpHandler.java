package com.zad.chat.handler;

import io.netty.channel.*;
import io.netty.handler.codec.http.*;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * 描述:
 * aa
 *
 * @author zad
 * @create 2018-09-26 5:34
 */
public class HttpHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private URL baseURL = HttpHandler.class.getProtectionDomain().getCodeSource().getLocation();

    private String WEB_ROOT = "webroot";

    private File getResource(String filename) throws URISyntaxException {
        String path = baseURL.toURI() + WEB_ROOT + "/" + filename;
        path = !path.startsWith("file:") ? path : path.substring(5);
        path.replaceAll("//", "/");
        return new File(path);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        // 获取客户端uri
        String uri = request.uri();

        RandomAccessFile file = null;
        try {
            String page = uri.equals("/") ? "chat.html" : uri;
            file = new RandomAccessFile(getResource(page), "r");
        } catch (Exception e) {
            ctx.fireChannelRead(request.retain());
            return;
        }

        HttpResponse response = new DefaultHttpResponse(request.protocolVersion(), HttpResponseStatus.OK);

        String contentType = "text/html;";
        if (uri.endsWith(".css")) {
            contentType = "text/css;";
        } else if (uri.endsWith(".js")) {
            contentType = "text/javascript;";
        } else if (uri.toLowerCase().matches("(jpg|png|gif)$")) {
            String ext = uri.substring(uri.lastIndexOf("."));
            contentType = "image/" + ext;
        }

        response.headers().set(HttpHeaderNames.CONTENT_TYPE, contentType + "charset=utf-8;");

        boolean keepAlive = HttpUtil.isKeepAlive(request);

        if (keepAlive) {
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, file.length());
            response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        }

        ctx.write(response);

        ctx.write(new DefaultFileRegion(file.getChannel(), 0, file.length()));

        ChannelFuture channelFuture = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);

        if (!keepAlive) {
            channelFuture.addListener(ChannelFutureListener.CLOSE);
        }

        file.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        Channel client = ctx.channel();
        //System.out.println(client);
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }

}
