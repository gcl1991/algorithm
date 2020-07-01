package content;

import com.sun.istack.internal.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * 1增删查
 * 1）（头节点（最小节点）/尾节点（最大节点））标记节点更新问题
 * 增：对比数值大小移动标记节点左右指针即可。
 * 删：如果删除的时标记节点，则移动标记节点nextMax/naxtMin引用即可。
 * 2）增删中路径上节点nextMin和nextMin指针更新问题：
 * 前提：将查找树看成利用点将值域进行递归的二分法划分，则对于某节点和其左右节点有：___al___a___ar___
 * 则可得出对于a节点的左子节点al，其左子树小于al小于a,其右子树小于a大于al
 * 对于a节点的右子树节点ar，其左子树小于al大于a，其右子树大于al大于a
 * 分两种情况讨论：
 * 新增节点为左节点：更新父节点nextMin（指向新增节点）和新增节点的nexMin（保存父节点的nextMin）/nexMax（指向父节点）
 * 新增节点为右节点：更新父节点nextMax(指向新增节点)和新增节点的nextMax（保存父节点的nextMax）/nextMin（指向父节点）
 * 增加状态标记isUpdateNewNodeReference，用于标记递归过程节点nextMax/nextMin引用是否更新
 * 2 此方法比保留父节点的方法编程更简单，更不容易出错，但是该方法查询父节点稍微繁琐。
 * 3 迭代器
 * 迭代器hasNext和next较为清晰，使用nextMin链和nextMax链即可。
 * 1) remove方法思路如下（需要找到该节点的父节点才能对该节点删除）
 // 假设要删除A节点，其父节点为B
 // 首先判断A节点是其B的左子树还是右子树
 // 如果A是左子树，则A右子树中最大节点的nextMax为B节点
 // 如果A是右子树，则A左子树中最小节点的nextMin为B节点
 */
class BinarySearchTreeSetMinMax<T extends Comparable<T>> implements Iterable<T> {
    private int size;
    private BinaryNode<T> root;
    private BinaryNode<T> begin;
    private BinaryNode<T> end;
    private boolean isUpdateNewNodeReference;

    BinarySearchTreeSetMinMax() {
        size = 0;
        root = null;
        begin = null;
        end = null;
        isUpdateNewNodeReference=false;
    }

    // 增
    boolean add(T element) {
        isUpdateNewNodeReference = false; // 每次插入元素都要清空以下
        root = addNode(element, root);
        if (root == null)
            return false;
        size++;
        updateMin(element);
        updateMax(element);
        return true;
    }

    private void updateMin(T element) {
        if (begin == null) {
            begin = root;
        } else if (element.compareTo(begin.element) < 0) {
            begin = begin.left;
        } else {
            // 不变
        }
    }

    private void updateMax(T element) {
        if (end == null) {
            end = root;
        } else if (element.compareTo(end.element) > 0) {
            end = end.right;
        } else {
            // 不变
        }
    }

    private BinaryNode<T> addNode(T element, BinaryNode<T> tree) {
        if (tree == null)
            return new BinaryNode<T>(element);
        int result = element.compareTo(tree.element);
        if (result < 0) {
            BinaryNode<T> newNode = addNode(element, tree.left);
            tree.left = newNode;
            if (!isUpdateNewNodeReference)
                updateMinMaxReference(newNode, tree, NodeType.LEFT);
        } else if (result > 0) {
            BinaryNode<T> newNode = addNode(element, tree.right);
            tree.right = newNode;
            if (!isUpdateNewNodeReference)
                updateMinMaxReference(newNode, tree, NodeType.RIGHT);
        } else {
            return null; // 节点重复
        }
        return tree;
    }

