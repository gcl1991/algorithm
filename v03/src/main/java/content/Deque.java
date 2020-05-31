package content;

import java.util.NoSuchElementException;

/**
 * 双端队列双链表实现
 */
public class Deque<T> {
    private Node<T> beginMarker;
    private Node<T> endMarker;
    private int theSize;

    public Deque() {
        beginMarker = new Node<>(null, null, null);
        endMarker = new Node<>(null, null, null);
        beginMarker.next = endMarker;
        endMarker.prev = beginMarker;
        theSize = 0;
    }

    // 查询
    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    // 队头操作
    public void push(T newValue) {
        Node<T> p = new Node<>(newValue, beginMarker, beginMarker.next);
        beginMarker.next = beginMarker.next.prev = p;
        theSize++;
    }

    public T pop() {
        if (!isEmpty()) {
            Node<T> firstNode = beginMarker.next;
            T data = firstNode.data;
            beginMarker.next = firstNode.next;
            firstNode.next.prev = beginMarker;
            clearNode(firstNode);
            theSize--;
            return data;
        } else throw new NoSuchElementException();
    }

    // 队尾操作
    public void inject(T x) {
        Node<T> p = new Node<>(x, endMarker.prev, endMarker);
        endMarker.prev = endMarker.prev.next = p;
        theSize++;
    }

    public T eject() {
        if (!isEmpty()) {
            Node<T> lastNode = endMarker.prev;
            T data = lastNode.data;

            lastNode.prev.next = endMarker;
            endMarker.prev = lastNode.prev;
            clearNode(lastNode);
            theSize--;
            return data;

        } else throw new NoSuchElementException();
    }

    private void clearNode(Node<T> p){
        p.data=null;
        p.prev=null;
        p.next=null;
    }


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

}
