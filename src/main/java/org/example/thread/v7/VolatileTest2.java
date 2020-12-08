package org.example.thread.v7;

/**
 * @Author qiu
 * @Date 2020/12/8 23:13
 *
 * volatile只能保证线程的可见性，即保证了read、load、use的顺序与连续性，也保证了assign、store、write的顺序与连续性
 * 但是如果修改操作不是原子性的话，那么还是无法保证数据的安全性
 */
public class VolatileTest2 {

    private int count = 0;

    public void m() {
        for (int i = 0; i < 10000; i++) {
            count++;
            System.out.println("count = " + count);
        }
    }

    public static void main(String[] args) {
        VolatileTest2 volatileTest2 = new VolatileTest2();
        for (int i = 0; i < 20; i++) {
            new Thread(volatileTest2::m).start();
        }
    }
}
