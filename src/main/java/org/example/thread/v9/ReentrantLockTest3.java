package org.example.thread.v9;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author qiu
 * @Date 2020/12/9 22:32
 *
 * ReentrantLock.lockInterruptibly允许在等待时由其它线程调用等待线程的Thread.interrupt方法来中断等待线程的等待而直接返回，这时不用获取锁，
 * 而会抛出一个InterruptedException。
 * ReentrantLock.lock方法不允许Thread.interrupt中断,即使检测到Thread.isInterrupted,一样会继续尝试获取锁，失败则继续休眠。
 * 只是在最后获取锁成功后再把当前线程置为interrupted状态,然后再中断线程。
 */
public class ReentrantLockTest3 {
    Lock lock = new ReentrantLock();

    public void m() {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + ", is lock");
        try {
//            lock.lockInterruptibly();
            for (int i = 1; i <= 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + ", count = " + i);
            }

        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + ", InterruptedException");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest3 reentrantLockTest3 = new ReentrantLockTest3();
        Thread t1 = new Thread(reentrantLockTest3::m, "t1");
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t2 = new Thread(reentrantLockTest3::m, "t2");
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("t2 exec interrupt");
        t2.interrupt();
    }
}
