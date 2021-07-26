package org.example.arrays.v1;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author qiu
 * @Date 2020/12/16 23:09
 *
 * 写时复制的集合
 * 读时无锁，写时通过ReentrantLock加锁
 * 每次写的时候，写线程都会copy一份新的副本，Arrays.copyOf(elements, len + 1);
 * 写线程对新副本进行操作，读线程继续读取旧的数据，当写完时，会将新副本替换旧数据。
 */
public class CopyOnWriteArrayListTest {

    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();
        list.add("12346");

        list.get(0);
    }
}
