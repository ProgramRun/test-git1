package com.zad.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 描述:
 * Curator Operation Demo
 *
 * @author zad
 * @create 2018-09-09 16:04
 */
public class OperationDemo {
    private static String ZK_CONNECTION = "192.168.220.131:2181,192.168.220.132:2181,192.168.220.133:2181";

    private static CuratorFramework getConnection() {
        CuratorFramework build = CuratorFrameworkFactory.builder().connectString(ZK_CONNECTION)
                .connectionTimeoutMs(10000)
                .sessionTimeoutMs(10000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
        build.start();
        return build;
    }

    public static void main(String[] args) throws InterruptedException {
        CuratorFramework curatorFrameWork = getConnection();
        // 创建节点
        /*try {
            String path = curatorFrameWork.create().creatingParentContainersIfNeeded().
                    withMode(CreateMode.PERSISTENT).
                    forPath("/curator/t1", "t1".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        System.out.println("congratulations, you create a node");

        // 删除节点
        /*try {
            curatorFrameWork.delete().deletingChildrenIfNeeded().
                    withVersion(-1).
                    forPath("/zad2");
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        System.out.println("now you delete it");

        // 获取数据
        /*Stat stat = new Stat();
        byte[] bytes = new byte[0];
        try {
            bytes = curatorFrameWork.getData().storingStatIn(stat).forPath("/zad1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("now u get som information " + new String(bytes) + "  " + stat);*/

        // 修改数据
        /*try {
            Stat stat = curatorFrameWork.setData().forPath("/zad1", "1992".getBytes());
            System.out.println("now u get the new data");
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        // 异步操作
        /*ExecutorService executorService = Executors.newFixedThreadPool(1);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            String path = curatorFrameWork.create()
                    .creatingParentContainersIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .inBackground(new BackgroundCallback() {
                        public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
                            System.out.println(Thread.currentThread().getName() + " -> resultCode:" + event.getResultCode()
                                    + event.getType());
                            countDownLatch.countDown();
                        }
                    }, executorService)
                    .forPath("/test/t1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        countDownLatch.await();
        executorService.shutdown();*/

        // 事务操作


    }
}
