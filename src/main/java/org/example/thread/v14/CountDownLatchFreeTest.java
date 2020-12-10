package org.example.thread.v14;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author qiu
 * @Date 2020/12/10 23:47
 */
public class CountDownLatchFreeTest {

    final List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        CountDownLatchFreeTest t = new CountDownLatchFreeTest();

        CountDownLatch c1 = new CountDownLatch(1);
        CountDownLatch c2 = new CountDownLatch(1);

        new Thread(() -> {
            System.out.println("t2 start...");
            try {
                c1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2 end...");
            c2.countDown();
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            System.out.println("t1 start...");
            for (int i = 1; i <= 10; i++) {
                t.add(new Object());
                System.out.println("t2 add " + i);
                if(t.size() == 5) {
                    c1.countDown();
                    try {
                        c2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("t1 end...");
        }).start();

    }
}
