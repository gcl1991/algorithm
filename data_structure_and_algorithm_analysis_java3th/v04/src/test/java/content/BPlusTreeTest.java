package content;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.stream.IntStream;

public class BPlusTreeTest {
    @Test
    public void testConstructor() {
        BPlusTree<Integer> tree = new BPlusTree<>(4, 4);
        System.out.println(tree);
    }

    @Test
    public void testAdd() {
        BPlusTree<Integer> tree = new BPlusTree<>(4, 6);
        for (int x : IntStream.rangeClosed(40, 62).toArray()) {
            tree.add(x);
        }
        tree.levelPrint();
    }

    @Test
    public void testPrint() {
        BPlusTree<Integer> tree = new BPlusTree<>(4, 6);
        for (int x : IntStream.rangeClosed(40, 49).toArray()) {
            tree.add(x);
        }
        tree.levelPrint();
    }

    @Test
    public void testContains(){
        BPlusTree<Integer> tree = new BPlusTree<>(4, 6);
        for (int x : IntStream.rangeClosed(40, 62).toArray()) {
            tree.add(x);
        }
        for (int x : IntStream.rangeClosed(40, 62).toArray()) {
            assertTrue(tree.contains(x));
        }
    }

    @Test
    public void testRemove(){
        BPlusTree<Integer> tree = new BPlusTree<>(4, 6);
        for (int x : IntStream.rangeClosed(40, 62).toArray()) {
            tree.add(x);
        }
        for (int x : IntStream.rangeClosed(40, 62).toArray()) {
            tree.remove(x);
            assertFalse(tree.contains(x));
        }
    }
}
