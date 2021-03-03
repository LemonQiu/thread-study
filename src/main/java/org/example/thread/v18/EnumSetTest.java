package org.example.thread.v18;

import java.util.EnumSet;
import java.util.Set;

/**
 * @Author qiu
 * @Date 2020/12/16 23:28
 *
 */
public class EnumSetTest {

    public static void main(String[] args) {
        // 初始集合不含任何数据
//        EnumSet<DayEnum> enums1 = EnumSet.noneOf(DayEnum.class);
        // 初始集合包括指定枚举类型的所有枚举值
//        EnumSet<DayEnum> enums2 = EnumSet.allOf(DayEnum.class);
        // 初始集合包括枚举值中指定范围的元素
//        EnumSet<DayEnum> enums3 = EnumSet.range(DayEnum.MONDAY, DayEnum.FRIDAY);
        // 初始集合包括参数中的所有元素
//        EnumSet<DayEnum> enums4 = EnumSet.of(DayEnum.MONDAY, DayEnum.SUNDAY);

        Worker[] workers = {new Worker("张三", EnumSet.of(DayEnum.MONDAY, DayEnum.SATURDAY)),
                            new Worker("李四", EnumSet.of(DayEnum.MONDAY, DayEnum.SATURDAY, DayEnum.SUNDAY)),
                            new Worker("王五", EnumSet.of(DayEnum.MONDAY, DayEnum.FRIDAY))};

        // 哪些天一个人都不会来？
        Set<DayEnum> dayEnumSet1 = EnumSet.allOf(DayEnum.class);
        for (Worker worker : workers) {
            dayEnumSet1.removeAll(worker.getDayEnumSet());
        }
        System.out.println(dayEnumSet1 + "这些天，所有人都不会来！！！");

        // 有哪些天至少会有一个人来？
        Set<DayEnum> dayEnumSet2 = EnumSet.noneOf(DayEnum.class);
        for (Worker worker : workers) {
            dayEnumSet2.addAll(worker.getDayEnumSet());
        }
        System.out.println(dayEnumSet2 + "这些天，至少会有一个人来！！！");
    }

   static class Worker {
        private String name;
        private Set<DayEnum> dayEnumSet;

       public Worker(String name, Set<DayEnum> dayEnumSet) {
           this.name = name;
           this.dayEnumSet = dayEnumSet;
       }

       public String getName() {
           return name;
       }

       public void setName(String name) {
           this.name = name;
       }

       public Set<DayEnum> getDayEnumSet() {
           return dayEnumSet;
       }

       public void setDayEnumSet(Set<DayEnum> dayEnumSet) {
           this.dayEnumSet = dayEnumSet;
       }
   }

    static enum DayEnum {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }
}





