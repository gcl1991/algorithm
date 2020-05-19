package content;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class CircularSinglyLinkedList<T> implements Iterable<T> {
    private int modCount;
    private int theSize;
    private Node<T> head;

    /**
     * 构造器
     */
    public CircularSinglyLinkedList() {
        modCount = 0;
        theSize = 0;
        doClear();
    }

    private void doClear() {
        modCount++;
        head = new Node<>(null, null);
        head.next = head;
    }

    /**
     * 增操作
     */
    public boolean add(T x) {
        return add(size(), x);
    }

    public boolean add(int idx, T x) {
        Node<T> pPrev = head;
        if (!isEmpty()){
            pPrev = getNode(idx - 1, -1, size());
        }
        addAfter(pPrev, x);
        return true;
    }

    private Node<T> getNode(int idx) {
        return getNode(idx, 0, size() - 1);
    }

    // [lower,upper]
    private Node<T> getNode(int idx, int lower, int upper) {
        if (idx < lower || idx > upper) throw new ArrayIndexOutOfBoundsException();
        Node<T> p;
        if (idx==-1){
            return head;
        }
        p = head.next;
        for (int i = 0; i < idx; i++) {
            p = p.next;
        }
        return p;
    }

    private void addAfter(Node<T> p, T x) {
        p.next = new Node<>(x, p.next);
        theSize++;
        modCount++;
    }

    /**
     * 删除
     */
    public T remove(int idx) {
        T d = null;
        if (!isEmpty()) {
            Node<T> pPrev = getNode(idx - 1, -1, size()-2);
            d = removeNext(pPrev);
        }
        return d;
    }

    private T removeNext(Node<T> p) {
        Node<T> pNext = p.next;
        p.next = pNext.next;
        pNext.next = null;
        theSize--;
        modCount++;
        return pNext.data;
    }

    /**
     * 查询操作
     */
    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 迭代器
     */
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private int expectedModCount = modCount;
        private boolean okToRemove = false;
        private Node<T> current = head.next;
        private Node<T> prevPrev = head;

        @Override
        public boolean hasNext() {
            return current != head;
        }

        @Override
        public T next() {
            if (expectedModCount != modCount) throw new ConcurrentModificationException();
            if (!hasNext()) throw new NoSuchElementException();
            T d = current.data;
            if (current != head.next) {
                prevPrev = prevPrev.next;
            }
            current = current.next;
            okToRemove = true;
            return d;
        }

        @Override
        public void remove() {
            if (expectedModCount != modCount) throw new ConcurrentModificationException();
            if (!okToRemove) throw new IllegalStateException();

            removeNext(prevPrev);
            expectedModCount++;
            okToRemove=false;
        }
    }

    /**
     * 内部类
     */
    private static class Node<T> {
        private T data;
        private Node<T> next;

        private Node(T d, Node<T> n) {
            data = d;
            next = n;
        }
    }


}
