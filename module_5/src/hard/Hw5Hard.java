package hard;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hw5Hard {
    public static void main(String[] args) {
        //Task 1
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number: ");
        int count = sc.nextInt();


        List<Integer> numbers = new ArrayList<>();
        System.out.println("Enter the " + count + " numbers: ");
        for (int i = 0; i < count; i++) {
            numbers.add(sc.nextInt());
        }
        System.out.println('\n' + "List: ");
        numbers.forEach(n -> System.out.print(n + " "));

        //Task 2
        System.out.println('\n' + "Unique list:");
        removeDuplicates(numbers).forEach(n -> System.out.print(n + " "));
    }

    //Task 2
    public static List<Integer> removeDuplicates(List<Integer> list) {
        List<Integer> unique = list.stream().distinct().toList();
        list.clear();
        list.addAll(unique);
        return list;
    }
}
