package com.zad.rmi.client;

import com.zad.rmi.server.UserHandler;
import lombok.extern.slf4j.Slf4j;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * 描述:
 * client
 *
 * @author zad
 * @create 2018-09-04 22:34
 */
@Slf4j
public class ClientBootStrap {


    public static void main(String[] args) {
        try {
            UserHandler uh = (UserHandler) Naming.lookup("rmi://127.0.0.1:8888/userHandler");
            log.info("uh.class : {}", uh.toString());
            log.info("uh.User : {}", uh.getUserById(1).toString());
            log.info("uh.getAge : {}", uh.getAge());
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
