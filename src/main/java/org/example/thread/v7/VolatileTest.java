package org.example.thread.v7;

import java.util.concurrent.TimeUnit;

/**
 * @Author qiu
 * @Date 2020/12/8 21:57
 *
 * 线程共享主内存（堆）中的数据，但是因为CPU的读写效率和内存读写效率差距过大，如果一直频繁读取内存，效率必然降低
 * 所以线程会将主内存中的共享变量（临界资源）COPY一份到自己的工作内存中。
 * 当其中一个线程堆共享变量进行修改后，会将修改后的值更新到主内存中，
 * 但是其他的线程却因为自己工作内存中的共享变量还未失效，所以不会立即重新读取数据，而是继续使用自己的数据
 * 只有当线程进行系统调用（上下文切换）时或者CPU空闲时（线程休眠、I/O操作等），线程中的工作内存中的共享变量将会失效，从而会重新从主内存中读取数据
 *
 * 而被volatile修饰的共享变量（临界资源）发生修改时，则其他线程再使用这个变量的时候，强制会从主内存中重新获取该值。
 *
 * volatile  保证线程可见性
 */
public class VolatileTest {

    private boolean flag = false;

    public void m() {
        System.out.println(Thread.currentThread().getName() + ", m() start...");
        while (true) {
            // 当进行上下文切换或者线程休眠时或者进行I/O操作时，线程中的工作内存中的共享变量将会失效，从而会重新从主内存中读取数据
//            System.out.println("sdsad");
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            File file = new File("");
            if(flag) {
                System.out.println(Thread.currentThread().getName() + ", flag is " + flag);
                break;
            }
        }
        System.out.println(Thread.currentThread().getName() + ", m() end...");
    }

    public static void main(String[] args) {
        VolatileTest volatileTest = new VolatileTest();
        new Thread(volatileTest::m, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        volatileTest.flag = true;
    }
}
