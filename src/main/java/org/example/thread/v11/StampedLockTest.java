package org.example.thread.v11;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * @Author qiu
 * @Date 2020/12/10 22:13
 *
 * StampedLock是为了优化可重入读写锁性能的一个锁实现工具，jdk8开始引入
 * 相比于普通的ReentranReadWriteLock主要多了一种乐观读的功能
 * 在API上增加了stamp的入参和返回值
 * 不支持重入
 */
public class StampedLockTest {

    private static final StampedLock STAMPED_LOCK = new StampedLock();

    private static int count = 10;

    private static final Random RANDOM = new Random();

    public int read() {
        // 获取乐观读锁
        long stamp = STAMPED_LOCK.tryOptimisticRead();
        int currentCount = count;

        // 判断是否有写操作，如果有，则需要设置悲观读锁
        if(!STAMPED_LOCK.validate(stamp)) {
            System.out.println(Thread.currentThread().getName() + ", 获取悲观读锁...");
            stamp = STAMPED_LOCK.readLock();
            try {
                currentCount = count;
            } finally {
                STAMPED_LOCK.unlockRead(stamp);
            }
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return currentCount;
    }

    public void write(int value) {
        long stamp = STAMPED_LOCK.writeLock();
        try {
            TimeUnit.SECONDS.sleep(1);
            count = value;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            STAMPED_LOCK.unlockWrite(stamp);
        }
    }

    public static void main(String[] args) {
        StampedLockTest stampedLockTest = new StampedLockTest();
        long startTime = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(9);
        for (int i = 1; i <= 10; i++) {
            if(i == 6) {
                continue;
            }
            if(i % 3 == 0) {
                new Thread(() -> {
                    int value = RANDOM.nextInt();
                    stampedLockTest.write(value);
                    System.out.println(Thread.currentThread().getName() + ", write data is " + value);
                    countDownLatch.countDown();
                }, "t" + i).start();
            } else {
                new Thread(() -> {
                    int read = stampedLockTest.read();
                    System.out.println(Thread.currentThread().getName() + ", read data is " + read);
                    countDownLatch.countDown();
                }, "t" + i).start();
            }
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("总耗时: " + (endTime - startTime));
    }

}
