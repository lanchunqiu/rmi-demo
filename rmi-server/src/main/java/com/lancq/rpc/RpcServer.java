package com.lancq.rpc;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author lancq
 * @Description ���ڷ���һ��Զ�̷���
 * @Date 2018/6/18
 **/
public class RpcServer {
    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    public void publish(final Object service, int port){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);//����һ���������
            System.out.println(serverSocket);
            while(true){//ѭ������
                Socket socket = serverSocket.accept();//��������
                System.out.println(socket);
                //ͨ���̳߳�ȥ��������
                executorService.execute(new ProcessorHandler(socket,service));

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
