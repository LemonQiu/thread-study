package org.example.thread.v1;

/**
 * @Author qiu
 * @Date 2020/12/8 0:20
 *
 * 不同线程操作同一个资源，资源的同步性处理
 */
public class T implements Runnable {

    private int count = 100;

    @Override
    public synchronized void run() {
        count--;
        System.out.println("count:" + count);
    }

    public static void main(String[] args) {
        T t = new T();
        for (int i = 0; i < 100; i++) {
            new Thread(t).start();
        }
    }
}
