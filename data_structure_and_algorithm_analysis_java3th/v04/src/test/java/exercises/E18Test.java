package exercises;

import org.junit.Test;
import static org.junit.Assert.*;

public class E18Test {

    @Test
    public void testRecursiveSolution(){
        assertEquals(E18.recursiveSolution(4),12);
    }

    @Test
    public void testAnalyticalSolution(){
        assertEquals(E18.analyticalSolution(4),12);
    }
}
