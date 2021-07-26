package org.example.sets.v1;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author qiu
 * @date 2021-03-09 下午 08:48
 * @since 1.0
 */
public class CopyOnWriteArraySetTest {

    public static void main(String[] args) {
        Set<String> copyOnWriteArraySet = new CopyOnWriteArraySet<>();
        copyOnWriteArraySet.add("1111");

    }
}
