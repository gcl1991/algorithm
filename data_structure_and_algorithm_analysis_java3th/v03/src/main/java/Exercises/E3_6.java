package Exercises;

import content.CircularSinglyLinkedList;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class E3_6 {
    public static int josephus(int m, int n) {
        LinkedList<Integer> lst = initLst(n);
        int index = 0;
        while (lst.size()!=1) {
            index = removeElements(lst,index,m);
        }
        return lst.getFirst();
    }

    private static LinkedList<Integer> initLst(int n) {
        LinkedList<Integer> lst = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            lst.add(i);
        }
        return lst;
    }

    public static <T> int removeElements(List<T> lst,int index,int m){
        Iterator<T> itr = lst.iterator();
        while (itr.hasNext()) {
            itr.next();
            index++;
            if (index == m + 1) {
                itr.remove();
                index = 0;
            }
        }
        return index;
    }
}
