package Exercises;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class E3_14Test {
    private E3_14 lst;

    public E3_14Test() {
        lst = new E3_14();
        for (int i = 0; i < 10; i++) {
            lst.add(i);
        }
    }


    @Test
    public void testHasNext() {
        int count = 0;
        ListIterator<Integer> itr = lst.listIterator();
        while (itr.hasNext()) {
            itr.next();
            count++;
        }
        assertEquals(count, lst.size());
    }

    @Test
    public void testNext() {
        int count = 0;
        ListIterator<Integer> itr = lst.listIterator();
        while (itr.hasNext()) {
            Integer item = itr.next();
            assertEquals(item, lst.get(count));
            count++;
        }
    }

    @Test()
    public void testNextException() {
        ListIterator<Integer> itr = lst.listIterator();
        int count = 0;
        try {
            while (true) {
                itr.next();
                count++;
            }
        } catch (NoSuchElementException e) {
            assertEquals(count, lst.size());
        }
    }

    @Test
    public void testHasPrevious() {
        int count = lst.size();
        ListIterator<Integer> itr = lst.listIterator();
        assertFalse(itr.hasPrevious());
        while (itr.hasNext()) {
            itr.next();
        }
        while (itr.hasPrevious()) {
            itr.previous();
            count--;
        }
        assertEquals(count, 0);
    }

    @Test
    public void testPrevious() {
        int count = lst.size() - 1;
        ListIterator<Integer> itr = lst.listIterator();
        while (itr.hasNext()) {
            itr.next();
        }
        while (itr.hasPrevious()) {
            Integer item = itr.previous();
            assertEquals(item, lst.get(count));
            count--;
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void testPreviousException() {
        ListIterator<Integer> itr = lst.listIterator();
        itr.previous();
    }

    @Test
    public void testPrevNextMove() {
        int count = 0;
        ListIterator<Integer> itr = lst.listIterator();
        while (itr.hasNext()) {
            Integer itemN = itr.next();
            if (count % 2 == 0) {
                if (itr.hasPrevious()){
                    Integer itemP = itr.previous();
                    assertEquals(itemN,itemP);
                }
            }
            count++;
        }
    }
}
