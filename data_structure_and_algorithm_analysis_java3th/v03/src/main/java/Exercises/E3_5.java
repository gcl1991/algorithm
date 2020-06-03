package Exercises;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class E3_5 {
    public static <T> List<T> union(List<T> lst1, List<T> lst2, Comparator<? super T> c) {
        List<T> small;
        List<T> big;
        if (lst1.size() < lst2.size()) {
            small = lst1;
            big = lst2;
        } else {
            small = lst2;
            big = lst1;
        }

        @SuppressWarnings("unchecked")
        T[] bigArray = (T[]) big.toArray();
        return small.stream().filter(x -> Arrays.binarySearch(bigArray, x, c) == -1).collect(Collectors.toList());
    }
}
