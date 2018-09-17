package com.zad.server;

import com.zad.handler.EchoServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 描述:
 * ss
 *
 * @author zad
 * @create 2018-09-17 12:59
 */
public class EchoServer {
    private int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        new EchoServer(8080).start();
    }

    public void start() throws Exception {
        EchoServerHandler esh = new EchoServerHandler();
        NioEventLoopGroup nelg = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(nelg)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(port)
                    .childHandler(
                            new ChannelInitializer<SocketChannel>() {
                                @Override
                                protected void initChannel(SocketChannel ch) throws Exception {
                                    ch.pipeline().addLast(esh);
                                }
                            }
                    );

            ChannelFuture sync = b.bind().sync();
            sync.channel().closeFuture().sync();
        } finally {
            nelg.shutdownGracefully().sync();
        }
    }
}
