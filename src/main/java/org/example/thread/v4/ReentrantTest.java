package org.example.thread.v4;

import java.util.concurrent.TimeUnit;

/**
 * @Author qiu
 * @Date 2020/12/8 1:20
 *
 * synchronized 可重入性
 */
public class ReentrantTest {

    public void m1() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + ", m1 start...");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.m2();
        }
    }

    public void m2() {
        System.out.println(Thread.currentThread().getName() + ", m2 start...");
        synchronized (this) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ", m2 end...");
        }
    }

    /**
     * 运行结果：
     * t1, m1 start...
     * t2, m2 start...
     * t1, m2 start...
     * t1, m2 end...
     * t2, m2 end...
     * <p>
     * 从运行结果上看，t1线程中在调用m1()方法后，对当前对象加锁，沉睡两秒后，可以直接访问m2()方法，这是因为synchronized的可重入性。
     * 且t1对m1()加锁后，m2()其实也已经被当前线程加锁了，所以其他无法访问。
     *
     * @param args
     */
    public static void main(String[] args) {
        ReentrantTest reentrantTest = new ReentrantTest();
        new Thread(() -> reentrantTest.m1(), "t1").start();
        new Thread(() -> reentrantTest.m2(), "t2").start();
    }
}
