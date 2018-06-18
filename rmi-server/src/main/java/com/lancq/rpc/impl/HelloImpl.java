package com.lancq.rpc.impl;

import com.lancq.rpc.IHello;

/**
 * @Author lancq
 * @Description
 * @Date 2018/6/18
 **/
public class HelloImpl implements IHello {
    public String sayHello(String msg) {
        return "Hello," + msg;
    }
}
