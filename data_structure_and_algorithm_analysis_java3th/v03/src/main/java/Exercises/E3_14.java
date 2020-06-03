package Exercises;

import content.DoublyLinkedList;

import java.util.List;
import java.util.ListIterator;

public class E3_14<T> {
// 在content DoublyLinkedList 实现
    private DoublyLinkedList<T> lst;
    public E3_14(){
        lst = new DoublyLinkedList<>();
    }

    // 增
    public void add(T x){
        lst.add(x);
    }

    // 查
    public T get(int idx){
        return lst.get(idx);
    }
    public int size(){
        return lst.size();
    }

    // 迭代器
    public ListIterator<T> listIterator(){
        return lst.listIterator();
    }
}
