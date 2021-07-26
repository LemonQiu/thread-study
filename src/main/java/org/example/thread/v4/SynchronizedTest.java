package org.example.thread.v4;

import java.util.concurrent.TimeUnit;

/**
 * 每一个对象都有自己的布局
 *  1、对象头：包括markword和ClassPointer（是对象指向它的类元数据的指针，虚拟机通过这个指针来确定这个对象是哪个类的实例）
 *      1.1：markword在64bit的虚拟机中，长度为64bit，其中2bit用于记录锁标识，1bit用于表示是否偏向锁
 *          1.1.1：01，同时表示无锁和偏向锁，偏向锁标识为0时，为无锁，为1时，为偏向锁
 *          1.1.2：00，表示为轻量级锁
 *          1.1.3：10，表示为重量级锁
 *          1.1.4：11，GC标记
 *      1.2：markword在64bit的虚拟机中，分出4bit表示对象年龄
 *      1.3：markword在偏向锁状态下，有54bit记录获取锁的Thread的指针，2bit为epoch，1bit为未使用。
 *      1.4：markword在轻量级锁状态下，有62bit记录获取锁的线程栈中lockRecord的指针。
 *      1.5：markword在重量级锁状态下，有62bit记录获取互斥量（重量级锁）的指针
 *      1.6：markword在无锁状态下，有26bit未使用，31bit记录hashcode。
 *
 *  2、实例数据：该对象本身的属性和其父类的属性。
 *  3、对齐填充：JVM规定对象的大小必须是8的整数倍，如果上面两个加在一起不是8的整数倍，则进行填充补齐。
 *
 *
 * 锁升级过程：
 *  1、获取偏向锁：
 *      1.1：首先判断锁对象是否属于可偏向状态（即查询锁对象的markword是否有记录Thread指针，且偏向锁标志为1）。
 *      1.2：如果是可偏向状态，则通过CAS操作更新锁对象markword标识。
 *          1.2.1：更新成功，锁对象转为偏向锁
 *          1.2.1：更新失败，说明有并发的线程获取到了偏向锁，升级为轻量级锁。
*       1.3：如果不是可偏向状态，则先判断markword的ThreadId是否为自己，如果不是，则升级为轻量级锁。
 *    偏向锁的撤销与升级：偏向锁撤销不是将锁状态恢复无锁，而是说偏向锁升级轻量级锁的过程，这也分为两种情况。
 *      1.1：获取偏向锁的线程此时已经执行完成，那么会将锁恢复为无锁状态，竞争的锁，此时会重新获取偏向锁
 *      1.2：
 *
 *
 *
 * @Author qiu
 * @Date 2020/12/8 1:20
 *
 * synchronized 可重入性
 */
public class SynchronizedTest {

    public void m1() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + ", m1 start...");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.m2();
        }
    }

    public void m2() {
        System.out.println(Thread.currentThread().getName() + ", m2 start...");
        synchronized (this) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ", m2 end...");
        }
    }

    /**
     * 运行结果：
     * t1, m1 start...
     * t2, m2 start...
     * t1, m2 start...
     * t1, m2 end...
     * t2, m2 end...
     * <p>
     * 从运行结果上看，t1线程中在调用m1()方法后，对当前对象加锁，沉睡两秒后，可以直接访问m2()方法，这是因为synchronized的可重入性。
     * 且t1对m1()加锁后，m2()其实也已经被当前线程加锁了，所以其他无法访问。
     *
     * @param args
     */
    public static void main(String[] args) {
        SynchronizedTest synchronizedTest = new SynchronizedTest();
        new Thread(() -> synchronizedTest.m1(), "t1").start();
        new Thread(() -> synchronizedTest.m2(), "t2").start();
    }
}
