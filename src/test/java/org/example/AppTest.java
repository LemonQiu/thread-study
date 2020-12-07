package org.example;

import org.example.thread.v1.Account;
import org.example.thread.v1.TestRunnable;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }


    @Test
    public void testSync() {
        Thread[] threads = new Thread[100];

        for (int i = 0; i < 100; i++) {
            Account account = new Account();
            Thread thread = new Thread(new TestRunnable(account));
            threads[i] = thread;
        }

        for (Thread thread : threads) {
            thread.start();
        }
    }
}
