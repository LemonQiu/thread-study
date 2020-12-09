package org.example.thread.v9;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author qiu
 * @Date 2020/12/9 22:06
 *
 * ReentrantLock CAS+AQS实现
 * 可重入，互斥锁，并且支持公平锁
 * 通过lock.lock加锁，通过lock.unlock解锁
 */
public class ReentrantLockTest {

    private final Lock lock = new ReentrantLock();
    private int count = 0;

    public void m1() {
        // 加锁
        lock.lock();
        try {
            for (int i = 0; i < 1000; i++) {
//                TimeUnit.SECONDS.sleep(1);
                count++;
                System.out.println(Thread.currentThread().getName() + ", count: = " + count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 解锁
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
        for (int i = 0; i < 10; i++) {
            new Thread(reentrantLockTest::m1).start();
        }
    }
}
