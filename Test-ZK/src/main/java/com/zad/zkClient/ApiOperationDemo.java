package com.zad.zkClient;

import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 * ZKClientApiOperationDemo
 *
 * @author zad
 * @create 2018-09-09 15:07
 */
@Slf4j
public class ApiOperationDemo {
    private static String ZK_CONNECTION = "192.168.220.131:2181,192.168.220.132:2181,192.168.220.133:2181";

    public static ZkClient getConnection() {
        return new ZkClient(ZK_CONNECTION, 10000);
    }

    public static void main(String[] args) throws InterruptedException {
        ZkClient zk = getConnection();
        zk.createEphemeral("/zad1/27");
        TimeUnit.SECONDS.sleep(1L);
        System.out.println("ephemeral node is created");

        // 递归创建父节点功能
        //zk.createPersistent("/zad2/27", true);
        System.out.println("create path");

        // 递归删除,先获取该路径下所有子节点并删除,然后删除该路径
        //boolean res = zk.deleteRecursive("/zad2/27");

        // 获取子节点
        List<String> children = zk.getChildren("/zad");
        System.out.println(children);

        // 订阅事件
        zk.subscribeDataChanges("/zad/zz2", new IZkDataListener() {

            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println(dataPath + "data is changed to " + data);
            }

            public void handleDataDeleted(String dataPath) throws Exception {

            }
        });

        zk.writeData("/zad/zz2", "119");
        TimeUnit.SECONDS.sleep(2L);
    }
}
