package org.example.thread;

import java.util.concurrent.Callable;

/**
 * @Author qiu
 * @Date 2020/12/7 22:49
 */
public class CallableThread implements Callable<Object> {

    @Override
    public Object call() throws Exception {
        return new Object();
    }
}
