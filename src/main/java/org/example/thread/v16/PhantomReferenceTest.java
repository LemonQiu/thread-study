package org.example.thread.v16;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author qiu
 * @Date 2020/12/12 1:41
 *
 * 虚引用：创建一个虚引用对象，还需要一个队列，这个队列的作用是记录被回收的虚引用。
 * 也就是说，如果虚引用被JVM回收了，会被记录在队列中，而我们要做的是清除队列中引用所指向的内存
 *
 * 一般是虚引用指向的都是堆外内存，而JVM无法直接清除，所以会先把虚引用回收掉，然后通过队列中再去调用C的方法去清除堆外内存
 *
 * -Xms20M -Xmx20M
 */
public class PhantomReferenceTest {

    private static final ReferenceQueue<M> queue = new ReferenceQueue();
    private static final List<Object> list = new LinkedList<>();

    public static void main(String[] args) {
        PhantomReference<M> phantomReference = new PhantomReference<>(new M(), queue);
        // 虚引用指向的对象，普通用户是无法获取到的，所以get()方法会返回null
        System.out.println(phantomReference.get());

        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 不停的分配内存
                list.add(new byte[1024 * 1024 * 2]);
                System.out.println("分配了2M的内存了");
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            while (true) {
                // 如果队列中有值了，表示虚引用被JVM回收了
                Reference<? extends M> poll = queue.poll();
                if (poll != null) {
                    System.out.println("虚引用被JVM清除了。。。");
                }
            }
        }).start();
    }
}
