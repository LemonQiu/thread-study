package org.example.thread.v14;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @Author qiu
 * @Date 2020/12/11 0:40
 */
public class SemaphoreFreeTest {

    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        SemaphoreFreeTest t = new SemaphoreFreeTest();
        Semaphore semaphore = new Semaphore(1);

        t1 = new Thread(() -> {
            System.out.println("t1 start...");
            try {
                // 获取信号量
                semaphore.acquire();
                t2.start();
                for (int i = 1; i <= 10; i++) {
                    t.add(new Object());
                    System.out.println("t1 add" + i);
                    if(t.size() == 5) {
                        semaphore.release();

                        t2.join();
                        semaphore.acquire();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1 end...");
            semaphore.release();
        });

        t2 = new Thread(() -> {
            // 获取信号量
            System.out.println("t2 start...");
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2 end...");
            semaphore.release();
        });

        t1.start();

    }
}
