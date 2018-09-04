package com.zad.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserHandler extends Remote {
    String getUsername() throws RemoteException;
    int getAge() throws RemoteException;
    User getUserById(int id) throws RemoteException;
}
