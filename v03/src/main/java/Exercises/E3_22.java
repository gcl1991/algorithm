package Exercises;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class E3_22 {
    public static int computeSuffixExpression(String expression) {
        Set<String> symbols = new HashSet<>();
        symbols.addAll(Arrays.asList(new String[]{"+", "-", "*", "/"}));
        Stack<Integer> digits = new Stack<>();
        for (String x : expression.split("")) {
            computeExecute(symbols,x,digits);
        }
        return digits.pop();
    }

    public static void computeExecute(Set<String> symbols,String x,Stack<Integer> digits){
        if (symbols.contains(x)) {
            int x1 = digits.pop();
            int x2 = digits.pop();
            int tmp = 0;
            switch (x) {
                case "+":
                    tmp = x1 + x2;
                    break;
                case "-":
                    tmp = x1 - x2;
                    break;
                case "*":
                    tmp = x1 * x2;
                    break;
                case "/":
                    tmp = x1 / x2;
                default:
                    throw new IllegalArgumentException("未定义的运算符");
            }
            digits.add(tmp);
        } else {
            digits.add(Integer.parseInt(x));
        }

    }
}
