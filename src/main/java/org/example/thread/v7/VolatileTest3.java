package org.example.thread.v7;

/**
 * @Author qiu
 * @Date 2020/12/8 23:41
 *
 * double check问题体现代码重排序的问题
 * 以下代码虽然有double check和synchronized 来保证数据的安全性
 * 但是依然可能会存在问题
 * 要知道，VolatileTest3 volatileTest3 = new VolatileTest3();在CPU执行指令时确实分为多个步骤
 * 第一步：分配内存
 * 第二部：初始化对象
 * 第三步：赋值
 * CPU为了运行单元被充分利用，会对代码进行乱序执行的优化，即保证结果正确，但是执行代码的顺序却不一定是与书写的一致
 * 这就导致了，如果第三步优先于第二步执行，下面的代码double check就会校验通过，因为已经对引用赋值了，但是还未完全初始化，然后直接返回对象，导致代码后续执行可能会报错。
 */
public class VolatileTest3 {

    private static volatile VolatileTest3 INSTANCE;

    public static VolatileTest3 getInstance() {
        if(INSTANCE == null) {
            synchronized (VolatileTest3.class) {
                if(INSTANCE == null) {
                    INSTANCE = new VolatileTest3();
                    return INSTANCE;
                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> System.out.println(VolatileTest3.getInstance().hashCode())).start();
        }
    }

}
