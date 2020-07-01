package content;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Iterator;

public class BinarySearchTreeSetParentTest {
    private BinarySearchTreeSetParent<Integer> treeSet = new BinarySearchTreeSetParent<>();
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

    @Test
    public void testFindMin(){
        treeSet.add(2);
        treeSet.add(1);
        assertEquals(treeSet.findMin(),Integer.valueOf(1));
    }

    @Test
    public void testFindMax(){
        treeSet.add(2);
        treeSet.add(1);
        assertEquals(treeSet.findMax(),Integer.valueOf(2));
    }

    @Test
    public void testContains(){
        assertFalse(treeSet.contains(1));
        treeSet.add(1);
        assertTrue(treeSet.contains(1));
    }

    @Test
    public void testIteratorLRTree(){
        treeSet.add(2);
        treeSet.add(1);
        treeSet.add(3);
        int i=0;
        for(Iterator<Integer> iterator=treeSet.iterator();iterator.hasNext();){
            assertEquals(++i,(int)iterator.next());
        }
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

    @Test
    public void testIteratorRemove(){
        treeSet.add(3);
        treeSet.add(2);
        treeSet.add(1);
        for(Iterator<Integer> iterator=treeSet.iterator();iterator.hasNext();){
            if(iterator.next().compareTo(2)==0){
                iterator.remove();
            }
        }
        assertFalse(treeSet.contains(2));
    }
}
