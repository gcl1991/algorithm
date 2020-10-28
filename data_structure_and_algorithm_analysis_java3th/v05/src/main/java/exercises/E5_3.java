package exercises;

import helpers.Hashs;

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

            if (size > (array.length >> 1))
                rehash();

            return true;
        }

        private void rehash() {
            T[] oldArray = array;

            // Create a new double-sized, empty table
            allocateArray(oldArray.length << 1);
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
        public int clashCount;

        LinearProbing() {
            this(DEFAULT_TABLE_SIZE);
        }

        LinearProbing(int size) {
            allocateArray(size);
        }

        @SuppressWarnings("unchecked")
        void allocateArray(int size) {
            array = (T[]) new Object[nextPrime(size)];
        }

        void insert(T x) {
            int pos = findPos(x);
            if (array[pos] == null) {
                array[pos] = x;
                size++;
            }
            if (size > (array.length >> 1)) {
                rehash();
            }
        }

        int findPos(T x) {
            int currentPos = Hashs.myhash(x, array.length);
            while (array[currentPos] != null && !array[currentPos].equals(x)) {
                currentPos += 1;
                if (currentPos >= array.length) {
                    currentPos -= array.length;
                }
                clashCount++;
            }
            return currentPos;
        }

        void rehash() {
            T[] oldArray = array;
            allocateArray(2 * array.length);
            size = 0;
            Arrays.stream(oldArray)
                    .filter(Objects::nonNull)
                    .forEach(this::insert);
        }
    }

    public static class DoubleHash<T> {
        private static final int DEFAULT_TABLE_SIZE = 101;
        private static final int R = 27;
        private T[] array;
        private int size;
        public  int clashCount;

        DoubleHash() {
            this(DEFAULT_TABLE_SIZE);
        }

        DoubleHash(int size) {
            allocateArray(size);
        }

        @SuppressWarnings("unchecked")
        void allocateArray(int size) {
            array = (T[]) new Object[nextPrime(size)];
        }

        void insert(T x) {
            int pos = findPos(x);
            if (array[pos] == null) {
                array[pos] = x;
                size++;
            }
            if (size > (array.length >> 1)) {
                rehash();
            }
        }

        int findPos(T x) {
            int pos = Hashs.myhash(x, array.length);
            int i = 1;
            int h2 = hash2(x);
            while (array[pos] != null && !array[pos].equals(x)) {
                pos = i * h2;
                if (pos > array.length) {
                    pos -= array.length;
                }
                i++;
                clashCount++;
            }
            return pos;
        }

        int hash2(T x) {
            return R - x.hashCode() % R;
        }

        void rehash() {
            T[] oldArray = array;
            allocateArray(array.length << 1);
            size = 0;
            Arrays.stream(oldArray)
                    .filter(Objects::nonNull)
                    .forEach(this::insert);
        }
    }
}
