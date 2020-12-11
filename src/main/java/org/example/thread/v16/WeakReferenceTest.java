package org.example.thread.v16;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * @Author qiu
 * @Date 2020/12/12 1:36
 *
 * 弱引用：只要开始GC，就会被回收掉
 */
public class WeakReferenceTest {

    public static void main(String[] args) {
        // 创建一个弱引用的M对象
        WeakReference<M> weakReference = new WeakReference<>(new M());
        System.out.println(weakReference.get());

        // 开始回收，此时M对象肯定会被回收的
        System.gc();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(weakReference.get());
    }
}
