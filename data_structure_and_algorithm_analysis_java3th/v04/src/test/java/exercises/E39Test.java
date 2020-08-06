package exercises;

import content.BinarySearchTree;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

public class E39Test {
    private BinarySearchTree.BinaryNode<Integer> root;

    public E39Test() {
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
    public void testFigureCompilation(){
        E39<Integer> tree = new E39<>(root);
        tree.figureCompilation()
                .stream()
                .sorted(Comparator.comparingInt(x -> x.value))
                .forEach(System.out::println);
        /*正确答案
        * Element{id=1, value=1, coordinate=Point{x=1, y=3}, lines=[Line{from='1', to='0'}, Line{from='1', to='0'}]}
            Element{id=3, value=2, coordinate=Point{x=2, y=2}, lines=[Line{from='3', to='1'}, Line{from='3', to='2'}]}
            Element{id=2, value=3, coordinate=Point{x=3, y=3}, lines=[Line{from='2', to='0'}, Line{from='2', to='0'}]}
            Element{id=7, value=4, coordinate=Point{x=4, y=1}, lines=[Line{from='7', to='3'}, Line{from='7', to='6'}]}
            Element{id=4, value=5, coordinate=Point{x=5, y=3}, lines=[Line{from='4', to='0'}, Line{from='4', to='0'}]}
            Element{id=6, value=6, coordinate=Point{x=6, y=2}, lines=[Line{from='6', to='4'}, Line{from='6', to='5'}]}
            Element{id=5, value=7, coordinate=Point{x=7, y=3}, lines=[Line{from='5', to='0'}, Line{from='5', to='0'}]}
        * */
    }
}
