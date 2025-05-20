package medium;

import easy.DayOfWeek;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hw5Medium {
    public static void main(String[] args) {
        List<DayOfWeek> days = new ArrayList<>();
        days.addAll(Arrays.stream(DayOfWeek.values()).toList());
        days.forEach(System.out::println);

        System.out.println(isWeekend(DayOfWeek.MONDAY));
        System.out.println(isWeekend(DayOfWeek.SATURDAY));
    }

    public static boolean isWeekend(DayOfWeek day) {
        switch (day) {
            case SATURDAY, SUNDAY -> {
                return true;
            }
            default -> {
                return false;
            }
        }
    }
}
