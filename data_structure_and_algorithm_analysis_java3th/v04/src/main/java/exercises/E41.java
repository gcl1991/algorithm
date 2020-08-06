package exercises;

import content.BinarySearchTree.BinaryNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class E41<T> {

    private BinaryNode<T> root;

    E41(BinaryNode<T> root) {
        this.root = root;
    }

    public void levelOrder() {
        levelOrder(root);
    }

    private void levelOrder(BinaryNode<T> node) {
        // 特殊情况
        if (node == null)
            return;
        // 初始化
        List<BinaryNode<T>> tmp = new LinkedList<>();
        tmp.add(root);
        // 迭代遍历
        while (true) {
            List<BinaryNode<T>> newTmp = new LinkedList<>();
            boolean isOver = true;
            for (BinaryNode<T> i : tmp) {
                System.out.printf("%s ",i.element);
                if (i.left != null) {
                    newTmp.add(i.left);
                    isOver = false;
                }
                if (i.right != null) {
                    newTmp.add(i.right);
                    isOver = false;
                }
            }
            System.out.println();
            if (isOver)
                break;
            else
                tmp = newTmp;
        }
    }
}
