package Practices.task4.self;

public class RBTree<T> {

    private RBTreeEl<T> start;

    public int size() {
        int count = 1;
        RBTreeEl<T> rightValue = start.getRightValue();
        RBTreeEl<T> leftValue = start.getLeftValue();
        while (rightValue != null && leftValue != null) {
            count++;
            rightValue = rightValue.getRightValue();
            leftValue = leftValue.getLeftValue();
        }
        return start != null ? count : 0;
    }

    public void add(T value) {
        start = addRecursive(start, value);
    }

    public void remove(T value) {
        start = deleteRecursive(start, value);
    }

    public T get(int index) {
        if (index > (size() - 1)) throw new IndexOutOfBoundsException();
        else {
            RBTreeEl<T> element = start;
            for (int i = 0; i < index; i++) element = element.getLeftValue();
            return element.getValue();
        }
    }

    private RBTreeEl<T> addRecursive(RBTreeEl<T> current, T value) {
        if (current == null) return new RBTreeEl<>(value);
        boolean lower = Integer.parseInt(String.valueOf(value)) < Integer.parseInt(String.valueOf(current.getValue()));
        boolean bigger = Integer.parseInt(String.valueOf(value)) > Integer.parseInt(String.valueOf(current.getValue()));
        if (lower) current.setLeftValue(addRecursive(current.getLeftValue(), value));
        else if (bigger) current.setRightValue(addRecursive(current.getRightValue(), value));
        else return current;
        return current;
    }

    private RBTreeEl<T> deleteRecursive(RBTreeEl<T> current, T value) {
        if (current == null) return null;

        if (value.equals(current.getValue())) {
            if (current.getLeftValue() == null && current.getRightValue() == null) return null;
            if (current.getRightValue() == null) return current.getLeftValue();
            if (current.getLeftValue() == null) return current.getRightValue();
            T smallestValue = findSmallestValue(current.getRightValue());
            current.setValue(smallestValue);
            current.setRightValue(deleteRecursive(current.getRightValue(), smallestValue));
            return current;
        }
        if (Integer.parseInt(String.valueOf(value)) < Integer.parseInt(String.valueOf(current.getValue()))) {
            current.setLeftValue(deleteRecursive(current.getLeftValue(), value));
            return current;
        }
        current.setRightValue(deleteRecursive(current.getRightValue(), value));
        return current;
    }

    private T findSmallestValue(RBTreeEl<T> root) {
        return root.getLeftValue() == null ? root.getValue() : findSmallestValue(root.getLeftValue());
    }

}
