package Exercises;
import static org.junit.Assert.*;
import org.junit.Test;

public class E3_19Test {
    E3_19.NoMarkerDoublyLinkedList<Integer> lst = new E3_19.NoMarkerDoublyLinkedList<>();
    @Test
    public void testConstructor(){
        assertEquals(lst.size(),0);
        assertTrue(lst.isEmpty());
    }

    @Test
    public void testAdd(){
        lst.add(1);
        lst.add(2);
        lst.add(1,3);
        assertEquals(lst.size(),3);
        assertEquals(lst.get(0),Integer.valueOf(1));
        assertEquals(lst.get(1),Integer.valueOf(3));
        assertEquals(lst.get(2),Integer.valueOf(2));
    }
}
