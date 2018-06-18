package com.lancq.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * @Author lancq
 * @Description
 * @Date 2018/6/18
 **/
public class Server {
    public static void main(String[] args) {
        try {
            IHelloService helloService=new HelloServiceImpl();//�Ѿ�������һ��Զ�̶���

            LocateRegistry.createRegistry(1099);

            Naming.rebind("rmi://127.0.0.1/Hello",helloService); //ע������ key - value
            System.out.println("���������ɹ�");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
