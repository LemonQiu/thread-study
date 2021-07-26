package org.example.sets.v1;

import java.util.Set;
import java.util.TreeSet;

/**
 * @Author qiu
 * @Date 2020/12/16 23:49
 *
 * 内部使用红黑树排序
 */
public class TreeSetTest {

    public static void main(String[] args) {
        Set<String> set = new TreeSet<>();
        set.add("e");
        set.add("a");
        set.add("d");
        set.add("z");
        System.out.println(set);
    }


}
