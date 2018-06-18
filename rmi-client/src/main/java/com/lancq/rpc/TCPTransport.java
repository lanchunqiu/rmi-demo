package com.lancq.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @Author lancq
 * @Description
 * @Date 2018/6/18
 **/
public class TCPTransport {
    private String host;
    private int port;

    public TCPTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }
    //����һ��socket����
    private Socket newSocket(){
        System.out.println("����һ���µ�����");
        Socket socket;
        try {
            socket = new Socket(host, port);
            System.out.println(socket);
            return socket;
        } catch (IOException e) {
            throw new RuntimeException("���ӽ���ʧ��");
        }
    }

    public Object send(RpcRequest rpcRequest){
        Socket socket = null;
        ObjectOutputStream objectOutputStream =null;
        ObjectInputStream objectInputStream = null;
        try{
            socket = newSocket();
            //��ȡ����������ͻ�����Ҫ���õ�Զ�̷�������request���͸�
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();
            //��ȡ���������õ�����˵ķ��ؽ��
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object result = objectInputStream.readObject();
            return result;
        } catch (Exception e){
            throw new RuntimeException("����Զ�̵����쳣��" + e);
        } finally {
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (objectInputStream != null ){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(objectOutputStream != null){
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
