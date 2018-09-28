package com.zad.chat.server;

import com.zad.chat.handler.HttpHandler;
import com.zad.chat.handler.SocketHandler;
import com.zad.chat.handler.WebSocketHandler;
import com.zad.chat.protocol.IMDecoder;
import com.zad.chat.protocol.IMEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.util.Objects;

/**
 * 描述:
 * aaa
 *
 * @author zad
 * @create 2018-09-26 5:26
 */
public class ChatServer {

    public void start(int port) {
        EventLoopGroup bossGroup = null;
        EventLoopGroup workerGroup = null;
        try {
            // boss 线程
            bossGroup = new NioEventLoopGroup();
            // worker 线程
            workerGroup = new NioEventLoopGroup();

            // 启动引擎
            ServerBootstrap server = new ServerBootstrap();

            // 主从模型
            server.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();

                            /** 解析自定义协议 */
                            pipeline.addLast(new IMDecoder());
                            pipeline.addLast(new IMEncoder());
                            pipeline.addLast(new SocketHandler());

                            /** 解析Http请求 */
                            pipeline.addLast(new HttpServerCodec());
                            //主要是将同一个http请求或响应的多个消息对象变成一个 fullHttpRequest完整的消息对象
                            pipeline.addLast(new HttpObjectAggregator(64 * 1024));
                            //主要用于处理大数据流,比如一个1G大小的文件如果你直接传输肯定会撑暴jvm内存的 ,加上这个handler我们就不用考虑这个问题了
                            pipeline.addLast(new ChunkedWriteHandler());
                            pipeline.addLast(new HttpHandler());

                            /** 解析WebSocket请求 */
                            pipeline.addLast(new WebSocketServerProtocolHandler("/im"));
                            pipeline.addLast(new WebSocketHandler());

                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);


            System.out.println("服务端启动,监听端口: " + port);
            // 等待客户端连接
            ChannelFuture sync = server.bind(port).sync();

            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(bossGroup)) {
                bossGroup.shutdownGracefully();
            }

            if (Objects.nonNull(workerGroup)) {
                workerGroup.shutdownGracefully();
            }
        }


    }

    public static void main(String[] args) {
        try {
            new ChatServer().start(80);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
