package org.example.thread.v2;

import java.util.concurrent.CountDownLatch;

/**
 * @Author qiu
 * @Date 2021/1/12 1:20
 */
public class Test {

    private int count;

    public static void main(String[] args) {
        Test test = new Test();

        CountDownLatch countDownLatch = new CountDownLatch(10000);
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                test.count++;
                countDownLatch.countDown();
            }).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(test.count);

    }
}
