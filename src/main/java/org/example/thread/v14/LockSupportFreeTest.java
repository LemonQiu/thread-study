package org.example.thread.v14;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author qiu
 * @Date 2020/12/10 23:52
 */
public class LockSupportFreeTest {

    final List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        LockSupportFreeTest t = new LockSupportFreeTest();

        t2 = new Thread(() -> {
            System.out.println("t2 start...");
            LockSupport.park();
            System.out.println("t2 end...");
            LockSupport.unpark(t1);
        });
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t1 = new Thread(() -> {
            System.out.println("t1 start...");
            for (int i = 1; i <= 10; i++) {
                t.add(new Object());
                System.out.println("t2 add " + i);
                if (t.size() == 5) {
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            }
            System.out.println("t1 end...");
        });
        t1.start();
    }
}
