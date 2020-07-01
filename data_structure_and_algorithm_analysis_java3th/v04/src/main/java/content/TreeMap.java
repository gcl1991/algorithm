package content;

import java.util.*;

/**
 * TreeMap vs TreeSet
 * （1）以组合TreeSet的方式实现TreeMap（java是以组合TreeMap的方式实现TreeSet）
 *  Set查询必须使用对象的副本，而在Map查询时只需要k，没有entry的副本的情况下，想要实现增删改查只能通过Set的迭代器实现，效率较低
 * （2）迭代器
 * Map不使用迭代器，而使用三个迭代方法，分别返回key，Value和Entry三者集合。
 * （3）内部类
 * 此内部类需要被外界访问，需要提供getKey/getValue/SetValue等访问器和修改器方法
 * */
public final class TreeMap<K extends Comparable<K>, V> {
    private final BinarySearchTreeSetParent<Entry<K, V>> m;

    TreeMap() {
        m = new BinarySearchTreeSetParent<>();
    }

    // 增
    boolean put(K k, V v) {
        return m.add(new Entry<K, V>(k, v));
    }

    // 删
    V remove(K k){
        for (Iterator<Entry<K,V>> iterator=m.iterator();iterator.hasNext();){
            Entry<K,V> entry= iterator.next();
            if (entry.k.compareTo(k)==0){
                iterator.remove();
                return entry.v;
            }
        }
        return null;
    }
    //改
    boolean set(K k,V v){
        for (Entry<K,V> entry:m){
            if (entry.k.compareTo(k)==0){
                entry.v = v;
                return true;
            }
        }
        return false;
    }
    //查
    V get(K k){
        for (Entry<K,V> entry:m){
            if (entry.k.compareTo(k)==0){
                return entry.v;
            }
        }
        return null;
    }

    boolean containsKey(K k){
        if (isEmpty()){
            return false;
        }
        for (Entry<K,V> entry:m){
            if (entry.k.compareTo(k)==0){
                return true;
            }
        }
        return false;
    }

    boolean isEmpty(){
        return m.isEmpty();
    }

    int size(){
        return m.size();
    }
    // 迭代器
    BinarySearchTreeSetParent<K> keySet(){
        BinarySearchTreeSetParent<K> keys=new BinarySearchTreeSetParent<>();
        for(Entry<K,V> entry:m){
            keys.add(entry.k);
        }
        return keys;
    }

    Collection<V> values(){
        Collection<V> values = new ArrayList<>();
        for(Entry<K,V> entry:m){
            values.add(entry.v);
        }
        return values;
    }
    BinarySearchTreeSetParent<Entry<K, V>> entrySet(){
        return m;
    }

    // 内部类
    private static final class Entry<K extends Comparable<K>, V> implements Comparable<Entry<K, V>> {
        private K k;
        private V v;

        private Entry(K k, V v) {
            this.k = k;
            this.v = v;
        }
        public K getKey(){
            return k;
        }
        public V getValue(){
            return v;
        }
        public void setValue(V v){
            this.v = v;
        }

        @Override
        public int compareTo(Entry<K, V> o) {
            return k.compareTo(o.k);
        }
    }
}
