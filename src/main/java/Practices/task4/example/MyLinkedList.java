package Practices.task4.example;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class MyLinkedList<T> implements Iterable<T> {

    private LinkedListEl<T> firstEl;
    transient Object[] elementData;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private static final int DEFAULT_CAPACITY = 10;
    public static final int SOFT_MAX_ARRAY_LENGTH = Integer.MAX_VALUE - 8;


    public void add(T element) {

        LinkedListEl<T> lastEl = findLastEl();
        if (lastEl != null) {
            LinkedListEl<T> newElement = new LinkedListEl<>(element);
            newElement.setLeftEl(lastEl);
            lastEl.setRightEl(newElement);
        } else firstEl = new LinkedListEl<>(element);
    }

    public boolean addAll(Collection<? extends T> c) {

        int size = size();
        Object[] a = c.toArray();
        int numNew = a.length;
        if (numNew == 0) return false;
        Object[] elementData;
        final int s;
        if (numNew > (elementData = this.elementData).length - (s = size)) elementData = grow(s + numNew);
        System.arraycopy(a, 0, elementData, s, numNew);
        size = s + numNew;
        return true;
    }

    private Object[] grow(int minCapacity) {

        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) return elementData = Arrays.copyOf(elementData, newLength(oldCapacity, minCapacity - oldCapacity, oldCapacity >> 1));
        else return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
    }

    public static int newLength(int oldLength, int minGrowth, int prefGrowth) {

        int prefLength = oldLength + Math.max(minGrowth, prefGrowth);
        if (0 < prefLength && prefLength <= SOFT_MAX_ARRAY_LENGTH) return prefLength;
        else return hugeLength(oldLength, minGrowth);
    }

    private static int hugeLength(int oldLength, int minGrowth) {

        int minLength = oldLength + minGrowth;
        if (minLength < 0) throw new OutOfMemoryError( "Required array length " + oldLength + " + " + minGrowth + " is too large");
        else return Math.max(minLength, SOFT_MAX_ARRAY_LENGTH);
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

    @Override
    public Iterator<T> iterator() {

        return new Iterator<>() {

            @Override
            public boolean hasNext() { return firstEl.getRightEl() != null; }

            @Override
            public T next() { return firstEl.getRightEl().getValue(); }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) { Iterable.super.forEach(action); }

    @Override
    public Spliterator<T> spliterator() { return Iterable.super.spliterator(); }

    public Stream<T> stream() { return StreamSupport.stream(spliterator(), false); }
}
