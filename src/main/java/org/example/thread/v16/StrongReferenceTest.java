package org.example.thread.v16;

import java.io.IOException;

/**
 * @Author qiu
 * @Date 2020/12/12 1:23
 *
 * 强引用， M m = new M();
 * m指向的new出来的对象，则为强引用，该对象只有在没有任何引用指向的时候才会被GC回收，否则即使是内存不足也不会去回收的
 */
public class StrongReferenceTest {

    public static void main(String[] args) {
        M m = new M();
        m = null;
        System.gc();
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
