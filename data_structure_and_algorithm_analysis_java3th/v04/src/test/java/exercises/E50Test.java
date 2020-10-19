package exercises;

import org.junit.Test;
import static org.junit.Assert.*;


public class E50Test {
    @Test
    public void testInsert(){
        E50<Integer> e50 = new E50<>();
        e50.insert(1);
        e50.insert(2);
        e50.insert(3);
        assertTrue(e50.contains(1));
        assertTrue(e50.contains(2));
        assertTrue(e50.contains(3));
    }

    @Test
    public void testContains(){
        E50<Integer> e50 = new E50<>();
        e50.insert(1);
        assertTrue(e50.contains(1));
        assertTrue(e50.contains(Integer.valueOf(1)));
        assertFalse(e50.contains(null));
        assertFalse(e50.contains(2));
    }

    @Test
    public void testPrintAsc(){
        E50<Integer> e50 = new E50<>();
        e50.insert(1);
        e50.insert(2);
        e50.insert(3);
        e50.printAsc();
    }
}
