package exercises;

import static org.junit.Assert.*;
import org.junit.Test;

public class BinarySearchTreeSetTest {
    private BinarySearchTreeSet<Integer> treeSet = new BinarySearchTreeSet<>();
    @Test
    public void testConstructor(){
        assertEquals(treeSet.size(),0);
    }

    @Test
    public void testAddDuplicate(){
        boolean isOkFirst= treeSet.add(1);
        boolean isOkSecond = treeSet.add(1);
        assertTrue(isOkFirst);
        assertFalse(isOkSecond);
        assertEquals(treeSet.size(),1);
    }

    @Test
    public void testRemove(){
        boolean isOk = treeSet.add(1);
        treeSet.remove(1);
        assertTrue(isOk);
        assertEquals(treeSet.size(),0);
    }
}
