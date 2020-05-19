package content;

import content.LearnIterator;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LearnIteratorTest {
    private ArrayList<Integer> arrayList = new ArrayList<>();
    private LinkedList<Integer> linkedList = new LinkedList<>();

    private void filling(List<Integer> array,int n){
        for (int i=0;i<n;i++){
            arrayList.add(i);
        }
    }
    @Before
    public void fillarray(){
        int n = 400_000;
        filling(arrayList,n);
        filling(linkedList,n);
    }

    @Test
    public void remove1ArrayListTest() {
        LearnIterator.remove1(arrayList);
    }

    @Test
    public void remove1LinkedListTest() {
        LearnIterator.remove1(linkedList);
    }

    @Test
    public void remove2ArrayListTest(){
        LearnIterator.remove2(arrayList);
    }

    @Test
    public void remove2LinkedListTest(){
        LearnIterator.remove2(linkedList);
    }

    @Test
    public void remove3ArrayListTest(){
        LearnIterator.remove3(arrayList);
    }

    @Test
    public void remove3LinkedListTest(){
        LearnIterator.remove3(linkedList);
    }
}
