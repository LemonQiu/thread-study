package org.example.thread.v5;

import java.util.concurrent.TimeUnit;

/**
 * @Author qiu
 * @Date 2020/12/8 1:36
 *
 * 父子类的重入性，如果子类方法中调用了super的方法，锁的都是当前子类的对象
 */
public class ParentTest {

    public void m1() {
        System.out.println(Thread.currentThread().getName() + ", parent m1 start...");
        synchronized (ParentTest.class) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ", parent m2 end...");
        }
    }
}

class ChildTest extends ParentTest {

    @Override
    public void m1() {
        synchronized (ParentTest.class) {
            System.out.println(Thread.currentThread().getName() + ", child m1 start...");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            super.m1();
            System.out.println(Thread.currentThread().getName() + ", child m1 end...");
        }
    }

    public void m2() {
        m1();
    }

    public static void main(String[] args) {
        ChildTest childTest = new ChildTest();
        new Thread(() -> childTest.m2(), "t1").start();

        ParentTest parentTest = new ParentTest();
        new Thread(() -> parentTest.m1(), "t2").start();
    }
}
