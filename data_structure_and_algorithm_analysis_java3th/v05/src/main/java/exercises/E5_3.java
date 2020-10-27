package exercises;

import helpers.Hashs;
import helpers.Primes;

import java.util.*;

import static helpers.Primes.nextPrime;

public class E5_3 {
    public static class QuadraticProbing<T> {
        private static final int DEFAULT_TABLE_SIZE = 101;

        private T[] array;
        // 标记有多少桶被占用
        private int size;
        public int clashCount;

        public QuadraticProbing() {
            this(DEFAULT_TABLE_SIZE);
        }

        private QuadraticProbing(int size) {
            allocateArray(size);
        }

        private void allocateArray(int size) {
            array = (T[]) new Object[nextPrime(size)];
        }

        public boolean insert(T x) {
            int currentPos = findPos(x);

            if (array[currentPos] == null)
                ++size;
            array[currentPos] = x;

            if (size > array.length / 2)
                rehash();

            return true;
        }

        private void rehash() {
            T[] oldArray = array;

            // Create a new double-sized, empty table
            allocateArray(2 * oldArray.length);
            size = 0;

            // Copy table over
            for (T entry : oldArray)
                if (entry != null)
                    insert(entry);
        }

        // f(x)=x^2;
        // f(x)=f(x-1)+2x-1
        // 采用此公式计算x^2，以加法代替乘法提高计算速度
        private int findPos(T x) {
            int offset = 1;
            int currentPos = Hashs.myhash(x, array.length);

            while (array[currentPos] != null && !array[currentPos].equals(x)) {
                currentPos += offset;  // Compute ith probe
                offset += 2;
                if (currentPos >= array.length)
                    currentPos -= array.length;
                clashCount++;
            }

            return currentPos;
        }
    }

    public static class LinearProbing<T> {
        private static final int DEFAULT_TABLE_SIZE = 101;
        private T[] array;
        public int size;

        LinearProbing() {
            this(DEFAULT_TABLE_SIZE);
        }

        @SuppressWarnings("unchecked")
        LinearProbing(int size) {
            array = (T[]) new Object[size];
        }

        void insert(T x) {
            int pos = findPos(x);
            if(array[pos]==null){
                array[pos] = x;
                size++;
            }
        }

        int findPos(T x) {
            int currentPos = Hashs.myhash(x, array.length);
            while (array[currentPos] != null && !array[currentPos].equals(x)) {
                currentPos += 1;
                if (currentPos >= array.length) {
                    currentPos -= array.length;
                }
            }
            return currentPos;
        }


    }
}
