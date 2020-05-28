package Exercises;

import static org.junit.Assert.*;
import org.junit.Test;

public class E3_22Test {
    @Test
    public void testComputeSuffixExpression(){
        String expression = "123*+45*6+7*+";

        int result = E3_22.computeSuffixExpression(expression);

        assertEquals(result,189);
    }
}
