package content;

import java.util.*;

public class LearnIterator {
    public static <T> void print(Collection<T> array) {
        for (T element : array) {
            System.out.println(element);
        }
    }
    public static <T>void remove1(List<Integer> array){
        int i = 0;
        while (i<array.size()){
            if (array.get(i) % 2==0){
                array.remove(i);
            }
            else
                i++;
        }
    }

    public static void remove2(List<Integer> array){
        for(Integer x:array){
            if (x%2==0){
                array.remove(x);
            }
        }
    }

    public static void remove3(List<Integer> array){
        Iterator<Integer> itr = array.iterator();
        while (itr.hasNext()){
            if (itr.next()%2==0){
                itr.remove();
            }
        }
    }
}
