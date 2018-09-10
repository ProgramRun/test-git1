package com.zad.DistributedLock.javaApiLock;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * 描述:
 * ZKClient
 *
 * @author zad
 * @create 2018-09-10 19:45
 */
public class ZKClient {
    private static String ZK_CONNECTION = "192.168.220.131:2181,192.168.220.132:2181,192.168.220.133:2181";

    private static int sessionTimeOut = 10000;

    public static ZooKeeper getConnection() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zk = null;
        try {
            zk = new ZooKeeper(ZK_CONNECTION, sessionTimeOut, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getState() == Event.KeeperState.SyncConnected) {
                        countDownLatch.countDown();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        countDownLatch.await();
        return zk;
    }

    public static int getSessionTimeOut() {
        return 10000;
    }
}
