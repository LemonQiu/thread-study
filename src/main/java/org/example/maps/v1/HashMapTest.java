package org.example.maps.v1;

import java.util.HashMap;
import java.util.Map;

/**
 * 表示我们自己在创建hashMap的时候，指定的初始化值，通过该方法，可以转换为2的整数幂，比如设置为15，则转换为16，设置为17，则转换为32
 * 为什么数组的长度要保证整数幂？
 *  1、indexFor()时，计算方式是hash & (length -1)，该方式如果数组长度为2的整数幂，则length - 1后，二进制的低位数一定都是1，从而在与hash值进行&运算时，计算的值就是hash的低位数，使计算出来的下标不会超出长度
 *      hash(49) & (length(16) - 1)
 *      110001      49
 *      001111      15
 *      000001      1
 *  2、在进行reIndex时，可以快速计算出新数组的下标位置，只需要判断低位数的前一位数是0还是1，如果是0，则还是原来的下标位置，如果是1，则是原来的下标位置+高位值，如果高位值为16，则加上16
 *      hash(49) & (length(32) - 1)
 *      110001      49
 *      011111      15
 *      010001      17
 * tableSizeFor(int cap) {
 *     int n = cap - 1;
 *     n |= n >>> 1;
 *     n |= n >>> 2;
 *     n |= n >>> 4;
 *     n |= n >>> 8;
 *     n |= n >>> 16;
 *     return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
 * }
 *
 * hash的计算方式，主要的目的是为了让高位数与低位数异或，从而让hash的分布更散列
 * (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16)
 *
 *
 * @author qiu
 * @date 2021-03-09 下午 08:53
 * @since 1.0
 */
public class HashMapTest {

    public static void main(String[] args) {
        Map<String, String> map =  new HashMap<>(17);
        map.put("1", "1");

    }
}
