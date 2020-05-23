package Exercises;

public class E3_11 {
    public static void main(String[] args) {

    }

    public static class SingleLinkedList<T> {
        private Node<T> head;

        private static class Node<T> {
            private T data;
            private Node<T> next;

            private Node(T d, Node<T> p) {
                data = d;
                next = p;
            }
        }

        /**
         * 构造器
         */
        public SingleLinkedList() {
            doClear();
        }

        private void doClear() {
            head = new Node<>(null, null);
        }

        /**
         * 增
         */
        public void add(T x) {
            Node<T> prev = getNode(size() - 1);
            addAfter(prev, x);
        }

        public boolean addIfNotExist(T x) {
            boolean isAdd = false;
            if (!contain(x)) {
                add(x);
                isAdd = true;
            }
            return isAdd;
        }

        private Node<T> getNode(int idx) {
            if (idx < -1 || idx > size() - 1) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int theIndex = -2;
            Node<T> p;
            for (p = head; p != null; p = p.next) {
                theIndex++;
                if (theIndex == idx) {
                    break;
                }
            }
            return p;
        }

        private void addAfter(Node<T> p, T x) {
            p.next = new Node<>(x, p.next);
        }

        /** 删除 */
        public boolean removeIfExist(T x){
            for (Node<T> p = head; p != null; p = p.next) {
                if(p.next!=null && p.next.data == x){
                    removeNext(p);
                    return true;
                }
            }
            return false;
        }

        private void removeNext(Node<T> p){
            Node<T> pNext = p.next;
            p.next = pNext.next;
            pNext.next = null;
        }

        /**
         * 查
         */
        public int size() {
            int theSize = 0;
            for (Node<T> p = head.next; p != null; p = p.next) {
                theSize++;
            }
            return theSize;
        }

        public void printAll() {
            System.out.print("[");
            for (Node<T> p = head.next; p != null; p = p.next) {
                if (p.next == null) {
                    System.out.print(p.data);
                    ;
                } else
                    System.out.print(p.data + ",");
            }
            System.out.println("]");
        }

        public boolean contain(T x) {
            boolean isExist = false;
            for (Node<T> p = head.next; p != null; p = p.next) {
                if (x == p.data) {
                    isExist = true;
                }
            }
            return isExist;
        }
    }


}
