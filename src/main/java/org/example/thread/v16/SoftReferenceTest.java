package org.example.thread.v16;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

/**
 * @Author qiu
 * @Date 2020/12/12 1:26
 * <p>
 * 软引用是用来描述一些还有用但并非必须的对象。
 * 对于软引用关联着的对象，在系统将要发生内存溢出异常之前，将会把这些对象列进回收范围进行第二次回收。
 * 如果这次回收还没有足够的内存，才会抛出内存溢出异常。
 *
 * -Xms20M -Xmx20M
 */
public class SoftReferenceTest {

    public static void main(String[] args) {
        // 先分配10M的内存大小的软引用字节数组，此时因为总内存大小为20M
        SoftReference<byte[]> softReference = new SoftReference<>(new byte[1024 * 1024 * 10]);
        System.out.println(softReference.get());

        // 此时内存还没有满，手动调用GC，所以软引用不会被回收的
        System.gc();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(softReference.get());

        // 然后分配一个12M大小的强引用字节数组，而此时内存不够用了，则会回收软引用
        byte[] bytes = new byte[1024 * 1024 * 12];
        System.out.println(softReference.get());
    }
}
