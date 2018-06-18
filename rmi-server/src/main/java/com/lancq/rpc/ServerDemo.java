package com.lancq.rpc;

import com.lancq.rpc.impl.HelloImpl;

/**
 * @Author lancq
 * @Description
 * @Date 2018/6/18
 **/
public class ServerDemo {
    public static void main(String[] args) {
        IHello hello = new HelloImpl();
        RpcServer rpcServer = new RpcServer();
        rpcServer.publish(hello,8888);
    }
}
