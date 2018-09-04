package com.zad.rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 描述:
 * impl
 *
 * @author zad
 * @create 2018-09-04 22:24
 */
public class UserHandlerImpl extends UnicastRemoteObject implements UserHandler{
    public UserHandlerImpl() throws RemoteException {
    }

    public String getUsername() throws RemoteException {
        return "xiaoLvZi";
    }

    public int getAge() throws RemoteException {
        return 18;
    }

    public User getUserById(int id) throws RemoteException {
        return new User("xiaoLvZi",18);
    }
}
