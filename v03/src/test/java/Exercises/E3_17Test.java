package Exercises;

import content.MyArrayList;
import org.junit.Test;

import java.util.ConcurrentModificationException;

public class E3_17Test {
    @Test(expected = ConcurrentModificationException.class)
    public void testModException(){
        MyArrayList<Integer> lst = new MyArrayList<>();
        for(int i=0;i<10;i++){
            lst.add(i);
        }
        int count = 0;
        for (Integer x:lst){
            if (count==5){
                lst.remove(0);
            }
            count++;
        }
    }
}
