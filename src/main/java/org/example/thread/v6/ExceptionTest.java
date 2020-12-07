package org.example.thread.v6;

import java.util.concurrent.TimeUnit;

/**
 * @Author qiu
 * @Date 2020/12/8 1:53
 * <p>
 * 一个线程获取到锁以后，如果后续代码中遇到异常信息，则锁会被释放掉
 * 如果不想释放锁，可以catch异常信息
 */
public class ExceptionTest {
    private static int count = 0;

    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + ", m1 start...");
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
            System.out.println(Thread.currentThread().getName() + ", count = " + count);

            if(count % 5 == 0) {
                System.out.println(Thread.currentThread().getName() + ", exception....");
//                try {
                    int i = count / 0;
//                } catch (Exception e) {
//                    System.out.println(Thread.currentThread().getName() + ", catch....");
//                }
            }
        }
    }

    public static void main(String[] args) {
        ExceptionTest exceptionTest = new ExceptionTest();
        for (int i = 1; i < 10; i++) {
            new Thread(() -> exceptionTest.m1(), "t" + i).start();
        }
    }
}
