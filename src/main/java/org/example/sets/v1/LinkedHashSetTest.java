package org.example.sets.v1;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @Author qiu
 * @Date 2020/12/16 23:21
 *
 * 有序的链表的集合, 空参构造中默认初始化一个长度为16，加载因子为0.75的LinkedHashMap
 */
public class LinkedHashSetTest {

    public static void main(String[] args) {
        Set<String> set = new LinkedHashSet<>();
        set.add("123456");
    }
}
