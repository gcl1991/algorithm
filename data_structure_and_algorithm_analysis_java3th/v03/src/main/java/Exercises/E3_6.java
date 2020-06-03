package Exercises;

import content.CircularSinglyLinkedList;

import java.util.Iterator;
import java.util.LinkedList;

public class E3_6 {
    public static void josephus(int m, int n) {
        LinkedList<Integer> lst = initLst(n);
        int count=0;
        int index = 0;
        while (lst.size()!=1) {
            Iterator<Integer> itr = lst.iterator();
            while (itr.hasNext()) {
                Integer element = itr.next();
                index++;
                count++;
                if (index == m + 1) {
                    System.out.println("被移除的元素" + element);
                    print(lst);
                    itr.remove();
                    index = 0;
                    print(lst);
                }
            }
        }
        System.out.println(count);
        System.out.println(lst.getFirst()+"号获胜");
    }

    private static LinkedList<Integer> initLst(int n) {
        LinkedList<Integer> lst = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            lst.add(i);
        }
        return lst;
    }

    private static void print(LinkedList<Integer> lst) {
        for (Integer x : lst) {
            System.out.print(x);
        }
        System.out.println();
    }
}
