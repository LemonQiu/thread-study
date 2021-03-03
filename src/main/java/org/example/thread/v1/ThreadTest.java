package org.example.thread.v1;

import java.util.concurrent.TimeUnit;

/**
 * @Author qiu
 * @Date 2020/12/23 18:36
 */
public class ThreadTest {
    private static final Object LOCK_OBJ = new Object();

    public static void main(String[] args) {
        ThreadTest  threadTest = new ThreadTest();
        new Thread(() -> {
            threadTest.m();
        }).start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                threadTest.m();
            }).start();
        }
    }

    public void m() {
        synchronized (LOCK_OBJ) {
            for(;;){}
        }
    }
}
