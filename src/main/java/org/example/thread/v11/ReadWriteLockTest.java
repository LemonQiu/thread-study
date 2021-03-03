package org.example.thread.v11;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author qiu
 * @Date 2020/12/10 1:27
 *
 * 读写锁
 * 读->共享锁   如果是读线程，则允许一起读取数据，但是写线程需要等待读锁释放才能写
 * 写->排它锁   如果是写线程，则只能一个一个的写
 */
public class ReadWriteLockTest {

    private static final ReadWriteLock READ_WRITE_LOCK = new ReentrantReadWriteLock();

    private static final Lock LOCK = new ReentrantLock();

    private static final Lock READ_LOCK = READ_WRITE_LOCK.readLock();

    private static final Lock WRITE_LOCK = READ_WRITE_LOCK.writeLock();

    private static final Random RANDOM = new Random();

    private int count = 0;

    public int read() {
        READ_LOCK.lock();
        try {
            TimeUnit.SECONDS.sleep(1);
            return count;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            READ_LOCK.unlock();
        }
        return count;
    }

    public void write(int v) {
        WRITE_LOCK.lock();
        try {
            TimeUnit.SECONDS.sleep(1);
            count = v;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            WRITE_LOCK.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockTest readWriteLockTest = new ReadWriteLockTest();
        for (int i = 1; i <= 10; i++) {
            if(i % 3 == 0) {
                new Thread(() -> {
                    int data = RANDOM.nextInt();
                    readWriteLockTest.write(data);
                    System.out.println(Thread.currentThread().getName() + " 写入了数据为：" + data);
                }, "t" + i).start();
            } else {
                new Thread(() -> {
                    int data = readWriteLockTest.read();
                    System.out.println(Thread.currentThread().getName() + " 读出数据为：" + data);
                }, "t" + i).start();
            }
        }
    }
}
