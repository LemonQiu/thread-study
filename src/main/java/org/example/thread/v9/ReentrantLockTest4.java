package org.example.thread.v9;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author qiu
 * @Date 2020/12/9 23:07
 *
 * 公平锁
 * 新起一个线程
 * 公平锁：会先看阻塞队列中是否有其他线程，如果有则进入队列等待
 * 非公平锁：直接和其他线程一起抢占锁
 */
public class ReentrantLockTest4 {

    /**
     * fair = true 表示为公平锁，因为是公平锁，所以执行的结果会是123轮询输出
     */
    private final Lock lock = new ReentrantLock(true);

    public void m() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " is over");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest4 reentrantLockTest4 = new ReentrantLockTest4();
        new Thread(reentrantLockTest4::m, "t1").start();
        new Thread(reentrantLockTest4::m, "t2").start();
        new Thread(reentrantLockTest4::m, "t3").start();
    }
}
