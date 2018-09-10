package com.zad.DistributedLock.zkClientApi;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 * zzz
 *
 * @author zad
 * @create 2018-09-10 21:58
 */
public class MasterChooseTest {

    private static String ZK_CONNECTION = "192.168.220.131:2181,192.168.220.132:2181,192.168.220.133:2181";

    public static void main(String[] args) {
        List<ZkClient> clients = new ArrayList<ZkClient>();
        List<MaterSelector> master = new ArrayList<>();

        try {
            for (int i = 0; i < 10; i++) {
                ZkClient zkClient = new ZkClient(ZK_CONNECTION, 10000, 10000, new SerializableSerializer());
                clients.add(zkClient);

                UserCenter userCenter = new UserCenter();
                userCenter.setMc_id(i);
                userCenter.setMc_name("客户端" + i);


                MaterSelector selector = new MaterSelector(userCenter, zkClient);
                master.add(selector);
                selector.start();
                TimeUnit.SECONDS.sleep(4L);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            for (MaterSelector ms : master) {
                ms.stop();
            }
        }
    }
}

