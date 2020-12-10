package org.example.thread.v15;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author qiu
 * @Date 2020/12/11 1:34
 */
public class ConsumerAndProviderTest2 {

    private final LinkedList<Object> list = new LinkedList<>();

    private final int MAX = 10;

    private final Lock lock = new ReentrantLock();
    // 生产两个互相隔离的等待对象
    private final Condition producer = lock.newCondition();
    private final Condition consumer = lock.newCondition();

    public void put(Object o) {
        lock.lock();
        try {
            // 如果不用while而是用if，那么可能存在等待队列中的消费者，因为其他的线程唤醒了，而直接继续执行生产包子，而导致篮子放不下了
            while (this.getCount() == MAX) {
                System.out.println("当前篮子已经满了，等待消费者消费。。。");
                // 生产者进入生产者等待队列
                producer.await();
            }

            list.add(o);
            System.out.println(Thread.currentThread().getName() + " 生成了一个包子，并放入了篮子中。。。当前篮子里的包子：" + this.getCount());
            // 唤醒消费者等待队列中的线程
            consumer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public Object get() {
        lock.lock();
        try {
            // 如果不用while而是用if，那么可能存在等待队列中的消费者，因为其他的线程唤醒了，而直接继续执行吃包子，那么就会造成没有包子还能继续吃的问题。
            while (this.getCount() == 0) {
                System.out.println("当前篮子已经空了，等待生产者生产。。。");
                // 消费者进入消费者等待队列
                consumer.await();
            }
            Object o = list.removeFirst();
            System.out.println(Thread.currentThread().getName() + " 消费了一个包子，并吃掉了它。。。当前篮子里的包子：" + this.getCount());
            // 唤醒生产者等待队列中的线程
            producer.signalAll();
            return o;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

    public int getCount() {
        lock.lock();
        try {
            return list.size();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConsumerAndProviderTest2 c = new ConsumerAndProviderTest2();
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    c.put(new Object());
                }
            }).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    c.get();
                }
            }).start();
        }
    }
}
