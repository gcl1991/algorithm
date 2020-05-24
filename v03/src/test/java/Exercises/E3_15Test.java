package Exercises;

import static org.junit.Assert.*;

import content.DoublyLinkedList;
import org.junit.Test;

import java.util.Iterator;

public class E3_15Test {
    @Test
    public void testSplice(){
        E3_15<Integer> lst1=new E3_15<>();
        DoublyLinkedList<Integer> lst2=new DoublyLinkedList<>();
        for (int i=0;i<5;i++){
            lst1.add(i);
            lst2.add(5-i);
        }
        lst1.splice(lst2);
        assertEquals(lst2.size(),0);
        assertEquals(lst1.size(),10);
        for (Iterator<Integer> itr=lst1.listIterator();itr.hasNext();){
            System.out.print(itr.next());;
        }

        System.out.println();
        for (Iterator<Integer> itr=lst2.listIterator();itr.hasNext();){
            System.out.print(itr.next());;
        }
    }
}
