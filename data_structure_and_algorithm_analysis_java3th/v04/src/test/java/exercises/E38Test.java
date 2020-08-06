package exercises;

import content.BinarySearchTree;
import content.BinarySearchTree.BinaryNode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class E38Test {
    private BinaryNode<Integer> root;

    public E38Test() {
        BinaryNode<Integer> n1 = new BinaryNode<Integer>(1);
        BinaryNode<Integer> n2 = new BinaryNode<Integer>(2);
        BinaryNode<Integer> n3 = new BinaryNode<Integer>(3);
        BinaryNode<Integer> n4 = new BinaryNode<Integer>(4);
        BinaryNode<Integer> n5 = new BinaryNode<Integer>(5);
        BinaryNode<Integer> n6 = new BinaryNode<Integer>(6);
        BinaryNode<Integer> n7 = new BinaryNode<Integer>(7);
        n4.left = n2;
        n4.right = n6;
        n2.left = n1;
        n2.right = n3;
        n6.left = n5;
        n6.right = n7;
        root = n4;
    }

    @Test
    public void testCoordinateXStateful() {
        E38<Integer> tree = new E38<>(root);
        assertEquals(tree.getCoordinateXStateful(1), 1);
        assertEquals(tree.getCoordinateXStateful(2), 2);
        assertEquals(tree.getCoordinateXStateful(3), 3);
        assertEquals(tree.getCoordinateXStateful(4), 4);
        assertEquals(tree.getCoordinateXStateful(5), 5);
        assertEquals(tree.getCoordinateXStateful(6), 6);
        assertEquals(tree.getCoordinateXStateful(7), 7);
    }

    @Test
    public void testCoordinateXNoStatus() {
        E38<Integer> tree = new E38<>(root);
        assertEquals(tree.getCoordinateXNoStatus(1), 1);
        assertEquals(tree.getCoordinateXNoStatus(2), 2);
        assertEquals(tree.getCoordinateXNoStatus(3), 3);
        assertEquals(tree.getCoordinateXNoStatus(4), 4);
        assertEquals(tree.getCoordinateXNoStatus(5), 5);
        assertEquals(tree.getCoordinateXNoStatus(6), 6);
        assertEquals(tree.getCoordinateXNoStatus(7), 7);
    }

    @Test
    public void testCoordinateYNoStatus(){
        E38<Integer> tree = new E38<>(root);
        assertEquals(tree.getCoordinateYNoStatus(4), 0);
        assertEquals(tree.getCoordinateYNoStatus(2), 1);
        assertEquals(tree.getCoordinateYNoStatus(6), 1);
        assertEquals(tree.getCoordinateYNoStatus(1), 2);
        assertEquals(tree.getCoordinateYNoStatus(3), 2);
        assertEquals(tree.getCoordinateYNoStatus(5), 2);
        assertEquals(tree.getCoordinateYNoStatus(7), 2);
    }
}
