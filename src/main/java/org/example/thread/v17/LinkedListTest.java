package org.example.thread.v17;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author qiu
 * @Date 2020/12/16 23:04
 *
 * 链表的集合，内部维护了Node对象，有prev、next和item3个属性，第一次新增数据时，first和last都是该节点
 */
public class LinkedListTest {

    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        list.add("1234560");
        System.out.println(list);
    }
}
