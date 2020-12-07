package org.example.thread.v1;

/**
 * @Author qiu
 * @Date 2020/12/7 23:43
 *
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

}
