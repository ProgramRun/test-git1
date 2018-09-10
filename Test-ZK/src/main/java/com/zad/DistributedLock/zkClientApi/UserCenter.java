package com.zad.DistributedLock.zkClientApi;

import java.io.Serializable;

/**
 * 描述:
 * sss
 *
 * @author zad
 * @create 2018-09-10 20:52
 */
public class UserCenter implements Serializable {

    private static final long serialVersionUID = 2858702479317529646L;

    // 机器id
    private int mc_id;

    // 机器名字
    private String mc_name;

    public int getMc_id() {
        return mc_id;
    }

    public void setMc_id(int mc_id) {
        this.mc_id = mc_id;
    }

    public String getMc_name() {
        return mc_name;
    }

    public void setMc_name(String mc_name) {
        this.mc_name = mc_name;
    }

    @Override
    public String toString() {
        return "UserCenter{" +
                "mc_id=" + mc_id +
                ", mc_name='" + mc_name + '\'' +
                '}';
    }
}
