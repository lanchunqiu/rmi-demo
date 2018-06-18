package com.lancq.rpc;


/**
 * @Author lancq
 * @Description
 * @Date 2018/6/18
 **/
public class ClientDemo {
    public static void main(String[] args) {
        RpcClientProxy rpcClientProxy = new RpcClientProxy();
        IHello hello = rpcClientProxy.clientProxy(IHello.class,"127.0.0.1",8888);

        System.out.println(hello.sayHello("lancq"));
    }
}
