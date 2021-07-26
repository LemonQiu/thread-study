package org.example.thread.v1;

/**
 * @author qiu
 * @date 2021-03-20 下午 05:12
 * @since 1.0
 */
public class SyncTest {

    private final Object lock = new Object();
    private int i = 0;

    public static void main(String[] args) {
        SyncTest syncTest = new SyncTest();
        for (int i = 0; i < 2; i++) {
            new Thread(syncTest::m1).start();
        }
    }

    private void m1() {
        synchronized(lock) {
            while (true) {
                System.out.println(Thread.currentThread().getName() + " is running...");
            }
        }
    }
}
