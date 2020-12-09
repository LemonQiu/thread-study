package org.example.thread.v12;

import java.util.concurrent.Semaphore;

/**
 * @Author qiu
 * @Date 2020/12/10 2:26
 *
 * 信号量
 * s.acquire(); 获取信号，信号-1，只有获取到信号的线程才能执行，否则一直等待
 * s.release(); 释放信号，信号+1
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        //Semaphore s = new Semaphore(2);
        Semaphore s = new Semaphore(2, true);

        new Thread(()->{
            try {
                s.acquire();

                System.out.println("T1 running...");
                Thread.sleep(200);
                System.out.println("T1 running...");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                s.release();
            }
        }).start();

        new Thread(()->{
            try {
                s.acquire();

                System.out.println("T2 running...");
                Thread.sleep(200);
                System.out.println("T2 running...");

                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
