package org.example.thread.v2;

/**
 * @Author qiu
 * @Date 2020/12/7 23:43
 *
 * synchronized锁的对象区别
 * synchronized (Account.class) 等同于 public synchronized static void add() 锁的是Account.class这个Class对象
 * synchronized (this) 等同于 public synchronized void add() 锁的是当前Account的对象
 */
public class Account {

    private static int count = 0;


    public synchronized static void add1() {
        count++;
        System.out.println("count:" + count);
    }

    public static void add2() {
        synchronized (Account.class) {
            count++;
            System.out.println("count:" + count);
        }
    }

    public synchronized void add3() {
        System.out.println(this);
        count++;
        System.out.println("count:" + count);
    }

    public void add4() {
        synchronized (this) {
            System.out.println(this);
            count++;
            System.out.println("count:" + count);
        }
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[100];

        // 如果是每一个线程都使用一个Account对象，那么可以synchronized (this)
//        Account account = new Account();
        for (int i = 0; i < 100; i++) {
            // 如果是每一个线程都是一个新的Account对象，那么synchronized (this)是没有作用的，因为锁的不是同一个对象，可以锁Account.class对象
            Account account = new Account();
            Thread thread = new Thread(new TestRunnable(account));
            threads[i] = thread;
        }

        for (Thread thread : threads) {
            thread.start();
        }
    }

}
