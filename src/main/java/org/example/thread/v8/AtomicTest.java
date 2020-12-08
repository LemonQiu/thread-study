package org.example.thread.v8;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author qiu
 * @Date 2020/12/9 2:00
 *
 * 无锁优化
 */
public class AtomicTest {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public void m() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(atomicInteger.incrementAndGet());
        }
    }

    public static void main(String[] args) {
        AtomicTest atomicTest = new AtomicTest();
        for (int i = 0; i < 10; i++) {
            new Thread(atomicTest::m).start();
        }
//        Class<Unsafe> unsafeClass = Unsafe.class;
//        try {
//            Method method = unsafeClass.getMethod("allocateMemory", long.class);
//            method.setAccessible(true);
//            method.invoke(Unsafe.getUnsafe(), 4096L);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }  catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
    }
}
