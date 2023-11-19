package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (container.length <= size) {
            container = grow();
            modCount++;
        }
        container[size] = value;
        size++;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T oldValue = container[index];
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T el = get(index);
        System.arraycopy(container, index + 1, container, index, size - index - 1);
        size--;
        modCount++;
        return el;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            final int mCount = modCount;
            int n = 0;
            @Override
            public boolean hasNext() {
                if (mCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return size > n;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[n++];
            }
        };
    }

    private T[] grow() {
        return Arrays.copyOf(container, (container.length + 1) * 2);
    }
}