package com.zad.zkClient;

import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.ZkClient;

/**
 * 描述:
 * ZKClientApi
 *
 * @author zad
 * @create 2018-09-09 14:55
 */
@Slf4j
public class ZKClientApi {

    private static String ZK_CONNECTION = "192.168.220.131:2181,192.168.220.132:2181,192.168.220.133:2181";

    public static void main(String[] args) {
        // 创建会话
        ZkClient zk = new ZkClient(ZK_CONNECTION, 10000);
        System.out.println("right now");
    }
}
