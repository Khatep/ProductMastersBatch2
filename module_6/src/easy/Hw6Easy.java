package easy;

import java.util.List;

public class Hw6Easy {
    public static void main(String[] args) {
        //Integer
        ListContainer<Integer> intContainer = new ListContainer<>(List.of(5, 1, 9, 3));
        System.out.println("Integer min: " + intContainer.findMinValue());
        System.out.println("Integer max: " + intContainer.findMaxValue());
        System.out.println("Integer sort: " + intContainer.sort());
        System.out.println("Integer sort desc: " + intContainer.sortDesc());
        System.out.println("Integer has 3? : " + intContainer.isHas(3));
        System.out.println("Integer '> 4': " + intContainer.filterMoreThan(4));
        System.out.println("Integer sum: " + intContainer.sumOfAll().intValue());
        System.out.println();

        //Double
        ListContainer<Double> doubleContainer = new ListContainer<>(List.of(2.5, 7.1, 0.3, 9.3));
        System.out.println("Double min: " + doubleContainer.findMinValue());
        System.out.println("Double max: " + doubleContainer.findMaxValue());
        System.out.println("Double sort: " + doubleContainer.sort());
        System.out.println("Double sort desc: " + doubleContainer.sortDesc());
        System.out.println("Double has 2.5?: " + doubleContainer.isHas(2.5));
        System.out.println("Double '> 3.0': " + doubleContainer.filterMoreThan(3.0));
        System.out.println("Double sum: " + doubleContainer.sumOfAll());
        System.out.println();

        //Long
        ListContainer<Long> longContainer = new ListContainer<>(List.of(114L, 265L, 501L));
        System.out.println("Long min: " + longContainer.findMinValue());
        System.out.println("Long max: " + longContainer.findMaxValue());
        System.out.println("Long sort: " + longContainer.sort());
        System.out.println("Long sort desc: " + longContainer.sortDesc());
        System.out.println("Long has 100?: " + longContainer.isHas(114L));
        System.out.println("Long '> 60': " + longContainer.filterMoreThan(200L));
        System.out.println("Long sum: " + longContainer.sumOfAll().longValue());
    }
}
