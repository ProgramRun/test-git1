package com.zad.rpc.registry;

import com.zad.rpc.core.msg.InvokeMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 描述:
 *
 * @author zad
 * @create 2018-09-27 18:30
 */
public class RegistryHandler extends ChannelInboundHandlerAdapter {

    public static ConcurrentHashMap<String, Object> registryMap = new ConcurrentHashMap<>();

    private List<String> classCache = new ArrayList<>();

    public RegistryHandler() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        scanClass("com.zad.rpc.provider");
        doRegistry();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        Object res = new Object();
        InvokeMsg req = (InvokeMsg) msg;
        if (registryMap.containsKey(req.getClassName())) {
            Object clazz = registryMap.get(req.getClassName());
            Method m = clazz.getClass().getMethod(req.getMethodName(), req.getParams());
            res = m.invoke(clazz, req.getValues());
        }
        System.out.println(res +" is transfered");
        ctx.writeAndFlush(res);
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private void scanClass(String packageName) {
        URL url = this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.", "/"));

        File dir = new File(url.getFile());

        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                scanClass(packageName + "." + file.getName());
            } else {
                classCache.add(packageName + "." + file.getName().replace(".class", "").trim());
            }

        }
    }

    private void doRegistry() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (classCache.size() == 0) {
            return;
        }

        for (String classname : classCache) {
            Class<?> clazz = Class.forName(classname);
            Class<?> interfaces = clazz.getInterfaces()[0];
            registryMap.put(interfaces.getName(), clazz.newInstance());
        }
    }
}
