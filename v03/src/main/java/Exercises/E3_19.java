package Exercises;
// 未实现完全
public class E3_19 {
    public static class NoMarkerDoublyLinkedList<T> {
        private Node<T> firstNode;
        private Node<T> lastNode;
        private int modCount;
        private int theSize;

        private static class Node<T> {
            private T data;
            private Node<T> prev;
            private Node<T> next;

            private Node(T data, Node<T> prev, Node<T> next) {
                this.data = data;
                this.prev = prev;
                this.next = next;
            }
        }

        public NoMarkerDoublyLinkedList() {
            doClear();
        }

        private void doClear() {
            firstNode = null;
            lastNode = null;
            theSize = 0;
            modCount++;
        }

        /**
         * 增
         */
        public void add(T x) {
            add(size(), x);
        }
        // 应该拆分，分情况处理，以增加可读性和可维护性，可读性差导致BUG的隐藏和难以定位
        public void add(int idx, T x) {
            Node<T> p = getNode(idx, 0, size());
            addBefore(p, x);
        }

        private Node<T> getNode(int idx, int lower, int upper) {
            Node<T> p;
            if (idx < lower || idx > upper) throw new ArrayIndexOutOfBoundsException();
            if (idx < size() / 2) {
                p = firstNode;
                for (int i = 0; i < idx; i++) {
                    p = p.next;
                }
            } else {
                p = lastNode;
                for (int i = size() - 1; i > idx; i--) {
                    p = p.prev;
                }
            }
            return p;
        }

        private void addBefore(Node<T> p, T x) {
            if (p == firstNode) { // 处理初始情况和在0位增加元素
                if (isEmpty())firstNode = lastNode = new Node<>(x, null, null);
                else {
                    p.next = firstNode;
                    firstNode.prev = p;
                    firstNode = p;
                }
            } else {
                p.prev = p.prev.next = new Node<T>(x, p.prev, p);
            }
            theSize++;
            modCount++;
        }
        /**删*/
        /**改*/
        /**
         * 查
         */
        public int size() {
            return theSize;
        }

        public boolean isEmpty() {
            return theSize == 0;
        }


        public T get(int idx){
            return getNode(idx).data;
        }

        private Node<T> getNode(int idx){
            return getNode(idx,0,size()-1);
        }
    }
}
