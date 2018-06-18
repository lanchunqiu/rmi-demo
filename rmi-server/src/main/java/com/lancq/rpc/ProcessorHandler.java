package com.lancq.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @Author lancq
 * @Description
 * @Date 2018/6/18
 **/
public class ProcessorHandler implements Runnable {
    private Socket socket;
    private Object service; //����˷����ķ���

    public ProcessorHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    public void run() {
        //��������
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            //��ȡ�ͻ��˵�������
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            //�����л�Զ�̴���Ķ���RpcRequest
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            System.out.println("rpcRequest:" + rpcRequest);
            Object result = invoke(rpcRequest);//ͨ������ȥ���ñ��صķ���

            //ͨ������������������ͻ���
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(result);
            objectOutputStream.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(objectInputStream != null){
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
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Object invoke(RpcRequest rpcRequest) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        //һ�¾�Ϊ���������Ŀ����ͨ��������÷���
        Object[] args = rpcRequest.getParameters();
        Class<?>[] types = new Class[args.length];
        for(int i=0; i<args.length; i++){
            types[i] = args[i].getClass();
        }
        Method method = service.getClass().getMethod(rpcRequest.getMethodName(),types);
        return method.invoke(service, args);
    }
}
