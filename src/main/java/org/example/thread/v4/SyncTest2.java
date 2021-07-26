package org.example.thread.v4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author qiu
 * @date 2021-03-20 下午 05:27
 * @since 1.0
 */
public class SyncTest2 {

    private int i = 0;
    private static Thread t1, t2;

    public static void main(String[] args) {
        SyncTest2 syncTest2 = new SyncTest2();

        t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // t1第一次读取
            int read = syncTest2.read();
            System.out.println(Thread.currentThread().getName() + ": " + read);
            LockSupport.park();

            // t1第二次读取
            int read2 = syncTest2.read();
            System.out.println(Thread.currentThread().getName() + ": " + read2);
            syncTest2.write();

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // t1第三次读取
            int read3 = syncTest2.read();
            System.out.println(Thread.currentThread().getName() + ": " + read3);
            LockSupport.unpark(t2);
        }, "t1");


        t2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // t2第一次读取
            int read1 = syncTest2.read();
            System.out.println(Thread.currentThread().getName() + ": " + read1);
            syncTest2.write();

            // t2第二次读取
            int read2 = syncTest2.read();
            System.out.println(Thread.currentThread().getName() + ": " + read2);
            LockSupport.unpark(t1);

            LockSupport.park();
            int read3 = syncTest2.read();
            System.out.println(Thread.currentThread().getName() + ": " + read3);
        }, "t2");

        t1.start();
        t2.start();
    }

    public synchronized void write() {
        i++;
    }

    public synchronized int read() {
        return i;
    }
}
