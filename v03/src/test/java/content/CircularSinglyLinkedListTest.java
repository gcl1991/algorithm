package content;

import content.CircularSinglyLinkedList;
import org.junit.After;
import org.junit.Test;

import java.util.Iterator;

public class CircularSinglyLinkedListTest {
    private CircularSinglyLinkedList<Integer> lst;

    public CircularSinglyLinkedListTest() {
        lst = new CircularSinglyLinkedList<>();
        for (int i = 1; i <= 10; i++) {
            lst.add(i);
        }
    }

    @After
    public void print(){
        for (Integer x : lst) {
            System.out.print(x);
        }
        System.out.println();
    }

    @Test
    public void addHeadTest() {
        lst.add(0,-1);
    }

    @Test
    public void addTailTest() {
        lst.add(lst.size(),-1);
    }

    @Test
    public void addMiddleTest(){
        lst.add(lst.size()/2,-1);
    }

    @Test
    public void removeHeadTest(){
        lst.remove(0);
    }

    @Test
    public void removeTailTest(){
        lst.remove(lst.size()-1);
    }

    @Test
    public void removeMiddleTest(){
        lst.remove(lst.size()/2);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void removeHeadOutIndexTest(){
        lst.remove(-1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void removeTailOutIndexTest(){
        lst.remove(lst.size());
    }

    @Test
    public void iteratorTest(){
        Iterator<Integer> itr = lst.iterator();
        int index=1;
        while (itr.hasNext()) {
            Integer element = itr.next();
            index++;
            if(index%2==0){
                System.out.println(element);
                itr.remove();
            }
            print();
        }
    }
}
