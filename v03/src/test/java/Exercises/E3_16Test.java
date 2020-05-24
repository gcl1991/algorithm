package Exercises;

import content.DoublyLinkedList;
import org.junit.Test;

import java.util.Iterator;

public class E3_16Test {
    @Test
    public void testReverseIterator() {
        DoublyLinkedList<Integer> lst = new DoublyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            lst.add(i);
        }

        for (Integer x:lst){
            System.out.print(x);
        }
        System.out.println();

        for (Iterator<Integer> rItr = lst.reverseIterator();rItr.hasNext();){
            System.out.print(rItr.next());
        }
        System.out.println();
    }
}
