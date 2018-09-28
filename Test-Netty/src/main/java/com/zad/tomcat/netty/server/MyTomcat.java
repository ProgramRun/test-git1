package com.zad.tomcat.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

import java.util.Objects;

/**
 * 描述:
 * aaa
 *
 * @author zad
 * @create 2018-09-23 19:49
 */
public class MyTomcat {
    public void start(int port) {
        EventLoopGroup bossGroup = null;
        EventLoopGroup workerGroup = null;
        try {
            bossGroup = new NioEventLoopGroup();
            workerGroup = new NioEventLoopGroup();

            ServerBootstrap server = new ServerBootstrap();

            server.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel client) throws Exception {
                            client.pipeline().addLast(new HttpResponseEncoder());
                            client.pipeline().addLast(new HttpRequestDecoder());
                            client.pipeline().addLast(new MyHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);


            System.out.println("服务端启动,监听端口: " + port);
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
            new MyTomcat().start(8989);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
