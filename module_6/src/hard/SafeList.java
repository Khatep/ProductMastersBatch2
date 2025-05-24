package hard;

import java.util.ArrayList;

public class SafeList<T> extends ArrayList<T> {

    @Override
    public boolean add(T element) {
        if (element == null || contains(element)) {
            return false;
        }
        return super.add(element);
    }

    @Override
    public void add(int index, T element) {
        if (element != null && !contains(element) && index > 0 && index < size()) {
            super.add(index, element);
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }
        return super.get(index);
    }

    @Override
    public T set(int index, T element) {
        if (element == null || contains(element) || index < 0 || index >= size()) {
            return null;
        }
        return super.set(index, element);
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }
        return super.remove(index);
    }
}
