package com.zad.rpc.consumer.proxy;

import com.zad.rpc.core.msg.InvokeMsg;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 描述:
 *
 * @author zad
 * @create 2018-09-27 19:18
 */
public class RpcProxy {

    public static <T> T create(Class<?> clazz) {

        MethodProxy methodProxy = new MethodProxy(clazz);

        T o = (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, methodProxy);
        return o;
    }


}

class MethodProxy implements InvocationHandler {

    private Class<?> clazz;

    public MethodProxy(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            return rpcInvoke(method, args);
        }
    }


    private Object rpcInvoke(Method method, Object[] args) {
        InvokeMsg invokeMsg = new InvokeMsg();

        invokeMsg.setClassName(this.clazz.getName());
        invokeMsg.setMethodName(method.getName());
        invokeMsg.setParams(method.getParameterTypes());
        invokeMsg.setValues(args);


        NioEventLoopGroup group = new NioEventLoopGroup();
        final ProxyHandler handler = new ProxyHandler();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));

                            ch.pipeline().addLast(new LengthFieldPrepender(4));

                            ch.pipeline().addLast("encoder", new ObjectEncoder());

                            ch.pipeline().addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));


                            ch.pipeline().addLast(handler);

                        }
                    });
            ChannelFuture sync = b.connect("localhost", 9000).sync();

            sync.channel().writeAndFlush(invokeMsg).sync();

            sync.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

        return handler.getResult();
    }
}