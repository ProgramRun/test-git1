# Netty简介

参考资料 http://tutorials.jenkov.com/netty/overview.html

![An Netty overview giving an overview of Netty's internal design](http://tutorials.jenkov.com/images/netty/overview-0.png) 





# Overview

## Bootstrap

Bootstrap引导了netty了多个步骤,包括开启线程,开启socket等.

## EventLoopGroup

EventLoopGroup 即为EventLoop的群组.多个EventLoop可以合并为一组.在EventLoopGroup 中的EventLoop共享一些资源,例如线程

## EventLoop

EventLoop为一个循环,持续监听新的事件.每当有新的事件发生时,Event会被发送给对应的事件处理器,例如ChannelHandler.

## SocketChannel

SocketChannel 表示一个TCP连接.Netty客户端与服务端之间交换数据就是通过SocketChannel .

SocketChannel 是被EventLoop所管理,而且一直被同一个EventLoop管理.因为EventLoop始终被相同的线程所执行,那么一个SocketChannel实例始终对应一个线程.不需要再担心同步问题.

## ChannelInitializer

ChannelInitializer 是一种特殊的ChannelHandler,被添加在SocketChannel 里的ChannelPipeline中.当初始化SocketChannel时,ChannelInitializer 被调用.

在SocketChannel初始化完成后,ChannelInitializer 会从SocketChannel 内移出.

## ChannelPipeline

每一个SocketChannel都有一个ChannelPipeline;ChannelPipeline 则含有一个ChannelHandler List.当EventLoop从SocketChannel 中读取数据后,数据会被传递给ChannelPipeline内的第一个ChannelHandler;第一ChannelHandler处理后,可以选择将数据发送给ChannelPipeline 中的下一个ChannelHandler并直到最后一个ChannelHandler.

当向SocketChannel中写数据时,写出的数据也依次通过ChannelPipeline 中的ChannelHandler.

## ChannelHandler

ChannelHandler处理来自SocketChannel的数据和将写入SocketChannel的数据.



Chanel---Scoket

EventLoop--控制流,多线程处理,并发

ChannelFuture---异步通知





# Netty ChannelPipeline

ChannelPipeline是Netty中非常核心的概念;每一个SocketChannel都含有一个ChannelPipeline;ChannelPipeline 则含有一个ChannelHandler List;当数据从SocketChannel中流入流出时,这些ChannelHandler 会被调用.

ChannelHandler 有两个子接口(ChannelInBoundHandler和ChannelOutBoundHandler),可以同时向ChannelHandler 添加2种接口

![The Netty ChannelPipeline design.](http://tutorials.jenkov.com/images/netty/channelpipeline-1.png) 

注意:上图的ChannelInBoundHandler和ChannelOutBoundHandler是位于一个ChannelPipeline中.

![The Netty ChannelPipeline - a ChannelInboundHandler writes data which is processed by all ChannelOutboundHandler instances earlier in the ChannelPipeline.](http://tutorials.jenkov.com/images/netty/channelpipeline-2.png) 

当某一个ChannelInboundHandler要写出数据时,数据会经过所有在ChannelInboundHandler之前的ChannelOutBoundHandler.

## Codecs

Netty有编解码器的概念.codec  可以将字节转为Object或者一些消息实;例如codec  可以将Http请求的字节流转化为Http对象,或将Http响应转化为字节流.

codec 实际上是一个(或两个)ChannelHandler 的实现类.将字节转为对象的codec通常继承了ChannelInboundHandler,而将对象转为字节的codec通常继承了ChannelOutBoundHandler.

# Netty TCP Server

要创建Netty TCP server,需要创建EventLoopGroup,创建并配置ServerBootstrap,创建ChannelInitializer,启动Server

```java
EventLoopGroup group = new NioEventLoopGroup();//1

try{
    ServerBootstrap serverBootstrap = new ServerBootstrap();
    serverBootstrap.group(group);
    serverBootstrap.channel(NioServerSocketChannel.class);//2
    serverBootstrap.localAddress(new InetSocketAddress("localhost", 9999));

    serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast(new HelloServerHandler());
        }
    });
    ChannelFuture channelFuture = serverBootstrap.bind().sync();
    channelFuture.channel().closeFuture().sync();
} catch(Exception e){
    e.printStackTrace();
} finally {
    group.shutdownGracefully().sync();
}
```

## Creating an EventLoopGroup

第一步是创建EventLoopGroup

EventLoopGroup group = new NioEventLoopGroup();

## Creating a ServerBootStrap

第二步是创建并配置ServerBootStrap

```java
ServerBootstrap serverBootstrap = new ServerBootstrap();
serverBootstrap.group(group);
serverBootstrap.channel(NioServerSocketChannel.class);
serverBootstrap.localAddress(new InetSocketAddress("localhost", 9999));
```

## Creating a ChannelInitializer

```java
serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new HelloServerHandler());
    }
});
```

ChannelInitializer 是一个抽象类,每当server端接受了一个TCP 连接请求时,ChannelInitializer 的 intiChannel方法都会被调用

## Start the Server

```java
ChannelFuture channelFuture = serverBootstrap.bind().sync();
```

## The HelloServerHandler

```java
public class HelloServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf inBuffer = (ByteBuf) msg;

        String received = inBuffer.toString(CharsetUtil.UTF_8);
        System.out.println("Server received: " + received);

        ctx.write(Unpooled.copiedBuffer("Hello " + received, CharsetUtil.UTF_8));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
```

每当`HelloServerHandler`  所在的`SocketChannel`  获得数据时,channelRead方法就被调用;当无数据时,调用channelReadComplete;当`SocketChannel` 在发送或读取数据过程中跑出异常,exceptionCaught方法被调用

# Netty TCP Client

要创建 TCP client,需要创建EventLoopGroup,创建并配置Bootstrap,创建ChannelInitializer,启动client

```java
EventLoopGroup group = new NioEventLoopGroup();
try{
    Bootstrap clientBootstrap = new Bootstrap();

    clientBootstrap.group(group);
    clientBootstrap.channel(NioSocketChannel.class);
    clientBootstrap.remoteAddress(new InetSocketAddress("localhost", 9999));
    clientBootstrap.handler(new ChannelInitializer<SocketChannel>() {
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast(new ClientHandler());
        }
    });
    ChannelFuture channelFuture = clientBootstrap.connect().sync();
    channelFuture.channel().closeFuture().sync();
} finally {
    group.shutdownGracefully().sync();
}
```

