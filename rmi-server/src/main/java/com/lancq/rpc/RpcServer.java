package com.lancq.rpc;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author lancq
 * @Description 用于发布一个远程服务
 * @Date 2018/6/18
 **/
public class RpcServer {
    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    public void publish(final Object service, int port){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);//启动一个服务监听
            System.out.println(serverSocket);
            while(true){//循环监听
                Socket socket = serverSocket.accept();//监听服务
                System.out.println(socket);
                //通过线程池去处理请求
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
