package hard;

public class Hw6Hard {
    public static void main(String[] args) {
        SafeList<String> list = new SafeList<>();
        list.add("apple");
        list.add("banana");
        list.add(null);       // Не добавляется
        list.add("apple");    // Не добавляется

        System.out.println(list.get(0));  // apple
        System.out.println(list.get(5));  // null (не ошибка!)

        System.out.println(list);
    }
}
