package easy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class ListContainer<T extends Number> {
    private final List<T> list = new ArrayList<>();

    public ListContainer(List<T> list) {
        this.list.addAll(list);
    }

    public T findMinValue() {
        return list.stream()
                .min(Comparator.comparingDouble(Number::doubleValue))
                .orElseThrow(() -> new NoSuchElementException("List is empty"));
    }

    public T findMaxValue() {
        return list.stream()
                .max(Comparator.comparingDouble(Number::doubleValue))
                .orElseThrow(() -> new NoSuchElementException("List is empty"));
    }

    public List<T> sort() {
        return list.stream()
                .sorted()
                .toList();
    }

    public List<T> sortDesc() {
        return list.stream()
                .sorted(Collections.reverseOrder())
                .toList();
    }

    public boolean isHas(T value) {
        return list.stream().anyMatch(v -> v.equals(value));
    }

    public List<T> filterMoreThan(T value) {
        return list.stream()
                .filter(v -> Comparator.comparingDouble(Number::doubleValue).compare(v, value) > 0)
                .toList();
    }

    public Number sumOfAll() {
        double sum = list.stream()
                .mapToDouble(Number::doubleValue)
                .sum();
        BigDecimal roundedSum = BigDecimal.valueOf(sum).setScale(3, RoundingMode.HALF_UP);
        return roundedSum.doubleValue();
    }
}
