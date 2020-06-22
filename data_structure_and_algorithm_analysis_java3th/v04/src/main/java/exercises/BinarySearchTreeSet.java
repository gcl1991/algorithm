package exercises;

import com.sun.istack.internal.NotNull;

import java.util.Optional;

/**
 * 1 内部节点类，与双链表类似
 * 2 增删查（没有修改，修改相当于先删除再增加，同时Set没有索引，无法修改数据），增删可以使用递归或循环实现(但是即位琐碎臃肿，需要增加父节点引用，
 * 同时左右节点内数据必须不同，否则将出现隐藏的致命bug)，还是应使用递归实现。
 * 3 迭代器
 * 4 如果不保留父节点引用,叶节点无法删除自身,同时使用while代替递归进行add和remove将变得困难，也就不能使用类似双链表的getNode，deleteNode逻辑
 * 同时引入父节点将形成不必要的内存开销，同时导致代码臃肿，容易出错
 */
class BinarySearchTreeSet<T extends Comparable<? super T>> {
    private int size = 0;
    private BinaryNode<T> root = null;

    BinarySearchTreeSet() {
        size = 0;
        root = null;
    }

    int size() {
        return size;
    }

    boolean isEmpty() {
        return size() == 0;
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
            if (x.isLeafNode()) {
                removeLeafNodeSelf(x);
            } else {
                removeNode(x);
            }
            return true;
        });
        return isOK.orElse(false);
    }

    private Optional<BinaryNode<T>> findNode(T toBeFindElement, BinaryNode<T> tree) {
        if (tree == null) {
            return Optional.empty();
        }else {
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
        if (node.father != null && node.father.left != null && node.element.compareTo(node.father.left.element) == 0) {
            node.father.left = null;
        } else if (node.father != null) {
            node.father.right = null;
        } else {
            root = null; // 删除根节点
        }
        size--;
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

        private boolean existsAllChild() {
            return left != null && right != null;
        }

        private boolean existsOneChild() {
            return left != right;
        }
    }
}
