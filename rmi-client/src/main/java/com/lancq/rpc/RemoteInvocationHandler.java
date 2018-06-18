package com.lancq.rpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author lancq
 * @Description
 * @Date 2018/6/18
 **/
public class RemoteInvocationHandler implements InvocationHandler {
    private String host;
    private int port;
    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //��װ����
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameters(args);
        //ͨ��tcp����Э����д���
        TCPTransport tcpTransport = new TCPTransport(this.host, this.port);
        //��������
        return tcpTransport.send(rpcRequest);
    }
}
