package com.lancq.rpc;

import java.lang.reflect.Proxy;

/**
 * @Author lancq
 * @Description 创建客户端的远程代理。通过远程代理进行访问
 * @Date 2018/6/18
 **/
public class RpcClientProxy {
    public<T> T clientProxy(final Class<T> interfacecls, final String host, final int port){
        //使用到了动态代理。
        return (T)Proxy.newProxyInstance(interfacecls.getClassLoader(),
                new Class[] {interfacecls},
                new RemoteInvocationHandler(host, port));
    }
}
