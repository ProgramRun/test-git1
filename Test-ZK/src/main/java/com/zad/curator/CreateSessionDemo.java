package com.zad.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 描述:
 * 创建会话范例
 *
 * @author zad
 * @create 2018-09-09 15:52
 */
public class CreateSessionDemo {

    private static String ZK_CONNECTION = "192.168.220.131:2181,192.168.220.132:2181,192.168.220.133:2181";

    public static void main(String[] args) {
        // 创建会话两种方式
        // 第一种 普通入参
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(ZK_CONNECTION, 5000, 5000, new ExponentialBackoffRetry(1000, 3));
        curatorFramework.start();

        // 第二种 fluent风格
        CuratorFramework build = CuratorFrameworkFactory.builder().connectString(ZK_CONNECTION)
                .connectionTimeoutMs(5000)
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
        build.start();
        System.out.println("ding dang");
    }
}
