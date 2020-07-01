package content;

import static org.junit.Assert.*;
import org.junit.Test;

public class TreeMapTest {
    TreeMap<Integer,Integer> m = new TreeMap<>();
    @Test
    public void testPut(){
        m.put(1,2);
        assertEquals(1, m.size());
        assertEquals(2,(int) m.get(1));
    }

    @Test
    public void testRemove(){
        m.put(1,2);
        assertEquals(2, (int) m.remove(1));
        assertFalse(m.containsKey(1));
    }

    @Test
    public void testSet(){
        m.put(1,2);
        assertTrue(m.set(1,3));
        assertEquals(3,(int) m.get(1));
    }

    @Test
    public void testGet(){
        m.put(1,2);
        assertEquals(2,(int) m.get(1));
    }

    @Test
    public void testContainsKey(){
        m.put(1,2);
        assertTrue(m.containsKey(1));
    }

    @Test
    public void testSize(){
        assertEquals(0,m.size());
        m.put(1,2);
        assertEquals(1,m.size());
    }
}
