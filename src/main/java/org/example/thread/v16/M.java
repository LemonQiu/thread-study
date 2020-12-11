package org.example.thread.v16;

/**
 * @Author qiu
 * @Date 2020/12/12 1:20
 */
public class M {

    /**
     * 重写Object的finalize，该方法是GC时回收对象需要调用的
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}
