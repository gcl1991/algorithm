package content;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SingleLinkedList<T> implements Iterable<T> {
    private int theSize;
    private int modCount;
    private Node<T> beginMarker;
    private Node<T> endMarker;


    private static class Node<T> {
        private T data;
        private Node<T> next;

        private Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }


    public SingleLinkedList() {
        doClear();
    }

    private void doClear() {
        beginMarker = new Node<>(null, null);
        endMarker = new Node<>(null, null);
        beginMarker.next = endMarker;
        modCount++;
        theSize = 0;
    }


    /**增*/
    public boolean add(T x){
        return add(size(),x);
    }

    public int size(){
        return theSize;
    }

    public boolean add(int idx,T x){
        Node<T> prev = getNode(idx-1);
        addAfter(prev,x);
        return true;
    }

    // [lower,upper]
    private Node<T> getNode(int idx){
        Node<T> p;
        if(idx<0 || idx>size()-1)throw new ArrayIndexOutOfBoundsException();
        p = beginMarker;
        for (int i=0;i<=idx-1;i++){
            p = p.next;
        }
        return p;
    }

    public void addAfter(Node<T> p,T x){
        p.next = new Node<T>(x,p.next);
        theSize++;
        modCount++;
    }


    /** 删 */
    public void clear(){
        doClear();
    }

    public T remove(int idx){
        return removeNextNode(getNode(idx-1));
    }

    private T removeNextNode(Node<T> x){
        Node<T> p = x.next;
        x.next = p.next;
        p.next = null;

        theSize--;
        modCount++;
        return p.data;
    }


    /** 改 */
    public T set(int idx,T x){
        Node<T> p = getNode(idx);
        T old = p.data;
        p.data = x;
        return old;
    }


    /** 查 */
    public boolean isEmpty(){
        return size()==0;
    }

    public T get(int idx){
        return getNode(idx).data;
    }

    /** 迭代器 */
    @Override
    public Iterator<T> iterator(){
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T>{
        private Node<T> current = beginMarker.next;
        private Node<T> prevPrev = beginMarker;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current!=endMarker;
        }

        @Override
        public T next() {
            if(modCount!=expectedModCount) throw new ConcurrentModificationException();
            if(!hasNext()) throw new NoSuchElementException();

            T item = current.data;
            if (current!=beginMarker.next){
                prevPrev = prevPrev.next;
            }
            current = current.next;
            okToRemove = true;
            return item;
        }

        @Override
        public void remove() {
            if(modCount!=expectedModCount) throw new ConcurrentModificationException();
            if(!okToRemove) throw new IllegalStateException();

            removeNextNode(prevPrev);
            expectedModCount ++;
            okToRemove = false;
        }
    }


}
