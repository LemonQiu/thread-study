package org.example;

import org.example.thread.v2.Account;
import org.example.thread.v2.TestRunnable;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Unit test for simple App.
 */
public class ThreadTest {

    @Test
    public void testThread() {
        Thread thread = new Thread();
        thread.start();
    }

    @Test
    public void testRunnable() {
        Thread thread = new Thread(() -> System.out.println("test runnable"));
        thread.start();
    }

    @Test
    public void testThreadFactory() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> System.out.println("123456"));
        executorService.shutdown();
    }

    @Test
    public void testSync() {
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
