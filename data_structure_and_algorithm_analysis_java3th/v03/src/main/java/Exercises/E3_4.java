package Exercises;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class E3_4 {
    public static <T> List<T> intersection(List<T> lst1, List<T> lst2, Comparator<? super T> c) {
        @SuppressWarnings("unchecked")
        T[] lst3 = (T[]) lst2.toArray();
        return lst1.stream().filter(x -> Arrays.binarySearch(lst3, x, c) >= 0).collect(toList());
    }
}
