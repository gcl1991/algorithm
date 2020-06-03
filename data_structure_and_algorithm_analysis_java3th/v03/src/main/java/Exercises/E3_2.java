package Exercises;

import content.DoublyLinkedList;
import content.SingleLinkedList;

public class E3_2<T>{
    public static class SingleLinkedList<T> {
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

        // Node(a)->Node(b) to Node(b)->Node(a)
        public void swapNearNextNode(int idx){
            Node<T> pPrev = getNode(idx-1);
            Node<T> p = pPrev.next;
            Node<T> pNext = p.next;
            Node<T> pNextNext = pNext.next;

            pPrev.next = pNext;
            pNext.next = p;
            p.next = pNextNext;
        }


        /** 增 */
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

        public void addAfter(Node<T> p, T x){
            p.next = new Node<T>(x,p.next);
            theSize++;
            modCount++;
        }

    }

    public static class DoublyLinkedList<T> {
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

        // Node(a)->Node(b) to Node(b)->Node(a)
        public void swapNearNextNode(int idx){
            Node<T> pPrev = getNode(idx-1);
            Node<T> p = pPrev.next;
            Node<T> pNext = p.next;
            Node<T> pNextNext = pNext.next;

            pPrev.next = pNext;
            pNext.prev = pPrev;

            pNext.next = p;
            p.prev = pNext;

            p.next = pNextNext;
            pNextNext.prev = p;
        }

        /** 增 */
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

        private Node<T> getNode(int idx) {
            return getNode(idx, 0, size());
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

    }

}
