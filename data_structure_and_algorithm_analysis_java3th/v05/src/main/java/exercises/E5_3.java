package exercises;

import content.QuadraticProbingHashTable;
import helpers.Primes;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

import static helpers.Primes.nextPrime;

public class E5_3 {
    public static class SeparateChaining<T> {
        private static final int DEFAULT_TABLE_SIZE = 10;
        public int clashCount = 0;
        private int currentSize = 0;
        // 为什么用数组？因为需要O(1)快速查找，是否可以用ArrayList？
        private List<T>[] theLists;

        public SeparateChaining() {
            this(DEFAULT_TABLE_SIZE);
        }

        public SeparateChaining(int size) {
            // 为什么用链表？
            theLists = new LinkedList[nextPrime(size)];
            for (int i = 0; i < theLists.length; i++) {
                theLists[i] = new LinkedList<>();
            }
        }

        public void insert(T x) {
            List<T> whichList = theLists[myhash(x)];
            if (!whichList.contains(x)) {
                whichList.add(x);
                // 问：为什么放到add之后而不是之前？
                // 答：为了保持函数的原子性，如果add抛出异常，则破坏了函数原子性。
                if (++currentSize > theLists.length)
                    rehash();
                clashCount++;
            }
        }

        private int myhash(T x) {
            int hashVal = x.hashCode();
            hashVal %= theLists.length;
            // 应对负值HASH
            if (hashVal < 0) {
                hashVal += theLists.length;
            }
            return hashVal;
        }

        private void rehash() {
            List<T>[] oldLists = theLists;

            theLists = new List[nextPrime(2 * theLists.length)];
            for (int i = 0; i < theLists.length; i++) {
                theLists[i] = new LinkedList<>();
            }

            currentSize = 0;
            Arrays.stream(oldLists).forEach(x -> x.forEach(this::insert));
        }
    }

    public static class QuadraticProbing<T> {
        private static final int DEFAULT_TABLE_SIZE = 101;

        private T[] array;
        // 标记有多少桶被占用
        private int occupied;
        private int theSize;
        public int clashCount;

        public QuadraticProbing() {
            this(DEFAULT_TABLE_SIZE);
        }

        private QuadraticProbing(int size) {
            allocateArray(size);
            doClear();
        }

        private void allocateArray(int arraySize) {
            array = (T[]) new Object[nextPrime(arraySize)];
        }

        private void doClear() {
            occupied = 0;
            Arrays.fill(array, null);
        }

        public boolean insert(T x) {
            int currentPos = findPos(x);

            if (array[currentPos] == null)
                ++occupied;
            array[currentPos] = x;
            theSize++;

            if (occupied > array.length / 2)
                rehash();

            return true;
        }

        private void rehash() {
            T[] oldArray = array;

            // Create a new double-sized, empty table
            allocateArray(2 * oldArray.length);
            occupied = 0;
            theSize = 0;

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
            int currentPos = myhash(x);

            while (array[currentPos] != null && !array[currentPos].equals(x)) {
                currentPos += offset;  // Compute ith probe
                offset += 2;
                if (currentPos >= array.length)
                    currentPos -= array.length;
                clashCount++;
            }

            return currentPos;
        }

        private int myhash(T x) {
            int hashVal = x.hashCode();

            hashVal %= array.length;
            if (hashVal < 0)
                hashVal += array.length;

            return hashVal;
        }


    }
}
