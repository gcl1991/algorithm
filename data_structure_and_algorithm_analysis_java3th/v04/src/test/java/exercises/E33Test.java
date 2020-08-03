package exercises;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class E33Test {
    private E33<Integer> tree;
    private int numNode=20;

    @Before
    public void setTree() {
        tree = new E33<>();
        Random random = new Random();
        for(int i=0;i<numNode;i++){
            tree.insert(random.nextInt());
        }
    }

    @Test
    public void testRemoveAllLeafNode(){
        System.out.println(tree.getNumAllNode());
        System.out.println(tree.getNumLeafNode());
        tree.removeAllLeafNode();
        System.out.println(tree.getNumAllNode());
    }
}
