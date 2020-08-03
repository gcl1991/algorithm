package exercises;

import content.BinarySearchTree;

public class E33<T extends Comparable<? super T>> extends E31<T> {
    public void removeAllLeafNode() {
        root = removeAllLeafNode(root);
    }


    /**
     * 分几种情况：
     * 假设函数签名为 Node remove(Node)
     * null 返回null
     * 叶节点 返回null
     * 非叶节点
     *      度数=1
     *          左节点为叶节点
     *              右节点为null，置node.left==null，返回node
     *          右节点为叶节点
     *              左节点为null，置node.right==null，返回node
     *      度数=2
     *          其左右节点存在且均为叶节点 node.left=node.right=null，返回node
     *          左节点为叶节点，右节点非空，node.left=null，（递归）置node.right=remove(node.right)，返回node
     *          右节点为叶节点，左节点非空，node.right=null，（递归）置node.left=remove(node.left)，返回node
     *          左右节点存在且均非叶节点 node.left=remove(node)，node.right=remove(node)，返回node
     * 对于if/elif/else语句，特例在前，泛例在后（if与elif与else存在耦合关系/否则所有特例将被范例拦截/小网在前，大网在后）
     */
    private BinaryNode<T> removeAllLeafNode(BinaryNode<T> tree) {
        if (tree == null) {
            return null;
        } else if (isLeafNode(tree)) {
            return null;
        } else if (isLeafNode(tree.left) && isLeafNode(tree.right)) {
            tree.left = null;
            tree.right = null;
            return tree;
        } else if (isLeafNode(tree.left)) {
            tree.left = null;
            if (tree.right != null)
                tree.right = removeAllLeafNode(tree.right);
            return tree;
        } else if (isLeafNode(tree.right)) {
            tree.right = null;
            if (tree.left != null)
                tree.left = removeAllLeafNode(tree.left);
            return tree;
        }
        tree.left = removeAllLeafNode(tree.left);
        tree.right = removeAllLeafNode(tree.right);
        return tree;
    }

    private boolean isLeafNode(BinaryNode<T> node) {
        return node != null && node.left == null && node.right == null;
    }
}
