package org.example.thread.v1;

import java.util.UUID;

/**
 * @Author qiu
 * @Date 2020/12/23 22:44
 */
public class StringTest {

    public static void main(String[] args) {
        String[] strings = new String[5_0000];
        for (int i = 0; i < 5_0000; i++) {
            strings[i] = UUID.randomUUID().toString();
        }

        Thread t1 = new Thread(() -> {
            long startTime = System.currentTimeMillis();
            String s = "";
            for (int i = 0; i < 5_0000; i++) {
                s += strings[i].replace("-", "");
            }
            long endTime = System.currentTimeMillis();
            System.out.println("s耗时：" + (endTime - startTime));
        }, "thread - 001");

        Thread t2 = new Thread(() -> {
            long startTime = System.currentTimeMillis();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 5_0000; i++) {
                builder.append(strings[i].replace("-", ""));
            }
            long endTime = System.currentTimeMillis();
            System.out.println("builder耗时：" + (endTime - startTime) + builder.toString());
        }, "thread - 001");

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
