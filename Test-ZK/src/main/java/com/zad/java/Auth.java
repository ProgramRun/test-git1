package com.zad.java;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * 描述:
 * ZK 权限测试
 *
 * @author zad
 * @create 2018-09-09 13:07
 */
@Slf4j
public class Auth implements Watcher {
    private static String ZK_CONNECTION = "192.168.220.131:2181,192.168.220.132:2181,192.168.220.133:2181";

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private static ZooKeeper zk;

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        Auth watcher = new Auth();
        zk = new ZooKeeper(ZK_CONNECTION, 2000, watcher);
        countDownLatch.countDown();

        zk.delete("/auth1", -1);
    }

    public void process(WatchedEvent event) {
        if (event.getState() == Event.KeeperState.SyncConnected) {
            if (Event.EventType.None == event.getType() && null == event.getPath()) {
                countDownLatch.countDown();
                System.out.println("OK you're connected to server");
            } else if (Event.EventType.NodeDataChanged == event.getType()) {
                try {
                    System.out.println("node date changed");
                    log.info("data changed from {} to {}", event.getPath(), zk.getData(event.getPath(), true, new Stat()));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (Event.EventType.NodeCreated == event.getType()) {
                System.out.println("create a new node");
            } else if (Event.EventType.NodeDeleted == event.getType()) {
                System.out.println("huhu 再见" + event.getPath());
            }
        }
    }
}
