package Exercises;

import org.junit.Test;

import static org.junit.Assert.*;

public class E3_11Test {
    private final E3_11.SingleLinkedList<Integer> sl;

    public E3_11Test() {
        sl = new E3_11.SingleLinkedList<>();
        sl.add(1);
        sl.add(2);
    }

    @Test
    public void testSizeOfEmpty() {
        E3_11.SingleLinkedList<Integer> sl = new E3_11.SingleLinkedList<>();
        assertEquals(sl.size(), 0);
    }

    @Test
    public void testSizeOfNotEmpty() {
        assertEquals(sl.size(), 2);
    }

    @Test
    public void testPrintAll() {
        sl.printAll();
    }

    @Test
    public void testContain(){
        assertTrue(sl.contain(1));
        assertFalse(sl.contain(3));
    }

    @Test
    public void testAddIfNotExist(){
        assertFalse(sl.addIfNotExist(1));
        assertTrue(sl.addIfNotExist(3));
    }

    @Test
    public void testRemoveIfExist(){
        assertTrue(sl.removeIfExist(1));
        assertFalse(sl.removeIfExist(3));
    }
}
