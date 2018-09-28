package com.zad.DistributedLock.javaApiLock;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.util.List;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 * zz
 *
 * @author zad
 * @create 2018-09-10 19:50
 */
public class DistributedLock {

    private static String ROOT_LOCKS = "/LOCKS";

    private ZooKeeper zk;

    private int sessionTimeout;

    private static final byte[] data = {1, 2};

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    private String lockId;

    public DistributedLock() throws InterruptedException {
        this.zk = ZKClient.getConnection();
        this.sessionTimeout = ZKClient.getSessionTimeOut();
    }

    // 获取锁
    public boolean lock() {
        try {
            // 创建临时有序节点
            lockId = zk.create(ROOT_LOCKS + "/", data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(Thread.currentThread().getName() + " -> 创建了节点[" + lockId + "],开始竞争锁");

            List<String> children = zk.getChildren(ROOT_LOCKS, true);

            // 排序
            TreeSet<String> strings = new TreeSet<>();
            for (String child : children) {
                strings.add(ROOT_LOCKS + "/" + child);
            }

            String first = strings.first();

            if (first.equals(lockId)) {
                System.out.println(Thread.currentThread().getName() + "成功获取锁,lock节点为:[ " + lockId + " ]");
            }

            // 监视上一个节点的删除事件
            SortedSet<String> strings1 = strings.headSet(lockId);
            if (!strings1.isEmpty()) {
                String last = strings1.last();
                zk.exists(last, new LockWatcher(this.countDownLatch));
                countDownLatch.await(sessionTimeout, TimeUnit.MILLISECONDS);
                System.out.println(Thread.currentThread().getName() + "成功获取锁,lock节点为:[ " + lockId + " ]");
            }

            return true;
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 释放锁
    public  boolean unlock() {
        System.out.println(Thread.currentThread().getName() + "开始释放锁:[ " + lockId + " ]");
        try {
            zk.delete(lockId, -1);
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                DistributedLock lock = null;
                try {
                    lock = new DistributedLock();
                    countDownLatch.countDown();
                    countDownLatch.await();
                    lock.lock();
                    Thread.sleep(random.nextInt(500));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }).start();
        }
    }
}
