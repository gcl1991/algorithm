package exercises;

import content.BinarySearchTree;
import content.Fig4_13BinaryNode;

public class E32<T extends Comparable<? super T>> extends BinarySearchTree<T> {
    public void checkNode() {
        checkNode(root);
    }

    /**
     * 分几种情况：
     * null 返回
     * 叶节点 返回
     * 度数=1的节点：左<中或中<右
     * 度数=2的节点：左<中<右
     */
    private void checkNode(BinaryNode<T> node) {
        if (node == null) {
            return;
        } else if (node.left == null && node.right == null) {
            return;
        } else if (node.left != null && node.right == null) {
            assert node.left.element.compareTo(node.element) < 0 : "不合格的左节点";
        } else if (node.left == null && node.right != null) {
            assert node.right.element.compareTo(node.element) > 0 : "不合格的右节点";
        } else if (node.left != null && node.right != null) {
            assert node.element.compareTo(node.left.element)>0 &&
                   node.element.compareTo(node.right.element)<0:"不合格的左右节点";
        }
        checkNode(node.left);
        checkNode(node.right);
    }
}
