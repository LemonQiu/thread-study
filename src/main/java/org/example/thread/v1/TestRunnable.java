package org.example.thread.v1;

/**
 * @Author qiu
 * @Date 2020/12/7 23:42
 */
public class TestRunnable implements Runnable {

    private Account account;

    public TestRunnable(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        account.add();
    }
}
