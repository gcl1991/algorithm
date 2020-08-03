package content;

import sun.security.x509.AVA;

/**
 * 内部类：节点增加了高度信息
 * 增删查：除平衡查询（递归性检测所有节点左右子树高度差值）外与二叉查找树相同
 * 平衡操作：
 * 平衡时负责更新高度信息
 * t=null时h=-1是为了保证叶节点的高度为0，从而可以计算任意节点的高度
 * 利用高度差判断四种情况，双旋转通过单旋转实现
 */
public class AvlTree<T extends Comparable<? super T>> {
    private AvlNode<T> root;
    private static final int ALLOWED_IMBALANCE = 1;

    public AvlTree() {
        root = null;
    }

    // 增
    public void insert(T x) {
        root = insert(x, root);
    }

    private AvlNode<T> insert(T x, AvlNode<T> t) {
        if (t == null)
            return new AvlNode<>(x, null, null);

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            t.left = insert(x, t.left);
        else if (compareResult > 0)
            t.right = insert(x, t.right);
        else
            ;
        return balance(t);
    }

    //删
    public void remove(T x) {
        root = remove(x, root);
    }

    private AvlNode<T> remove(T x, AvlNode<T> t) {
        if (t == null)
            return t;

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            t.left = remove(x, t.left);
        else if (compareResult > 0)
            t.right = remove(x, t.right);
        else if (t.left != null && t.right != null) {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else
            t = (t.left != null) ? t.left : t.right;
        return balance(t);
    }

    //查
    public void makeEmpty() {
        root = null;
    }

    public boolean checkTreeBalance() {
        return checkTreeBalance(root);
    }

    private boolean checkTreeBalance(AvlNode<T> tree) {
        if (tree == null)
            return true;
        checkTreeBalance(tree.left);
        checkTreeBalance(tree.right);
        if (!isNodeBalance(tree)) {
            return false;
        }
        return true;
    }

    private boolean isNodeBalance(AvlNode<T> node) {
        return Math.abs(height(node.left) - height(node.right)) > ALLOWED_IMBALANCE;
    }

    private int height(AvlNode<T> t) {
        return t == null ? -1 : t.height;
    }

    public T findMin() {
        if (isEmpty())
            throw new UnderflowException();
        return findMin(root).element;
    }

    private AvlNode<T> findMin(AvlNode<T> t) {
        if (t == null)
            return t;

        while (t.left != null)
            t = t.left;
        return t;
    }

    public T findMax() {
        if (isEmpty())
            throw new UnderflowException();
        return findMax(root).element;
    }

    private AvlNode<T> findMax(AvlNode<T> t) {
        if (t == null)
            return t;

        while (t.right != null)
            t = t.right;
        return t;
    }

    public boolean contains(T x) {
        return contains(x, root);
    }

    public boolean isEmpty() {
        return root == null;
    }

    private boolean contains(T x, AvlNode<T> t) {
        while (t != null) {
            int compareResult = x.compareTo(t.element);

            if (compareResult < 0)
                t = t.left;
            else if (compareResult > 0)
                t = t.right;
            else
                return true;    // Match
        }
        return false;   // No match
    }

    public void printTree() {
        if (isEmpty())
            System.out.println("Empty tree");
        else
            printTree(root);
    }

    private void printTree(AvlNode<T> t) {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }

    // 平衡操作
    private AvlNode<T> balance(AvlNode<T> t) {
        if (t == null)
            return t;
        if (isLeftImbalance(t))
            if (isLeftLeft(t))
                t = rotateWithLeftChild(t);
            else
                t = doubleWithLeftChild(t);
        else if (isRightImbalance(t))
            if (isRightRight(t))
                t = rotateWithRightChild(t);
            else
                t = doubleWithRightChild(t);
        updateHeight(t);
        return t;
    }

    private boolean isLeftImbalance(AvlNode<T> node) {
        return isHeightGreaterOneXThanY(node.left, node.right);
    }

    private boolean isRightImbalance(AvlNode<T> node) {
        return isHeightGreaterOneXThanY(node.right, node.left);
    }

    private boolean isHeightGreaterOneXThanY(AvlNode<T> x, AvlNode<T> y) {
        return height(x) - height(y) > ALLOWED_IMBALANCE;
    }

    private boolean isLeftLeft(AvlNode<T> node) {
        return isHeightGreaterOrEqualXThanY(node.left.left, node.left.right);
    }

    private boolean isRightRight(AvlNode<T> node) {
        return isHeightGreaterOrEqualXThanY(node.right.right, node.right.left);
    }

    private boolean isHeightGreaterOrEqualXThanY(AvlNode<T> x, AvlNode<T> y) {
        return height(x) >= height(y);
    }

    private void updateHeight(AvlNode<T> node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;

    }

    private AvlNode<T> rotateWithLeftChild(AvlNode<T> oldRoot) {
        AvlNode<T> newRoot = oldRoot.left;
        // 节点变更
        oldRoot.left = newRoot.right;
        newRoot.right = oldRoot;
        // 高度更新,注意顺序不可调换
        oldRoot.height = Math.max(height(oldRoot.left), height(oldRoot.right)) + 1;
        newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1;
        return newRoot;
    }

    private AvlNode<T> doubleWithLeftChild(AvlNode<T> oldRoot) {
        oldRoot.left = rotateWithRightChild(oldRoot.left);
        return rotateWithLeftChild(oldRoot);
    }

    private AvlNode<T> doubleWithLeftChildFast(AvlNode<T> grandfather) {
        AvlNode<T> son = grandfather.left.right;
        AvlNode<T> father = grandfather.left;
        int height = grandfather.height;
        // 节点变更
        father.right = son.left;
        son.left = father;
        grandfather.left = son.right;
        son.right = grandfather;
        // 高度更新，直接计算给出
        son.height = height-1;
        son.left.height = son.right.height = height-2;
        return son;
    }

    private AvlNode<T> rotateWithRightChild(AvlNode<T> oldRoot) {
        AvlNode<T> newRoot = oldRoot.right;
        // 节点变更
        oldRoot.right = newRoot.left;
        newRoot.left = oldRoot;
        // 高度更新，注意顺序不可调换
        oldRoot.height = Math.max(height(oldRoot.left), height(oldRoot.right)) + 1;
        newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1;
        return newRoot;
    }

    private AvlNode<T> doubleWithRightChild(AvlNode<T> oldRoot) {
        oldRoot.right = rotateWithLeftChild(oldRoot.right);
        return rotateWithRightChild(oldRoot);
    }

    //内部类
    private static class AvlNode<T> {
        private T element;
        private AvlNode<T> left;
        private AvlNode<T> right;
        private int height;

        AvlNode(T theElement) {
            this(theElement, null, null);
        }

        AvlNode(T theElement, AvlNode<T> lt, AvlNode<T> rt) {
            element = theElement;
            left = lt;
            right = rt;
            height = 0;
        }

    }

}
