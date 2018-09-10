package com.zad.DistributedLock.zkClientApi;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 * 选主的服务
 *
 * @author zad
 * @create 2018-09-10 20:54
 */
public class MaterSelector {

    private ZkClient zkClient;
    // 需要争抢的节点
    private final static String MASTER = "/master";
    // 注册节点内容变化
    private IZkDataListener dataListener;
    // 其他服务器
    private UserCenter server;
    // master 节点
    private UserCenter master;

    ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    private static boolean isRun = false;

    public MaterSelector(UserCenter server, ZkClient zkClient) {
        System.out.println("[" + server + "]去争抢权限了");
        this.server = server;
        this.zkClient = zkClient;
        this.dataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {

            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("节点删除开始" + dataPath);
                // 如果被删除,发起选主操作
                chooseMater();
            }
        };
    }


    public void start() {
        // 开始选主
        if (!isRun) {
            isRun = true;
            // 注册节点事件
            zkClient.subscribeDataChanges(MASTER, dataListener);
            chooseMater();
        }
    }

    public void stop() {
        // 停止
        if (isRun) {
            isRun = false;
            scheduledExecutorService.shutdown();
            zkClient.unsubscribeDataChanges(MASTER, dataListener);
            releaseMaster();
        }
    }

    private void chooseMater() {
        if (!isRun) {
            System.out.println("当前服务没有启动");
            return;
        }

        try {
            zkClient.createEphemeral(MASTER, server);
            master = server;
            System.out.println("客户端[" + server.getMc_id() + "] -> 我现在已经是master了");

            // 通过定时器释放锁,模拟出现故障
            scheduledExecutorService.schedule(() -> {
                releaseMaster();
            }, 5, TimeUnit.SECONDS);
        } catch (ZkNodeExistsException e) {
            UserCenter center = zkClient.readData(MASTER, true);
            if (center == null) {
                // master已经存在
                chooseMater();// 再次获取master
            } else {
                master = center;
            }
        }
    }

    private void releaseMaster() {
        // 判断是否为master,只有master才需要释放锁
        if (isMaster()) {
            System.out.println("释放master");
            zkClient.deleteRecursive(MASTER);
        }
    }

    private boolean isMaster() {
        UserCenter userCenter = zkClient.readData(MASTER);
        if (userCenter.getMc_name().equals(server.getMc_name())) {
            master = userCenter;
            return true;
        }
        return false;
    }

}
