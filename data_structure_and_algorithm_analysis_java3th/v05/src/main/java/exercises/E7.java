package exercises;
import static helpers.Primes.*;

public class E7 {
    public static class QuadraticProbingHashTable<T> {
        private static final int DEFAULT_TABLE_SIZE = 101;
        private HashEntry<T>[] array; // The array of elements
        private int occupied;                 // The number of occupied cells
        private int theSize;                  // Current size

        public QuadraticProbingHashTable() {
            this(DEFAULT_TABLE_SIZE);
        }

        public QuadraticProbingHashTable(int size) {
            allocateArray(size);
            doClear();
        }

        public boolean insert(T x) {
            // Insert x as active
            int currentPos = findPosForInsert(x);
            if (isActive(currentPos))
                return false;

            if (array[currentPos] == null)
                ++occupied;
            array[currentPos] = new HashEntry<>(x, true);
            theSize++;

            // Rehash; see Section 5.5
            if (occupied > array.length / 2)
                rehash();

            return true;
        }

        private void rehash() {
            QuadraticProbingHashTable.HashEntry<T>[] oldArray = array;

            // Create a new double-sized, empty table
            allocateArray(2 * oldArray.length);
            occupied = 0;
            theSize = 0;

            // Copy table over
            for (QuadraticProbingHashTable.HashEntry<T> entry : oldArray)
                if (entry != null && entry.isActive)
                    insert(entry.element);
        }

        private int findPos(T x) {
            int offset = 1;
            int currentPos = myhash(x);

            while (array[currentPos] != null && !array[currentPos].element.equals(x)) {
                currentPos += offset;  // Compute ith probe
                offset += 2;
                if (currentPos >= array.length)
                    currentPos -= array.length;
            }

            return currentPos;
        }
        private int findPosForInsert(T x) {
            int offset = 1;
            int currentPos = myhash(x);

            while (array[currentPos] != null && !array[currentPos].element.equals(x)
            && array[currentPos].isActive
            ) {
                currentPos += offset;  // Compute ith probe
                offset += 2;
                if (currentPos >= array.length)
                    currentPos -= array.length;
            }

            return currentPos;
        }

        public boolean remove(T x) {
            int currentPos = findPos(x);
            if (isActive(currentPos)) {
                array[currentPos].isActive = false;
                theSize--;
                return true;
            } else
                return false;
        }

        public int size() {
            return theSize;
        }

        public int capacity() {
            return array.length;
        }

        public boolean contains(T x) {
            int currentPos = findPos(x);
            return isActive(currentPos);
        }

        private boolean isActive(int currentPos) {
            return array[currentPos] != null && array[currentPos].isActive;
        }

        boolean isEmpty() {
            return size() == 0;
        }

        public void makeEmpty() {
            doClear();
        }

        private void doClear() {
            occupied = 0;
            for (int i = 0; i < array.length; i++)
                array[i] = null;
        }

        private int myhash(T x) {
            int hashVal = x.hashCode();

            hashVal %= array.length;
            if (hashVal < 0)
                hashVal += array.length;

            return hashVal;
        }

        private static class HashEntry<T> {
            public T element;   // the element
            public boolean isActive;  // false if marked deleted

            public HashEntry(T e) {
                this(e, true);
            }

            public HashEntry(T e, boolean i) {
                element = e;
                isActive = i;
            }
        }

        private void allocateArray(int arraySize) {
            array = new QuadraticProbingHashTable.HashEntry[nextPrime(arraySize)];
        }
    }
}
