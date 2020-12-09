package org.example.thread.v12;

import java.util.concurrent.Exchanger;

/**
 * @Author qiu
 * @Date 2020/12/10 2:28
 *
 * 两个线程之间进行交换数据
 * exchanger.exchange(s);  交换数据，如果当前容器中只有一个线程放入数据，则该线程一直阻塞等待其他线程放入数据
 */
public class ExchangerTest {
    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(() -> {
            String s = "T1";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);

        }, "t1").start();


        new Thread(() -> {
            String s = "T2";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);

        }, "t2").start();
    }
}
