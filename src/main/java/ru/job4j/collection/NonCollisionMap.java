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
        if ((float) count / (float) capacity >= LOAD_FACTOR) {
            expand();
        }
        int index = getIndex(key);
        boolean rsl = false;
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    private int hash(K key) {
        int hashcode = Objects.hashCode(key);
        return hashcode ^ (hashcode >>> 16);
    }

    private int indexFor(int hash) {
        return hash % capacity;
    }

    private void expand() {
        MapEntry<K, V>[] newTable = new MapEntry[capacity * 2];
        capacity = capacity * 2;
        for (MapEntry<K, V> newT: table) {
            if (newT != null) {
                newTable[getIndex(newT.key)] = newT;
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        int index = getIndex(key);
        V rsl = null;
        if (equalsKey(key)) {
            rsl = table[index].value;
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        int index = getIndex(key);
        boolean rsl = false;
        if (equalsKey(key)) {
            table[index] = null;
            rsl = true;
            count--;
            modCount++;
        }
        return rsl;
    }

    private int getIndex(K key) {
        return indexFor(hash(key));
    }

    private boolean equalsKey(K key) {
        int index = getIndex(key);
        return table[index] != null
                && Objects.hashCode(key) == Objects.hashCode(table[index].key)
                && Objects.equals(table[index].key, key);
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
}