package com.zad.java;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * 描述:
 * ZK Java API
 *
 * @author zad
 * @create 2018-09-07 6:01
 */
@Slf4j
public class JavaAPI {
    private static String ZK_CONNECTION = "192.168.220.131:2181,192.168.220.132:2181,192.168.220.133:2181";

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private static ZooKeeper zk;

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        zk = new ZooKeeper(ZK_CONNECTION, 2000, new Watcher() {
            public void process(WatchedEvent event) {
                if (event.getState() == Event.KeeperState.SyncConnected) {
                    countDownLatch.countDown();
                    System.out.println(event.getState());
                } else if (event.getType() == Event.EventType.NodeDataChanged) {
                    try {
                        log.info("路径 : {}, 改变后路径 : {}", event.getPath(), zk.getData(event.getPath(), true, new Stat()).toString());
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (event.getType() == Event.EventType.NodeChildrenChanged) {

                } else if (event.getType() == Event.EventType.NodeCreated) {

                } else if (event.getType() == Event.EventType.NodeDeleted) {

                }
            }
        });
        countDownLatch.await();
        System.out.println(zk.getState());
        String res = zk.create("/zad/z2", "1990".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println(res);
    }
}
