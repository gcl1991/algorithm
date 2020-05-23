package Exercises;

import org.junit.Test;

import java.util.Iterator;
import java.util.ListIterator;

import static org.junit.Assert.*;

public class E3_13Test {
    private E3_13<Integer> lst;
    private ListIterator<Integer> iterator;

    public E3_13Test() {
        lst = new E3_13<>();
        for (int i = 0; i < 10; i++) {
            lst.add(i);
        }
        iterator = lst.listIterator();
    }

    @Test
    public void testHasPreviousEmpty() {
        assertFalse(iterator.hasPrevious());
    }

    @Test
    public void testAdd() {
        int count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            if (count == 5) {
                iterator.add(-1);
            }
            count++;
        }
        assertEquals(Integer.valueOf(5), lst.get(5));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSetEmpty() {
        iterator.set(1);
    }

    @Test
    public void testPrevious() {
        int count = 0;
        Integer v = null;
        while (iterator.hasNext()) {
            iterator.next();
            if (count == 5) {
                v = iterator.previous();
            }
            count++;
        }
        assertEquals(v, lst.get(5));

    }
}
