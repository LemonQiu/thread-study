package org.example.thread.v17;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author qiu
 * @Date 2020/12/16 22:53
 *
 * 线程不安全的集合
 * 如果没有指定默认大小，则第一次add的时候，会通过grow()方法进行初始化大小为10
 */
public class ArrayListTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(1);
        list.add("123456");
        list.add("123456");
        System.out.println(list);
    }
}
