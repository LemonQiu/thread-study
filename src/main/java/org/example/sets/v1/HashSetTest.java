package org.example.sets.v1;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author qiu
 * @Date 2020/12/16 23:18
 * Set是一个元素唯一的集合，原因是set底层使用的Map，将新加入的元素作为key来保证元素唯一
 * return map.put(e, PRESENT)==null;
 * 注意：如果是我们自定义的对象，想要使用set保证元素唯一，必须重写equals和hashCode方法
 */
public class HashSetTest {

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("123456");
    }
}
