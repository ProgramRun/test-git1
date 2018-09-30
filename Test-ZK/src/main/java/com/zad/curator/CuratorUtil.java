package com.zad.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 描述:
 * Curator Util
 *
 * @author zad
 * @create 2018-09-09 20:51
 */
public class CuratorUtil {
    private CuratorUtil() {
        throw new AssertionError("Util禁止实例化");
    }

    private static String ZK_CONNECTION = "192.168.220.131:2181,192.168.220.132:2181,192.168.220.133:2181";

    public static CuratorFramework getConnection() {
        CuratorFramework build = CuratorFrameworkFactory.builder()
                .connectString(ZK_CONNECTION)
                .connectionTimeoutMs(10000)
                .sessionTimeoutMs(10000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
        build.start();
        return build;
    }
}
