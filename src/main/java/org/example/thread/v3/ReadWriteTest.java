package org.example.thread.v3;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author qiu
 * @Date 2020/12/8 0:58
 *
 * 如果写加锁，读不加锁会怎样？
 */
public class ReadWriteTest {

    private String name;

    private double score;

    public synchronized void set(String name, double score) {
        this.name = name;

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.score = score;
    }

    public void getScore(String name) {
        if(Objects.equals(this.name, name)) {
            System.out.println(Thread.currentThread().getName() + ", name: " + this.name + ", score: " + this.score);
        }
    }


    /**
     * 如果只给写方法加锁，而读方法不加锁，则会产生脏读
     * 如果业务中允许，则可以不给读加锁
     * @param args
     */
    public static void main(String[] args) {
        ReadWriteTest readWriteTest = new ReadWriteTest();
        new Thread(() -> readWriteTest.set("li si", 12.23), "t1").start();

        new Thread(() -> readWriteTest.getScore("li si"), "t2").start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> readWriteTest.getScore("li si"), "t3").start();
    }
}
