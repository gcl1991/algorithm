package content;
/**
 * 内部类：与二叉树相同
 * 增删查：任何查询操作均将被查询节点上游至根节点，
 * 删除节点：先将待删除节点上游至根，再将左子树最大值节点上游至根从而与右子树结合成新树（或从右子树中上游最小节点至根）
 * 所有的查询和删除操作在树为空的情况下均报错（空树root=nullNode）
 * 伸展操作：可接受空树（不过输入空树也没意义,当节点不存在时，副作用是将其理论上的父节点上游至根）。
 * 插入操作：
 * （1）上游x理论上的父节点至根，插入新节点作为新根
 * （2）将父节点上游至根再将子节点插入与直接将子节点上游只根是不同的，但是由于路径的相同，形成的树是有一定相似性的。
 * （3）保证树永远是满二叉树，所有的叶节点均指向nullNode，所有的插入均发生再非叶节点
 * */
public class SplayTree<T extends Comparable<? super T>> {
    private BinaryNode<T> newNode = null;  // Used between different inserts
    private BinaryNode<T> header = new BinaryNode<T>(null); // For splay
    private BinaryNode<T> root;
    private BinaryNode<T> nullNode;


    public SplayTree() {
        nullNode = new BinaryNode<T>(null);
        nullNode.left = nullNode.right = nullNode;
        root = nullNode;
    }

    // 增
    public void insert(T x) {
        if (newNode == null)
            newNode = new BinaryNode<T>(null);
        newNode.element = x;
        if (root == nullNode) {
            newNode.left = newNode.right = nullNode;
            root = newNode; // 只具有一个元素时，左右子树均指向nullNode
        } else {
            root = splay(x, root); // 上游x理论上的父节点至根
            int compareResult = x.compareTo(root.element);
            if (compareResult < 0) {
                newNode.left = root.left;
                newNode.right = root;
                root.left = nullNode;
                // 保证树永远是满二叉树，所有的叶节点均指向nullNode，所有的插入均发生再非叶节点
                root = newNode;
            } else if (compareResult > 0) {
                newNode.right = root.right;
                newNode.left = root;
                root.right = nullNode;
                root = newNode;
            } else
                return;
        }
        newNode = null;
    }

    // 删
    public void remove(T x) {
        if (!contains(x)) // ！！！副作用，将x上游至根节点
            return;

        BinaryNode<T> newTree;

        if (root.left == nullNode)
            newTree = root.right;
        else {
            newTree = root.left;
            newTree = splay(x, newTree);  // 从左子树中找出最大值点作为新根
            newTree.right = root.right;
        }
        root = newTree;
    }

    public void makeEmpty() {
        root = nullNode;
    }

    // 查
    public T findMin() throws UnderflowException {
        if (isEmpty())
            throw new UnderflowException();

        BinaryNode<T> ptr = root;

        while (ptr.left != nullNode)
            ptr = ptr.left;

        root = splay(ptr.element, root);
        return ptr.element;
    }

    public T findMax() {
        if (isEmpty())
            throw new UnderflowException();

        BinaryNode<T> ptr = root;

        while (ptr.right != nullNode)
            ptr = ptr.right;

        root = splay(ptr.element, root);
        return ptr.element;
    }

    public boolean contains(T x) {
        if (isEmpty())
            return false;

        root = splay(x, root);

        return root.element.compareTo(x) == 0;
    }

    public boolean isEmpty() {
        return root == nullNode;
    }

    // 伸展操作
    private BinaryNode<T> splay(T x, BinaryNode<T> t) {
        BinaryNode<T> leftTreeMax, rightTreeMin;

        header.left = header.right = nullNode;
        leftTreeMax = rightTreeMin = header;
        // leftTreeMax只操作其右指針，rightTreeMin只操作其左指針

        nullNode.element = x;   // Guarantee a match保证一个匹配,

        while (true) {
            int compareResult = x.compareTo(t.element);

            if (compareResult < 0) {
                if (x.compareTo(t.left.element) < 0)
                    t = rotateWithLeftChild(t);
                if (t.left == nullNode)
                    break;
                // Link Right
                rightTreeMin.left = t;
                rightTreeMin = t;
                t = t.left;
            } else if (compareResult > 0) {
                if (x.compareTo(t.right.element) > 0)
                    t = rotateWithRightChild(t);
                if (t.right == nullNode)
                    break;
                // Link Left
                leftTreeMax.right = t;
                leftTreeMax = t;
                t = t.right;
            } else
                break;
        }

        leftTreeMax.right = t.left;
        rightTreeMin.left = t.right;
        t.left = header.right;
        t.right = header.left;
        return t;
    }


    private static <T> BinaryNode<T> rotateWithLeftChild(BinaryNode<T> k2) {
        BinaryNode<T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        return k1;
    }


    private static <T> BinaryNode<T> rotateWithRightChild(BinaryNode<T> k1) {
        BinaryNode<T> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        return k2;
    }

    // 内部类
    private static class BinaryNode<T> {
        T element;
        BinaryNode<T> left;
        BinaryNode<T> right;

        BinaryNode(T theElement) {
            this(theElement, null, null);
        }

        BinaryNode(T theElement, BinaryNode<T> lt, BinaryNode<T> rt) {
            element = theElement;
            left = lt;
            right = rt;
        }

    }


}

