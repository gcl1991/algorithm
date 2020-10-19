package exercises;

import static content.BinarySearchTree.BinaryNode;

import Helpers.TreeHelpers;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

public class E47Test {
    public BinaryNode<String> tree1;
    public BinaryNode<String> tree2;

    @Before
    public void build() {
        BinaryNode<String> a1 = new BinaryNode<>("A", null, null);
        BinaryNode<String> b1 = new BinaryNode<>("B", null, null);
        BinaryNode<String> c1 = new BinaryNode<>("C", null, null);
        BinaryNode<String> d1 = new BinaryNode<>("D", null, null);
        BinaryNode<String> e1 = new BinaryNode<>("E", null, null);
        BinaryNode<String> f1 = new BinaryNode<>("F", null, null);
        BinaryNode<String> g1 = new BinaryNode<>("G", null, null);
        BinaryNode<String> h1 = new BinaryNode<>("H", null, null);
        a1.left = b1;
        b1.left = d1;
        b1.right = e1;
        e1.left = f1;
        a1.right = c1;
        c1.left = g1;
        g1.left = h1;
        tree1 = a1;

        BinaryNode<String> a2 = new BinaryNode<>("A", null, null);
        BinaryNode<String> b2 = new BinaryNode<>("B", null, null);
        BinaryNode<String> c2 = new BinaryNode<>("C", null, null);
        BinaryNode<String> d2 = new BinaryNode<>("D", null, null);
        BinaryNode<String> e2 = new BinaryNode<>("E", null, null);
        BinaryNode<String> f2 = new BinaryNode<>("F", null, null);
        BinaryNode<String> g2 = new BinaryNode<>("G", null, null);
        BinaryNode<String> h2 = new BinaryNode<>("H", null, null);

        a2.left = c2;
        c2.left = g2;
        g2.right = h2;
        a2.right = b2;
        b2.left = e2;
        e2.left = f2;
        b2.right = d2;
        tree2 = a2;
    }

    @Test
    public void testisIsomorphismTree() {
        // 正常情况
        assertTrue(E47.isIsomorphismTree(tree1, tree2));
        // 边界情况
        assertFalse(E47.isIsomorphismTree(new BinaryNode<>("1"),new BinaryNode<>(1)));
        assertTrue(E47.isIsomorphismTree(new BinaryNode<>(1),new BinaryNode<>(1)));
        // 特殊情况
        assertTrue(E47.isIsomorphismTree(null, null));
        assertTrue(E47.isIsomorphismTree(tree1, tree1));
        assertFalse(E47.isIsomorphismTree(tree1, null));
        // 非法输入 todo 暂时没想到
    }
}
