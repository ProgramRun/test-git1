package com.zad.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * 描述:
 * CuratorSelector
 *
 * @author zad
 * @create 2018-09-11 21:22
 */
public class CuratorSelector {
    private static String ZK_CONNECTION = "192.168.220.131:2181,192.168.220.132:2181,192.168.220.133:2181";

    private static String MASTER_PATH = "/curator_master_path";

    public static void main(String[] args) {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(ZK_CONNECTION)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();

        LeaderSelector leaderSelector = new LeaderSelector(curatorFramework, MASTER_PATH, new LeaderSelectorListenerAdapter() {
            @Override
            public void takeLeadership(CuratorFramework client) throws Exception {
                System.out.println("获得leader成功");
                TimeUnit.SECONDS.sleep(2L);
            }
        });

        leaderSelector.autoRequeue();

        leaderSelector.start();
    }

}
