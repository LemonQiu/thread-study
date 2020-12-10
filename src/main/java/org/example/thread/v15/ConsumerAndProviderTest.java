package org.example.thread.v15;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @Author qiu
 * @Date 2020/12/10 23:57
 */
public class ConsumerAndProviderTest {

    private final LinkedList<Object> list = new LinkedList<>();

    private final Object lock = new Object();

    private final int MAX = 10;

    public void put(Object o) {
        synchronized (lock) {
            try {
                // 如果不用while而是用if，那么可能存在等待队列中的消费者，因为其他的线程唤醒了，而直接继续执行生产包子，而导致篮子放不下了
                while (this.getCount() == MAX) {
                    System.out.println("当前篮子已经满了，等待消费者消费。。。");
                    lock.wait();
                }

                list.add(o);
                System.out.println(Thread.currentThread().getName() + " 生成了一个包子，并放入了篮子中。。。当前篮子里的包子：" + this.getCount());
                lock.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Object get() {
        synchronized (lock) {
            Object o = null;
            try {
                // 如果不用while而是用if，那么可能存在等待队列中的消费者，因为其他的线程唤醒了，而直接继续执行吃包子，那么就会造成没有包子还能继续吃的问题。
                while (this.getCount() == 0) {
                    System.out.println("当前篮子已经空了，等待生产者生产。。。");
                    lock.wait();
                }

                o  = list.removeFirst();
                System.out.println(Thread.currentThread().getName() + " 消费了一个包子，并吃掉了它。。。当前篮子里的包子：" + this.getCount());
                // 当篮子里的不满时，需要生产者继续生产
                lock.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return o;
        }
    }

    public synchronized int getCount() {
        return list.size();
    }

    public static void main(String[] args) {
        ConsumerAndProviderTest c = new ConsumerAndProviderTest();
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
