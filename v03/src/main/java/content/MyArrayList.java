package content;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 要点
 * MyArrayList本身：增删改查操作和数组容量扩充
 * 迭代器内部类实现
 */
public class MyArrayList<T> implements Iterable<T> {
    private static final int DEFAULT_CAPACITY = 10; // 所有对象共享
    private int theSize;
    private T[] theItems;

    /** 初始化操作*/
    public MyArrayList() {
        doClear();
    }

    private void doClear() {
        theSize = 0;
        theItems = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private void ensureCapacity(int newCapacity) {
        if (newCapacity < size())
            return;
        T[] newTheItems = (T[]) new Object[newCapacity];// 为什么不出错，猜测因为object内全为null
        arrayCopy(theItems, 0, newTheItems, 0, size());
        theItems = newTheItems;
    }

    private void arrayCopy(T[] src, int srcPos, T[] dest, int destPos, int length) {
        System.arraycopy(src, srcPos, dest, destPos, length);
    }

    /** 增 */
    public boolean add(T x) {
        add(size(), x);
        return true;
    }

    public boolean add(int index, T x) {
        int oldSize = size();
        if (size() == theItems.length) {
            ensureCapacity(2 * size() + 1);
        }
        arrayCopy(theItems, index, theItems, index + 1, oldSize - index);
        theItems[index] = x;
        theSize++;
        return true;
    }

    /**删*/
    public void clear() {
        doClear();
    }

    public T remove(int index) {
        T removeItem = theItems[index];
        arrayCopy(theItems, index + 1, theItems, index, size() - index - 1);
        theSize--;
        return removeItem;
    }

    /**改*/
    public void trimToSize() {
        ensureCapacity(size());
    }

    public T set(int index, T newValue) {
        checkRange(index);
        T old = theItems[index];
        theItems[index] = newValue;
        return old;
    }

    private void checkRange(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**查*/
    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T get(int index) {
        checkRange(index);
        return theItems[index];
    }

    /** 迭代器实现 */
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    // 主要注意index的移动问题
    private class ArrayListIterator implements Iterator<T> {
        private int index = 0;

        public boolean hasNext() {
            return index < size();
        }

        public T next() {
            if (hasNext())
                return theItems[index++];
            else
                throw new NoSuchElementException();
        }

        public void remove() {
            MyArrayList.this.remove(--index);
        }
    }
}
