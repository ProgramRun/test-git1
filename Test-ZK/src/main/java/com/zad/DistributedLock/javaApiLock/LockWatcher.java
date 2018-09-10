package com.zad.DistributedLock.javaApiLock;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

/**
 * 描述:
 * zz
 *
 * @author zad
 * @create 2018-09-10 20:09
 */
public class LockWatcher implements Watcher {
    private CountDownLatch countDownLatch;

    public LockWatcher(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void process(WatchedEvent event) {
        if (event.getType() == Event.EventType.NodeDeleted) {
            this.countDownLatch.countDown();
        }
    }
}
