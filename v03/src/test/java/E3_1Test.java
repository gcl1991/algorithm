import Exercises.E3_1;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class E3_1Test {
    @Test
    public void printLots(){
        Integer[] p1 = {0,1,2};
        Integer[] l1 = {4,5,6};
        ArrayList<Integer> p2 = new ArrayList<>();
        ArrayList<Integer> l2 = new ArrayList<>();
        for (int i=0;i<l1.length;i++){
            l2.add(l1[i]);
            p2.add(p1[i]);
        }

        E3_1.printLots(l2,p2);
    }
}
