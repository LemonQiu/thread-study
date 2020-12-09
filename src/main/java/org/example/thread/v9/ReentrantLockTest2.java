package org.example.thread.v9;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author qiu
 * @Date 2020/12/9 22:25
 *
 * tryLock 尝试获取锁
 */
public class ReentrantLockTest2 {

    private final Lock lock = new ReentrantLock();

    public void m1() {
        lock.lock();
        try {
            for (int i = 0; i < 4; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + ", count = " + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void m2() {
        try {
            boolean tryLock = lock.tryLock(5, TimeUnit.SECONDS);
            System.out.println(Thread.currentThread().getName() + ", m2 tryLock = " + tryLock);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ReentrantLockTest2 reentrantLockTest2 = new ReentrantLockTest2();

        new Thread(reentrantLockTest2::m1).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(reentrantLockTest2::m2).start();
    }
}
