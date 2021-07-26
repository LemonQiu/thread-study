package org.example.arrays.v1;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author qiu
 * @Date 2020/12/16 22:05
 *
 *  线程安全的集合
 *  每次超出容量会翻倍
 */
public class VectorTest {

    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(0);

    private static final Vector VECTOR = new Vector(1);

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                VECTOR.add("vector" + ATOMIC_INTEGER.incrementAndGet());
                countDownLatch.countDown();
            }).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(VECTOR);
    }
}
