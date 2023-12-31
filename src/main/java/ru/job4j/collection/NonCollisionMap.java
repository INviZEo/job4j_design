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
        int index = getIndex(key);
        boolean rsl = false;
        if ((float) count / (float) capacity >= LOAD_FACTOR) {
            expand();
        }
        if (table[i] == null) {
            table[i] = new MapEntry<>(key, value);
            count++;
            modCount++;
            rsl = true;
        } else if (hash(key) == Objects.hashCode(table[index].key)
                && Objects.equals(key, table[index].key)) {
                table[i] = new MapEntry<>(key, value);
                rsl = true;
        }
        return rsl;
    }

    private int hash(K key) {
        int hashcode = Objects.hashCode(key);
        return hashcode ^ (hashcode >>> 16);
    }

    private int indexFor(int hash) {
        return hash % table.length;
    }

    private void expand() {
        MapEntry<K, V>[] newTable = new MapEntry[capacity * 2];
        capacity = capacity * 2;
        for (MapEntry<K, V> newT: table) {
            if (newT != null) {
                put(newT.key, newT.value);
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        int index = getIndex(key);
        V rsl = null;
        if (table[index] != null
                && Objects.hashCode(key) == Objects.hashCode(table[index].key)
                && Objects.equals(key, table[index].key)) {
            rsl = table[index].value;
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        int index = getIndex(key);
        boolean rsl = false;
        if (table[index] != null
                && Objects.hashCode(key) == Objects.hashCode(table[index].key)
                && Objects.equals(key, table[index].key)) {
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