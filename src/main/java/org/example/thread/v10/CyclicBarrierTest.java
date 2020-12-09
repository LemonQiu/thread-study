package org.example.thread.v10;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author qiu
 * @Date 2020/12/10 0:11
 *
 * CyclicBarrier: 周期的栅栏，只有满足了条件，才会执行后续的内容，否则一直阻塞。可以不停的循环
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(20, () -> System.out.println("满20人了，出发！！！"));

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName() + " is over + " + cyclicBarrier.getParties());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
