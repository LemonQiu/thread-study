package org.example.thread.v16;

import java.util.concurrent.TimeUnit;

/**
 * @Author qiu
 * @Date 2020/12/11 23:20
 *
 * ThreadLocal的内存泄漏问题：
 * 因为ThreadLocalMap是Thread.currentThread所拥有的，所以每一个线程get()的时候都是获取的自己的map中的数据
 *
 * set()方法的时候，key是this(即当前ThreadLocal对象)，而value是Object。且因为Entry对象继承了WeakReference对象，并且对key做了弱引用包装
 *
 * ThreadLocal<Person> THREAD_LOCAL = new ThreadLocal<>()是强引用，只有我们将THREAD_LOCAL=null，对象才会被回收，而又因为ThreadLocalMap的key是弱引用，所以一旦发生GC，就会被回收。
 * 此时key为null，而此时谁也无法访问该key指向的value了。
 * 如果此时Thread没有停止，那么它所拥有的ThreadLocalMap一直存在，也就是表示key为null的value一直存在，而value是强引用，所以value指向的对象一直无法被GC，就会造成内存泄漏问题
 *
 * 解决方案：
 * 1. Thread结束，此时该Thread的所有资源都会被回收
 * 2. 调用ThreadLocal.remove()方法，这个是专门清除key为null的value的API
 */
public class ThreadLocalTest {

    private static final ThreadLocal<Person> THREAD_LOCAL = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(THREAD_LOCAL.get());
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            THREAD_LOCAL.set(new Person());
        }).start();

        while (true) {

        }
    }
}

class Person {
    String name = "zhang san";
}
