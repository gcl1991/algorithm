package exercises;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import static org.junit.Assert.*;

public class E32Test {
    private E32<Integer> tree;
    private int numNode=20;

    @Before
    public void setTree() {
        tree = new E32<>();
        Random random = new Random();
        for(int i=0;i<numNode;i++){
            tree.insert(random.nextInt());
        }
    }

    @Test
    public void testCheckNode(){
        tree.checkNode();
    }
}
