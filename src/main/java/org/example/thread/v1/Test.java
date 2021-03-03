package org.example.thread.v1;

/**
 * @Author qiu
 * @Date 2020/12/23 23:30
 *
 *  0 ldc #2
 *  2 astore_1
 *  3 iconst_0
 *  4 istore_2
 *  5 iload_2
 *  6 iconst_3
 *  7 if_icmpge 36 (+29)
 * 10 new #3 <java/lang/StringBuilder>
 * 13 dup
 * 14 invokespecial #4 <java/lang/StringBuilder.<init>>
 * 17 aload_1
 * 18 invokevirtual #5 <java/lang/StringBuilder.append>
 * 21 ldc #6 <ad>
 * 23 invokevirtual #5 <java/lang/StringBuilder.append>
 * 26 invokevirtual #7 <java/lang/StringBuilder.toString>
 * 29 astore_1
 * 30 iinc 2 by 1
 * 33 goto 5 (-28)
 * 36 return
 */
public class Test {
    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            builder.append("ad");
        }
    }
}
