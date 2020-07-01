package content;

import com.sun.istack.internal.NotNull;
import content.Fig4_13BinaryNode;

import java.util.*;
import java.util.function.Consumer;

/**
 * 天生排序（无法保留插入顺序）
 * 1 内部节点类，与双链表类似
 * 2 增删查（没有修改，修改相当于先删除再增加，同时Set没有索引，无法修改数据），增删可以使用递归或循环实现(但是即位琐碎臃肿，需要增加父节点引用，
 * 同时左右节点内数据必须不同，否则将出现隐藏的致命bug)，还是应使用递归实现。
 * 3 迭代器实现（中序遍历）：
 * 基于这样一个事实：在先序遍历递归过程中，如果当前节点上一迭代节点为其左子节点，则当前节点未被使用，如果当前节点上一节点为右子节点，则当前节点已被使用。
 * 起点h=0,左->中->右->跳h+1的树(比当前树高度+1的下一棵更大子树)—>最大树(max h)表现为树的增长过程
 * 先中后序遍历表现为树增长过程(h从1到max)
 * (1)如何判断hasNext终点问题：以最右下(最大值节点)节点为标记
 * (2)关于如何获取下一个更大的节点（起点为最左下（最小值节点）,从小到大遍历）
 * 0）引入fromWhichReturn用于标记当前节点是从哪个子节点返回的。
 * 1） 如果该节点是叶节点，则其父节点为下一节点。
 * 2) 如果该节点上一节点是其左子节点，则寻找其该节点右子树中最小节点作为下一节点，如果其无右子树，则沿路径向上寻找其一个左子树祖先，将该祖先的父节点作为下一节点。
 * 3) 如果该节点上一节点是其右子节点，则沿路径向上寻找其一个左子树祖先，将该祖先的父节点作为下一节点。
 * 4) 注意只有1个根节点和只有左子树和只有右子树两种特殊情况的处理
 * 4 如果不保留父节点引用,叶节点无法删除自身,同时使用while代替递归进行add和remove将变得困难，也就不能使用类似双链表的getNode，deleteNode逻辑
 * 同时引入父节点将形成不必要的内存开销，同时导致代码臃肿，容易出错
 * 5 主要是递归和先中后序遍历
 */
class BinarySearchTreeSetParent<T extends Comparable<? super T>> implements Iterable<T> {
    private int size = 0;
    private BinaryNode<T> root = null;

    BinarySearchTreeSetParent() {
        size = 0;
        root = null;
    }

    // 增
    boolean add(T element) {
        boolean isOk = true;
        if (isEmpty()) {
            root = BinaryNode.createLeafNode(element, null);
            size++;
            return isOk;
        }
        BinaryNode<T> parentOrDuplicate = findParentNodeOrDuplicateNode(element, root);
        int result = element.compareTo(parentOrDuplicate.element);
        if (result < 0) {
            parentOrDuplicate.left = BinaryNode.createLeafNode(element, parentOrDuplicate);
            size++;
        } else if (result > 0) {
            parentOrDuplicate.right = BinaryNode.createLeafNode(element, parentOrDuplicate);
            size++;
        } else {
            isOk = false;
            // 节点重复
        }
        return isOk;
    }

    private BinaryNode<T> findParentNodeOrDuplicateNode(T element, BinaryNode<T> tree) {
        while (!tree.isLeafNode()) {
            int result = element.compareTo(tree.element);
            if (result < 0) {
                if (tree.existsLeftChild()) {
                    tree = tree.left;
                } else {
                    break; // tree即为父节点
                }
            } else if (result > 0) {
                if (tree.existsRightChild()) {
                    tree = tree.right;
                } else {
                    break; // tree即为父节点
                }
            } else {
                break; // 节点重复
            }
        }
        return tree;
    }

    //删
    boolean remove(T toBeDeleteElement) {
        Optional<BinaryNode<T>> toBeDeleteNode = findNode(toBeDeleteElement, root);
        Optional<Boolean> isOK = toBeDeleteNode.map(x -> {
                removeNode(x);
            return true;
        });
        return isOK.orElse(false);
    }

    private Optional<BinaryNode<T>> findNode(T toBeFindElement, BinaryNode<T> tree) {
        if (tree == null) {
            return Optional.empty();
        } else {
            for (int result = toBeFindElement.compareTo(tree.element); result != 0; result = toBeFindElement.compareTo(tree.element)) {
                if (result < 0) {
                    if (tree.existsLeftChild()) {
                        tree = tree.left;
                    } else {
                        break;
                    }
                } else {
                    if (tree.existsRightChild()) {
                        tree = tree.right;
                    } else {
                        break;
                    }
                }
            }
            if (toBeFindElement.compareTo(tree.element) == 0) {
                return Optional.of(tree);
            }
        }
        return Optional.empty();
    }

    private void removeNode(BinaryNode<T> node) {
        if (node.isLeafNode()) {
            removeLeafNodeSelf(node);
        } else if (node.existsLeftChild() && node.existsRightChild()) {
            node.element = removeMinNode(node);
        } else if (node.existsLeftChild()) {
            node.element = node.left.element;
            node.left = null;
            size--;
        } else {
            node.element = node.right.element;
            node.right = null;
            size--;
        }
    }

    private T removeMinNode(@NotNull BinaryNode<T> tree) {
        T element = null;
        if (tree.isLeafNode()) {
            removeLeafNodeSelf(tree);
            element = tree.element;
        } else {
            BinaryNode<T> fatherNode = tree;
            while (tree.existsLeftChild()) {
                fatherNode = tree;
                tree = tree.left;
            }
            element = tree.element;
            fatherNode.left = null;
            size--;
        }
        return element;
    }

