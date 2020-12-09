package org.example.thread.v10;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author qiu
 * @Date 2020/12/9 23:48
 *
 * CountDownLatch：等待门栓条件满足，否则一直阻塞
 */
public class CountDownLatchTest {

    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger();

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    ATOMIC_INTEGER.incrementAndGet();
                }
                countDownLatch.countDown();
            }).start();
        }

        try {
            // 只有等所有线程完成任务，执行countDown后，门栓条件=0才会继续执行，否则一直阻塞
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("count = " + ATOMIC_INTEGER.get());
    }
}
