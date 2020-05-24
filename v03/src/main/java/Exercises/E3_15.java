package Exercises;

import content.DoublyLinkedList;
import content.MyArrayList;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class E3_15<T>{
    private DoublyLinkedList<T> lst;
    public E3_15(){
        lst = new DoublyLinkedList<>();
    }

    public void add(T x){
        lst.add(x);
    }

    public ListIterator<T> listIterator(){
        return lst.listIterator();
    }

    public void splice(DoublyLinkedList<T> lst){
        this.lst.splice(lst);
    }

    public int size(){
        return lst.size();
    }
}
