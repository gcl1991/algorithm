package exercises;

import content.AvlTree;
import content.BinarySearchTree;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Random;

public class E31Test {
    private E31<Integer> tree;
    private int numNode=20;

    @Before
    public void setTree() {
        tree = new E31<>();
        Random random = new Random();
        for(int i=0;i<numNode;i++){
            tree.insert(random.nextInt());
        }
    }
    @Test
    public void testGetNumAllNode(){
        assertEquals(tree.getNumAllNode(),numNode);
    }

    @Test
    public void testGetNumLeafNodeAndFullNode(){
        assertEquals(tree.getNumLeafNode(),tree.getNumFullNode()+1);
    }

}
