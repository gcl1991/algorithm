package content;
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 */
public class BinarySearchTree<T extends Comparable<? super T>> {
    private BinaryNode<T> root;
    private Comparator<? super T> cmp;

    public BinarySearchTree() {
        root = null;
    }

    public  BinarySearchTree(Comparator<? super T> c) {
        root = null;
        cmp = c;
    }

    //Print the tree contents in sorted order.

    /**
     * 增
     */
    // Insert x into tree t
    //duplicates are ignored.
    public void insert(T x) {
        root = insert(x, root);
    }

    private BinaryNode<T> insert(T x, BinaryNode<T> t) {
        if (t == null)
            return new BinaryNode<>(x, null, null);

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            t.left = insert(x, t.left);
        else if (compareResult > 0)
            t.right = insert(x, t.right);
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * 删
     */
    public void makeEmpty() {
        root = null;
    }

    // Remove x from t
    // Nothing is done if x is not found
    public void remove(T x) {
        root = remove(x, root);
    }

    private BinaryNode<T> remove(T x, BinaryNode<T> t) {
        if (t == null)
            return t;   // Item not found; do nothing

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            t.left = remove(x, t.left);
        else if (compareResult > 0)
            t.right = remove(x, t.right);
        else if (t.left != null && t.right != null) // Two children
        {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else
            t = (t.left != null) ? t.left : t.right;
        return t;
    }

    /**
     * 打印
     */
    public void printTree() {
        if (isEmpty())
            System.out.println("Empty tree");
        else
            printTreeBySorted(root);
    }

    private static <T> void printTreeBySorted(BinaryNode<T> t) {
        if (t != null) {
            printTreeBySorted(t.left);
            System.out.println(t.element);
            printTreeBySorted(t.right);
        }
    }

    /**
     * 查询
     */
    public boolean isEmpty() {
        return root == null;
    }

    public T findMin() {
        if (isEmpty())
            throw new NoSuchElementException();
        return findMin(root).element;
    }

    private static <T> BinaryNode<T> findMin(BinaryNode<T> t) {
        if (t == null)
            return null;
        else if (t.left == null)
            return t;
        return findMin(t.left);
    }

    public T findMax() {
        if (isEmpty())
            throw new NoSuchElementException();
        return findMax(root).element;
    }


    private static <T> BinaryNode<T> findMax(BinaryNode<T> t) {
        if (t != null)
            while (t.right != null)
                t = t.right;

        return t;
    }

    public boolean contains(T x) {
        return contains(x, root);
    }

    private boolean contains(T x, BinaryNode<T> t) {
        if (t == null)
            return false;

        int compareResult = myCompare(x,t.element);

        if (compareResult < 0)
            return contains(x, t.left);
        else if (compareResult > 0)
            return contains(x, t.right);
        else
            return true;
    }
    private int myCompare(T x,T y){
        if (cmp!=null){
            return cmp.compare(x,y);
        }else {
            return x.compareTo(y);
        }
    }

    private static <T> int height(BinaryNode<T> t) {
        if (t == null)
            return -1;
        else
            return 1 + Math.max(height(t.left), height(t.right));
    }

    /**
     * 节点内部类
     */
    private static class BinaryNode<T> {
        BinaryNode(T theElement) {
            this(theElement, null, null);
        }

        BinaryNode(T theElement, BinaryNode<T> lt, BinaryNode<T> rt) {
            element = theElement;
            left = lt;
            right = rt;
        }

        T element;
        BinaryNode<T> left;
        BinaryNode<T> right;
    }


}
