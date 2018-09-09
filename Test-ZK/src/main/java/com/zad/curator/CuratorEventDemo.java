package com.zad.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.TimeUnit;

/**
 * 描述:
 * EventDemo
 *
 * @author zad
 * @create 2018-09-09 20:54
 */

public class CuratorEventDemo {
    public static void main(String[] args) throws Exception {
        CuratorFramework connection = CuratorUtil.getConnection();

        /**
         * 三种 watcher 监听
         * PathCache 监视一个路径下子节点的创建,删除更新
         * NodeCache 监听一个节点的创建,更新,删除
         * TreeCache PathCache+NodeCache的合体
         */

        // NodeCache变化
        /*final NodeCache nodeCache = new NodeCache(connection, "/curator", false);

        nodeCache.start(true);

        nodeCache.getListenable().addListener(() -> System.out.println("节点数据发生变化,变化后的结果为 : " + new String(nodeCache.getCurrentData().getData())));

        connection.setData().forPath("/curator", "zad".getBytes());

        TimeUnit.SECONDS.sleep(3L);*/

        // PathChildrenCache
        /*PathChildrenCache cache = new PathChildrenCache(connection, "/event", false);
        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        cache.getListenable().addListener((curatorFramework, pathChildrenCache) -> {
            switch (pathChildrenCache.getType()) {
                case CHILD_ADDED:
                    System.out.println("增加子节点");
                    break;
                case CHILD_REMOVED:
                    System.out.println("删除子节点");
                    break;
                case CHILD_UPDATED:
                    System.out.println("更新数据");
                    break;
                default:
                    break;
            }
        });

        //connection.create().withMode(CreateMode.PERSISTENT).forPath("/event", "event".getBytes());

        TimeUnit.SECONDS.sleep(1L);

        connection.create().withMode(CreateMode.PERSISTENT).forPath("/event/event1", "event".getBytes());

        TimeUnit.SECONDS.sleep(1L);

        connection.setData().forPath("/event/event1", "111".getBytes());

        connection.delete().forPath("/event/event1");

        TimeUnit.SECONDS.sleep(2L);*/

    }
}
