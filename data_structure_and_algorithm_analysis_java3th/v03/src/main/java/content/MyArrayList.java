package content;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * 要点
 * MyArrayList本身：增删改查操作和数组容量扩充
 * 迭代器内部类实现
 */
public class MyArrayList<T> implements Iterable<T> {
    private static final int DEFAULT_CAPACITY = 10; // 所有对象共享
    private int modCount;
    private int theSize;
    private T[] theItems;

    /**
     * 初始化操作
     */
    public MyArrayList() {
        doClear();
    }

    private void doClear() {
        theSize = 0;
        theItems = (T[]) new Object[DEFAULT_CAPACITY];
        modCount++;
    }

    private void ensureCapacity(int newCapacity) {
        if (newCapacity < size())
            return;
        T[] newTheItems = (T[]) new Object[newCapacity];// 为什么不出错，因为object内全为null,全Null矩阵可以转换成任意类型
        arrayCopy(theItems, 0, newTheItems, 0, size());
        theItems = newTheItems;
        modCount++;
    }

    private void arrayCopy(T[] src, int srcPos, T[] dest, int destPos, int length) {
        System.arraycopy(src, srcPos, dest, destPos, length);
    }

    /**
     * 增
     */
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
        modCount++;
        return true;
    }

    /**
     * 删
     */
    public void clear() {
        doClear();
    }

    public T remove(int index) {
        T removeItem = theItems[index];
        arrayCopy(theItems, index + 1, theItems, index, size() - index - 1);
        theSize--;
        modCount++;
        return removeItem;
    }

    /**
     * 改
     */
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

    /**
     * 查
     */
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

    /**
     * 迭代器实现
     */
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    // 主要注意index的移动问题
    private class ArrayListIterator implements Iterator<T> {
        private int index = 0;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        public boolean hasNext() {
            return index < size();
        }

        public T next() {
            if(expectedModCount!=modCount) throw new ConcurrentModificationException();
            if (!hasNext()) throw new NoSuchElementException();

            T data = theItems[index++];
            okToRemove = true;
            return data;
        }

        public void remove() {
            if(expectedModCount!=modCount) throw new ConcurrentModificationException();
            if (!okToRemove) throw new IllegalStateException();
            MyArrayList.this.remove(--index);
            okToRemove = false;
        }
    }

    public ListIterator<T> listIterator() {
        return new InnerListIterator();
    }

    private class InnerListIterator implements ListIterator<T> {
        private int index = 0;

        @Override
        public boolean hasPrevious() {
            return index > 0 && index < size();
        }

        @Override
        public T previous() {
            if (hasPrevious())
                return theItems[--index];
            else
                throw new NoSuchElementException();
        }

        @Override
        public void add(T x) {
            MyArrayList.this.add(index, x);
            index++;
        }

        @Override
        public void set(T t) {
            if (hasPrevious())
                theItems[index - 1] = t;
            else
                throw new ArrayIndexOutOfBoundsException();
        }


        @Override
        public boolean hasNext() {
            return index < size();
        }

        @Override
        public T next() {
            if (hasNext())
                return theItems[index++];
            throw new NoSuchElementException();
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }
}
