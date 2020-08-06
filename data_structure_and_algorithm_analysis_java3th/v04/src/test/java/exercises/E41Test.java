package exercises;

import content.BinarySearchTree;
import org.junit.Test;

public class E41Test {
    private BinarySearchTree.BinaryNode<Integer> root;

    public E41Test() {
        BinarySearchTree.BinaryNode<Integer> n1 = new BinarySearchTree.BinaryNode<Integer>(1);
        BinarySearchTree.BinaryNode<Integer> n2 = new BinarySearchTree.BinaryNode<Integer>(2);
        BinarySearchTree.BinaryNode<Integer> n3 = new BinarySearchTree.BinaryNode<Integer>(3);
        BinarySearchTree.BinaryNode<Integer> n4 = new BinarySearchTree.BinaryNode<Integer>(4);
        BinarySearchTree.BinaryNode<Integer> n5 = new BinarySearchTree.BinaryNode<Integer>(5);
        BinarySearchTree.BinaryNode<Integer> n6 = new BinarySearchTree.BinaryNode<Integer>(6);
        BinarySearchTree.BinaryNode<Integer> n7 = new BinarySearchTree.BinaryNode<Integer>(7);
        n4.left = n2;
        n4.right = n6;
        n2.left = n1;
        n2.right = n3;
        n6.left = n5;
        n6.right = n7;
        root = n4;
    }

    @Test
    public void testLevelOrder(){
        E41<Integer> tree = new E41<>(root);
        tree.levelOrder();
//        正确结果如下
//        4
//        2 6
//        1 3 5 7
    }
}
