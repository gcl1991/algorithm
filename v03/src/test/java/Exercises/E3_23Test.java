package Exercises;

import static org.junit.Assert.*;
import org.junit.Test;

public class E3_23Test {
    private E3_23 e323=new E3_23();
    @Test
    public void testExpressionInfix2Suffix() {
        String expressionInfix = "a+b*c+(d*e+f)*g";

        String expressionSuffix = e323.expressionInfix2Suffix(expressionInfix);

        assertEquals(expressionSuffix,"abc*+de*f+g*+");
    }

    @Test
    public void testExpressionPowerOperation() {
        String expressionInfix = "a+b*c+(d*e+f)*g+2^2^3";

        String expressionSuffix = e323.expressionInfix2Suffix(expressionInfix);

        assertEquals(expressionSuffix,"abc*+de*f+g*+223^^+");
    }

    @Test
    public void testExpressionSuffix2Infix(){
        String expressionSuffix = "abc*+de*f+g*+";

        String expressionInfix = e323.expressionSuffix2Infix(expressionSuffix);

        assertEquals(expressionInfix,"a+b*c+(d*e+f)*g");

    }
}