    // 要求左右节点数据一定不相同
    private void removeLeafNodeSelf(BinaryNode<T> node) {
        SubNodeType nodeType = getNodeType(node);
        if (nodeType == SubNodeType.LEFT) {
            node.father.left = null;
        } else if (nodeType == SubNodeType.RIGHT) {
            node.father.right = null;
        } else {
            root = null;
        }
        size--;
    }

    private SubNodeType getNodeType(@NotNull BinaryNode<T> node) {
        SubNodeType nodeType = null;
        if (node.father == null) {
            nodeType = SubNodeType.ROOT;
        } else if (node.father.existsLeftChild() && node.element.compareTo(node.father.left.element) == 0) {
            nodeType = SubNodeType.LEFT;
        } else {
            nodeType = SubNodeType.RIGHT;
        }
        return nodeType;
    }

    // 查询
    int size() {
        return size;
    }

    boolean isEmpty() {
        return size() == 0;
    }

    T findMin() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return findMinNode(root).element;
    }

    private static <T> BinaryNode<T> findMinNode(@NotNull BinaryNode<T> tree) {
        while (tree.existsLeftChild()) {
            tree = tree.left;
        }
        return tree;
    }

    T findMax() {
        if (isEmpty()) throw new NoSuchElementException();
        return findMaxNode(root).element;
    }
    // 一直向右走
    private static <T> BinaryNode<T> findMaxNode(@NotNull BinaryNode<T> tree) {
        if (tree.existsRightChild()) {
            return findMaxNode(tree.right);
        } else {
            return tree;
        }
    }

    boolean contains(@NotNull T element) {
        if (isEmpty()) {
            return false;
        }
        return contains(element, root);
    }

    // (1)比较确定方向 (2)向左或右递归
    private static <T extends Comparable<? super T>> boolean contains(@NotNull T element, @NotNull BinaryNode<T> tree) {
        int result = tree.element.compareTo(element);
        if (result == 0) {
            return true;
        } else if (result < 0) {
            if (tree.existsLeftChild())
                return contains(element, tree.left);
            else
                return false;
        } else {
            if (tree.existsRightChild())
                return contains(element, tree.right);
            else
                return false;
        }
    }

    public void print() {
        if (isEmpty()) throw new NoSuchElementException();
        print(root);
    }

    // 无返回值的递归
    private static <T> void print(BinaryNode<T> tree) {
        if (tree.existsLeftChild()) {
            print(tree.left);
        }
        System.out.println(tree.element);
        if (tree.existsRightChild()) {
            print(tree.right);
        }
    }

    // 迭代器

    @Override
    public Iterator<T> iterator() {
        return new TreeIterator();
    }

    enum SubNodeType {
        ROOT, LEFT, RIGHT
    }

    private class TreeIterator implements Iterator<T> {
        BinaryNode<T> currentNode;
        BinaryNode<T> prevNode;
        BinaryNode<T> endNode;
        private boolean isOkRemove;
        private boolean isHasNext;
        private SubNodeType fromWhichReturn;

        private TreeIterator() {
            if (root == null) {
                throw new NoSuchElementException();
            }
            currentNode = findMinNode(root);
            prevNode = null;
            endNode = findMaxNode(root); // 当只有一个元素时，为根节点
            isHasNext = true;
            isOkRemove = false;
        }

        @Override
        public boolean hasNext() {
            return isHasNext;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T data = currentNode.element;
            if (currentNode == endNode) {
                isHasNext = false;
            }
            prevNode = currentNode;
            currentNode = getNextMaxNode(currentNode);
            isOkRemove = true;
            return data;
        }

        @Override
        public void remove() {
            if (!isOkRemove) throw new IllegalStateException();
            removeNode(prevNode);
            isOkRemove = false;
        }

        private BinaryNode<T> getNextMaxNode(BinaryNode<T> node) {
            BinaryNode<T> nextNode = null;
            if (node.isLeafNode()) { // 起点（左）
                nextNode = node.father;
                fromWhichReturn = getNodeType(node); // 可能为根节点（当只有一个元素时）
            } else if (fromWhichReturn == SubNodeType.LEFT && node.existsRightChild() || fromWhichReturn==null) {// 中间（根）
                nextNode = findMinNode(node.right);
                fromWhichReturn = null;
            } else {// 终点（右）——>跳转到h+1的父树
                while (getNodeType(node) == SubNodeType.RIGHT) {
                    node = node.father;
                }
                nextNode = node.father;
                fromWhichReturn = SubNodeType.LEFT;
            }
            return nextNode;
        }


    }

    // 内部节点类
    private static class BinaryNode<T> {
        private T element = null;
        private BinaryNode<T> left = null;
        private BinaryNode<T> right = null;
        private BinaryNode<T> father = null;

        private static <T> BinaryNode<T> createLeafNode(T e, BinaryNode<T> father) {
            return new BinaryNode<>(e, null, null, father);
        }

        private BinaryNode(T element, BinaryNode<T> left, BinaryNode<T> right, BinaryNode<T> father) {
            this.element = element;
            this.left = left;
            this.right = right;
            this.father = father;
        }

        private boolean isLeafNode() {
            return left == null && right == null;
        }

        private boolean existsLeftChild() {
            return left != null;
        }

        private boolean existsRightChild() {
            return right != null;
        }
    }
}
