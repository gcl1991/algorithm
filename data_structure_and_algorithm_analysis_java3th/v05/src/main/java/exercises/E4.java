package exercises;

import helpers.Hashs;
import helpers.Primes;

import java.lang.reflect.Array;
import java.util.*;

public class E4 {
    public static class SeparateChainingWithSingle<T> {
        private static final int DEFAULT_TABLE_SIZE = 101;
        private List<T>[] array;
        private int size;

        SeparateChainingWithSingle() {
            this(DEFAULT_TABLE_SIZE);
        }

        SeparateChainingWithSingle(int size) {
            allocateArray(size);
        }

        void allocateArray(int size) {
            int nextPrime = Primes.nextPrime(size);
            array = new LinkedList[nextPrime];
            for (int i = 0; i < nextPrime; i++)
                array[i] = new LinkedList<>();
        }

        void insert(T x) {
            int pos = Hashs.myhash(x, array.length);
            if (!array[pos].contains(x)) {
                array[pos].add(0, x);
                size++;
                if (size >= array.length) {
                    rehash();
                }
            }
        }

        void rehash() {
            List<T>[] oldArray = array;
            allocateArray(2 * array.length);
            size = 0;
            Arrays.stream(oldArray)
                    .flatMap(Collection::stream)
                    .filter(Objects::nonNull)
                    .forEach(this::insert);
        }

        boolean contains(T x) {
            int pos = Hashs.myhash(x, array.length);
            if (array[pos] != null) {
                return array[pos].contains(x);
            } else {
                return false;
            }
        }

        boolean remove(T x) {
            int pos = Hashs.myhash(x, array.length);
            if (array[pos] != null && array[pos].contains(x)) {
                array[pos].remove(x);
                size--;
                return true;
            } else {
                return false;
            }
        }

        int size() {
            return size;
        }
    }
}
