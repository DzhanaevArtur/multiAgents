package Practices.task4.example;

public class MyLinkedList<T> {

    private LinkedListEl<T> firstEl;

    public void add(T element) {
        LinkedListEl<T> lastEl = findLastEl();
        if (lastEl != null) {
            LinkedListEl<T> newElement = new LinkedListEl<>(element);
            newElement.setLeftEl(lastEl);
            lastEl.setRightEl(newElement);
        } else firstEl = new LinkedListEl<>(element);
    }

    public T get(int index) {
        if (index > (size() - 1)) throw new IndexOutOfBoundsException();
        else {
            LinkedListEl<T> element = firstEl;
            for (int i = 0; i < index; i++) element = element.getRightEl();
            return element.getValue();
        }
    }

    public void remove(int index) {
        if (index > (size() - 1)) throw new IndexOutOfBoundsException();
        else {
            LinkedListEl<T> element = firstEl;
            for (int i = 0; i < index; i++) element = element.getRightEl();
            if (element == firstEl) {
                firstEl = firstEl.getRightEl();
                firstEl.setLeftEl(null);
            } else {
                LinkedListEl<T> leftEl = element.getLeftEl();
                LinkedListEl<T> rightEl = element.getRightEl();
                if (leftEl != null) leftEl.setRightEl(rightEl);
                if (rightEl != null) rightEl.setLeftEl(leftEl);
            }
        }
    }

    private LinkedListEl<T> findLastEl() {
        if (firstEl != null) {
            LinkedListEl<T> rightEl = firstEl;
            while (rightEl != null) {
                if (rightEl.getRightEl() != null) rightEl = rightEl.getRightEl();
                else break;
            }
            return rightEl;
        } else return null;
    }

    public int size() {
        int count = 1;
        if (firstEl != null) {
            LinkedListEl<T> rightEl = firstEl.getRightEl();
            while (rightEl != null) {
                count++;
                rightEl = rightEl.getRightEl();
            }
            return count;
        } else return 0;
    }
}
