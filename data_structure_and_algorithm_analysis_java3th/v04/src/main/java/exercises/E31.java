package exercises;

import content.AvlTree;
import content.BinarySearchTree;

/**
 * 统计树中信息往往使用后序遍历，如计算树中节点个数和统计目录容量
 */
public class E31<T extends Comparable<? super T>> extends BinarySearchTree<T> {
    public int getNumAllNode() {
        return getNumAllNode(root);
    }

    private int getNumAllNode(BinaryNode<T> tree) {
        if (tree == null)
            return 0;
        int numLeft = getNumAllNode(tree.left);
        int numRight = getNumAllNode(tree.right);
        return numLeft + numRight + 1;
    }

    public int getNumLeafNode() {
        return getNumLeafNode(root);
    }

    private int getNumLeafNode(BinaryNode<T> tree) {
        // 基准情况
        if (tree == null)
            return 0;
        if (tree.left == null && tree.right == null)
            return 1;
        // 分配策略
        int numLeft = getNumLeafNode(tree.left);
        int numRight = getNumLeafNode(tree.right);
        // 合并策略
        return numLeft + numRight;
    }

    public int getNumFullNode() {
        return getNumFullNode(root);
    }

    private int getNumFullNode(BinaryNode<T> tree) {
        // 基准
        if (tree == null)
            return 0;
        if (tree.left == null && tree.right == null)
            return 0;
        // 分配
        int numLeft = getNumFullNode(tree.left);
        int numRight = getNumFullNode(tree.right);
        // 合并策略（兼收集统计信息）
        if (tree.left != null && tree.right != null)
            return numLeft + numRight + 1;
        else
            return numLeft + numRight;
    }

}
