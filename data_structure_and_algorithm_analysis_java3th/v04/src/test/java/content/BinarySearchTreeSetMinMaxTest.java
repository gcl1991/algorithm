package content;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Iterator;

public class BinarySearchTreeSetMinMaxTest {
    BinarySearchTreeSetMinMax<Integer> treeSet = new BinarySearchTreeSetMinMax<>();
    @Test
    public void testConstructor(){
        assertTrue(treeSet.isEmpty());
    }

    @Test
    public void testAdd(){
        treeSet.add(1);
        assertEquals(1,treeSet.size());
    }

    @Test
    public void testAddDuplicate(){
        treeSet.add(1);
        treeSet.add(1);
        assertEquals(1,treeSet.size());
    }

    @Test
    public void testGetMin(){
        treeSet.add(1);
        treeSet.add(2);
        assertEquals(1,(int) treeSet.getMin());
    }

    @Test
    public void testGetMax(){
        treeSet.add(1);
        treeSet.add(2);
        assertEquals(2,(int) treeSet.getMax());
    }

    @Test
    public void testRemove(){
        treeSet.add(1);
        treeSet.add(2);
        treeSet.remove(2);
        assertFalse(treeSet.contains(2));
    }

    @Test
    public void testContains(){
        treeSet.add(1);
        assertTrue(treeSet.contains(1));
    }

    @Test
    public void testIteratorLRTree(){
        treeSet.add(2);
        treeSet.add(1);
        treeSet.add(3);
        Iterator<Integer> iterator = treeSet.iterator();
        assertEquals(1, (int)iterator.next());
        assertEquals(2, (int)iterator.next());
        assertEquals(3, (int)iterator.next());
    }

    @Test
    public void testIteratorJustOne(){
        treeSet.add(1);
        for(Iterator<Integer> iterator=treeSet.iterator();iterator.hasNext();){
            assertEquals(1,(int) iterator.next());
        }
    }

    @Test
    public void testIteratorLeftTree(){
        treeSet.add(1);
        treeSet.add(2);
        treeSet.add(3);
        int i=0;
        for(Iterator<Integer> iterator=treeSet.iterator();iterator.hasNext();){
            assertEquals(++i,(int) iterator.next());
        }
    }

    @Test
    public void testIteratorRightTree(){
        treeSet.add(3);
        treeSet.add(2);
        treeSet.add(1);
        int i=0;
        for(Iterator<Integer> iterator=treeSet.iterator();iterator.hasNext();){
            assertEquals(++i,(int) iterator.next());
        }
    }
}
