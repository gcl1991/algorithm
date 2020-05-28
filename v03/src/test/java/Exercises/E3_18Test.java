package Exercises;
import static org.junit.Assert.*;
import content.DoublyLinkedList;
import org.junit.Test;

public class E3_18Test {
    DoublyLinkedList<Integer> lst  = new DoublyLinkedList<>();
    {
        for(int i=0;i<3;i++){
            lst.add(i);
        }
    }
    @Test
    public void testAddFist(){
        lst.addFirst(-1);
        assertEquals(lst.get(0),Integer.valueOf(-1));
    }

    @Test
    public void testAddLast(){
        lst.addLast(-1);
        assertEquals(lst.get(lst.size()-1),Integer.valueOf(-1));
    }

    @Test
    public void testRemoveFirst(){
        Integer item = lst.removeFirst();

        assertEquals(item,Integer.valueOf(0));
        assertNotEquals(lst.get(0),Integer.valueOf(0));
    }

    @Test
    public void testRemoveLast(){
        Integer item = lst.removeLast();

        assertEquals(item,Integer.valueOf(2));
        assertNotEquals(lst.get(lst.size()-1),Integer.valueOf(2));
    }

    @Test
    public void testGetFirst(){
        Integer item = lst.getFirst();

        assertEquals(item,Integer.valueOf(0));
    }

    @Test
    public void testGetLast(){
        Integer item = lst.getLast();

        assertEquals(item,Integer.valueOf(2));
    }
}
