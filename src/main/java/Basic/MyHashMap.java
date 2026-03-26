package Basic;

import java.io.Serializable;
import java.util.*;

/**
 * 功能：
 * 作者：yml
 * 日期：2026/3/129:47
 */

public class MyHashMap<K,V> extends AbstractMap<K,V> implements Map<K,V>, Cloneable, Serializable {

    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    static final int MAXIMUM_CAPACITY = 1 << 30;
    static final int TREEIFY_THRESHOLD = 8;
    static final int UNTREEIFY_THRESHOLD = 6;

    /// 被 transient 修饰的变量，在序列化时，不会被序列化
    transient MyHashMap.Node<K,V>[] table;
    final float loadFactor;
    int threshold;

    public MyHashMap(int initialCapacity, float loadFactor){
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity: " +
                    initialCapacity);
        } else {
            if (initialCapacity > 1 << 30){
                initialCapacity = 1 << 30;
            }

            if (!(loadFactor <= 0.0F) && !Float.isNaN(loadFactor)) {
                this.loadFactor = loadFactor;
                this.threshold = tableSizeFor(initialCapacity);
            } else {
                throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
            }
        }
    }

    public MyHashMap(){
        this.loadFactor = 0.75F;
    }

    static final int tableSizeFor(int cap) {
        int n = -1 >>> Integer.numberOfLeadingZeros(cap - 1);
        return n < 0 ? 1 : (n >= 1073741824 ? 1073741824 : n + 1);
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return Set.of();
    }

    static class Node<K,V> implements Map.Entry<K,V>{
        final int hash;
        final K key;
        V value;
        MyHashMap.Node<K, V> next;

        Node(int hash, K key, V value, MyHashMap.Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V newValue) {
            V oldValue = this.value;
            this.value = newValue;
            return oldValue;
        }
    }
}
