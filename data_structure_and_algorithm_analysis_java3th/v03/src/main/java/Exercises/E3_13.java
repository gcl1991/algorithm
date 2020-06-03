package Exercises;

import content.MyArrayList;

import java.util.ListIterator;

public class E3_13<T> {
    // 在content MyArrayList 实现
    private MyArrayList<T> lst;
    public E3_13(){
        lst = new MyArrayList<>();
    }

    public void add(T x){
        lst.add(x);
    }

    public T get(int idx){
        return lst.get(idx);
    }

    public ListIterator<T> listIterator() {
        return lst.listIterator();
    }
}
