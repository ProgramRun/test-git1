package com.zad.rmi.server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * 描述:
 * boot
 *
 * @author zad
 * @create 2018-09-04 22:26
 */
public class ServerBootStrap {
    public static void main(String[] args) {
        UserHandlerImpl userHandler;
        try {
            userHandler = new UserHandlerImpl();
            LocateRegistry.createRegistry(8888);
            Naming.bind("rmi://127.0.0.1:8888/userHandler", userHandler);
            System.out.println("server is OK");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
