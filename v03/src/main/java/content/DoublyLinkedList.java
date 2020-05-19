package content;

import javax.swing.text.html.HTMLDocument;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * 主要结构
 * MyLinkedList自身增删改查及迭代器实现
 * 内部Node类
 * 注意要点：
 * 删除操作，增加操作和遍历操作指针的移动
 * 变动操作modCount的改变，迭代器remove和next的迭代器合法性检测。
 */
public class DoublyLinkedList<T> implements Iterable<T> {
    private int theSize;
    private int modCount;
    private Node<T> beginMarker;
    private Node<T> endMarker;

    /**
     * 节点类，外围类与嵌套类可以互相访问private属性
     */
    private static class Node<T> {
        private T data;
        private Node<T> prev;
        private Node<T> next;

        private Node(T d, Node<T> p, Node<T> n) {
            data = d;
            prev = p;
            next = n;
        }
    }


    public DoublyLinkedList() {
        doClear();
    }

    private void doClear() {
        beginMarker = new Node<>(null, null, null);
        endMarker = new Node<>(null, beginMarker, null);
        beginMarker.next = endMarker;
        modCount++;
        theSize = 0;
    }

    /**
     * 增
     */
    public boolean add(T x) {
        return add(size(), x);
    }

    public int size() {
        return theSize;
    }

    public boolean add(int idx, T x) {
        Node<T> p = getNode(idx, 0, size());
        addBefore(p, x);
        return true;
    }

    // 两端为闭区间
    private Node<T> getNode(int idx, int lower, int upper) {
        Node<T> p;
        if (idx < lower || idx > upper) {
            throw new ArrayIndexOutOfBoundsException();
        }

        if (idx < size() / 2) {
            p = beginMarker.next;
            for (int i = 0; i < idx; i++) {
                p = p.next;
            }
        } else {
            p = endMarker.prev;
            for (int i = size() - 1; i > idx; i--) {
                p = p.prev;
            }
        }
        return p;
    }

    private void addBefore(Node<T> p, T x) {
        p.prev = p.prev.next = new Node<T>(x, p.prev, p);
        theSize++;
        modCount++;
    }


    /**
     * 删
     */
    public void clear() {
        doClear();
    }

    public T remove(int idx) {
        return remove(getNode(idx));
    }

    private Node<T> getNode(int idx) {
        return getNode(idx, 0, size());
    }

    private T remove(Node<T> p) {
        p.prev.next = p.next;
        p.next.prev = p.prev;
        p.next = null;
        p.prev = null;

        theSize--;
        modCount++;
        return p.data;
    }

    /**
     * 改
     */
    public T set(int idx, T x) {
        Node<T> p = getNode(idx);
        T old = p.data;
        p.data = x;
        return old;
    }


    /**
     * 查
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    public T get(int idx) {
        return getNode(idx).data;
    }

    public boolean contains(T x) {
        boolean isContains = false;
        for (Iterator<T> it = iterator(); it.hasNext() && !isContains; ) {
            T item = it.next();
            isContains = Objects.equals(item, x);
        }
        return isContains;
    }

    /**
     * 迭代器
     */
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public T next() {
            if (modCount != expectedModCount) throw new ConcurrentModificationException();
            if (!hasNext()) throw new NoSuchElementException();

            T item = current.data;
            current = current.next;
            okToRemove = true;
            return item;
        }

        @Override
        public void remove() {
            if (modCount != expectedModCount) throw new ConcurrentModificationException();
            if (!okToRemove) throw new IllegalStateException();

            DoublyLinkedList.this.remove(current.prev);
            expectedModCount++; // 因为size与modCount都被改动
            okToRemove = false;
        }
    }

}