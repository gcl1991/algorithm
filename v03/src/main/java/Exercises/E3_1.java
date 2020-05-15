package Exercises;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class E3_1 {
    public static void printLots(ArrayList<Integer> l, ArrayList<Integer> p){
        for (int i=0;i<p.size();i++){
            Integer px = Collections.min(p);
            Integer lx = l.get(px);
            System.out.println(lx);
            Collections.replaceAll(p,px,Integer.MAX_VALUE);
        }
    }
}
