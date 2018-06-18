package com.lancq.rpc;

import java.lang.reflect.Proxy;

/**
 * @Author lancq
 * @Description �����ͻ��˵�Զ�̴���ͨ��Զ�̴�����з���
 * @Date 2018/6/18
 **/
public class RpcClientProxy {
    public<T> T clientProxy(final Class<T> interfacecls, final String host, final int port){
        //ʹ�õ��˶�̬����
        return (T)Proxy.newProxyInstance(interfacecls.getClassLoader(),
                new Class[] {interfacecls},
                new RemoteInvocationHandler(host, port));
    }
}
