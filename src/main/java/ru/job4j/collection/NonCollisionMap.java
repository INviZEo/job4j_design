package ru.job4j.collection;

import java.util.*;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        int hash = hash(key);
        int i = hash & (table.length - 1);
        boolean rsl = false;
        /*if ((float) count / capacity >= 0.75) {
            capacity *= 2;
            modCount++;
        }*/
        if (table[i] == null) {
            table[i] = new MapEntry<>(key, value);
            count++;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    private int hash(K hashcode) {
        return hashcode == null ? 0 : (hashcode.hashCode()) ^ (hashcode.hashCode() >>> 16);
    }

    private int indexFor(int hash) {
        return hash % table.length;
    }

    private void expand() {

    }

    @Override
    public V get(K key) {
        int hash = hash(key);
        int i = hash & (table.length - 1);
        V rsl = null;
        if (table[i] != null
                && key.hashCode() == table[i].key.hashCode()
                && Objects.equals(rsl, table[i].value)) {
            rsl = table[i].value;
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        int hash = hash(key);
        int i = hash & (table.length - 1);
        boolean rsl = false;
        count--; // если будет удаление
        modCount++; // если будет удаление
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int mCount = modCount;
            int size = 0;
            @Override
            public boolean hasNext() {
                if (mCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (table.length > size && table[size] == null) {
                    size++;
                }
                return capacity > size;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[size++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NonCollisionMap<?, ?> that = (NonCollisionMap<?, ?>) o;
        return capacity == that.capacity
                && count == that.count
                && modCount == that.modCount
                && Arrays.equals(table, that.table);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(capacity, count, modCount);
        result = result + Arrays.hashCode(table);
        return result;
    }

    public static void main(String[] args) {
        NonCollisionMap<Object, Object> map = new NonCollisionMap<>();
        System.out.println(map.hash(65536));
        System.out.println(map.indexFor(7));
        System.out.println(map.table.length);

    }
}