    private void updateMinMaxReference(BinaryNode<T> sub, BinaryNode<T> parent, NodeType direction) {
        if (direction == NodeType.LEFT) {
            sub.nextMin = parent.nextMin;
            sub.nextMax = parent;
            parent.nextMin = sub;
            isUpdateNewNodeReference = true;
        } else if (direction == NodeType.RIGHT) {
            sub.nextMax = parent.nextMax;
            sub.nextMin = parent;
            parent.nextMax = sub;
            isUpdateNewNodeReference = true;
        } else {
            throw new IllegalArgumentException();
        }
    }

    // 删 todo 元素不存在时无法返回false
    void remove(T element) {
        root = remove(element, root);
    }

    private BinaryNode<T> remove(@NotNull T element, @NotNull BinaryNode<T> tree) {
        if (tree == null)
            return tree;
        int result = element.compareTo(tree.element);
        if (result < 0) {
            tree.left = remove(element, tree.left); // 由于子节点没有父节点引用，利用返回的方式修改树
        } else if (result > 0) {
            tree.right = remove(element, tree.right);
        } else {
            tree = removeNode(tree);
        }
        return tree;
    }

    private BinaryNode<T> removeNode(BinaryNode<T> node) {
        if (node.left != null && node.right != null) {
            node = removeFromRight(node);
        } else {
            node = node.left != null ? node.left : node.right;
        }
        return node;
    }

    private BinaryNode<T> removeFromRight(BinaryNode<T> node) {
        BinaryNode<T> maxNode = node;
        BinaryNode<T> parent = node;
        while (maxNode.right != null) {
            parent = maxNode;
            maxNode = maxNode.right;
        }
        T data = maxNode.element;
        parent.right = null;
        node.element = data;
        return node;
    }

    //查
    int size() {
        return size;
    }

    boolean isEmpty() {
        return size() == 0;
    }

    T getMin() {
        if (isEmpty()) throw new NoSuchElementException();
        return begin.element;
    }

    T getMax() {
        if (isEmpty()) throw new NoSuchElementException();
        return end.element;
    }

    boolean contains(T element) {
        return contains(element, root);
    }

    boolean contains(T element, BinaryNode<T> tree) {
        if (tree == null) {
            return false;
        }

        int result = element.compareTo(root.element);
        if (result < 0) {
            return contains(element, tree.left);
        } else if (result > 0) {
            return contains(element, tree.right);
        } else {
            return true;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new TreeIterator();
    }

    private class TreeIterator implements Iterator<T> {
        private BinaryNode<T> currentNode;
        private BinaryNode<T> prevNode;
        private boolean isHasNext;
        private boolean isOkRemove;

        private TreeIterator(){
            if(isEmpty()) throw new IllegalStateException();
            currentNode = begin;
            prevNode = null;
            isHasNext = true;
            isOkRemove = false;
        }

        @Override
        public boolean hasNext() {
            return isHasNext;
        }

        @Override
        public T next() {
            if(!hasNext()) throw new IllegalStateException();
            T data = currentNode.element;
            if (currentNode == end){
                isHasNext = false;
            }
            prevNode = currentNode;
            currentNode = currentNode.nextMax;
            isOkRemove = true;
            return data;
        }

        @Override
        public void remove() {
            if(!isOkRemove) throw new IllegalStateException();
            // 假设要删除A节点，其父节点为B
            // 首先判断A节点是其B的左子树还是右子树
            // 如果A是左子树，则A右子树中最大节点的nextMax为B节点
            // 如果A是右子树，则A左子树中最小节点的nextMin为B节点
            isOkRemove=false;
        }
    }

    private enum NodeType {
        LEFT, RIGHT
    }

    // 内部类
    private static class BinaryNode<T> {
        private T element;
        private BinaryNode<T> left;
        private BinaryNode<T> right;
        private BinaryNode<T> nextMax;
        private BinaryNode<T> nextMin;

        private BinaryNode(T element) {
            this.element = element;
            this.left = null;
            this.right = null;
            this.nextMin = null;
            this.nextMax = null;
        }
    }
}
