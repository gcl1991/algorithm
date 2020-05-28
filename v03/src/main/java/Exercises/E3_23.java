package Exercises;

import javax.print.DocFlavor;
import java.util.*;

public class E3_23 {
    Set<String> symbols;
    Map<String, Integer> priority = new HashMap<>();

    public E3_23() {
        symbols = new HashSet<>(Arrays.asList("+", "-", "*", "/", "(", ")", "^"));
        priority.put("+", 0);
        priority.put("-", 0);
        priority.put("*", 1);
        priority.put("/", 1);
        priority.put("^", 2);
        priority.put("(", 3);
        priority.put(")", -1);
    }

    public String expressionInfix2Suffix(String eInfix) {
        Stack<String> tmpSymbols = new Stack<>();
        StringBuilder eSuffix = new StringBuilder();

        for (String x : eInfix.split("")) {
            if (symbols.contains(x)) {
                if (tmpSymbols.empty()) {
                    tmpSymbols.add(x);
                } else {
                    eSuffix.append(popSymbols(tmpSymbols, x));
                    if (!x.equals(")"))
                        tmpSymbols.add(x);
                }
            } else {
                eSuffix.append(x);
            }
        }
        while (!tmpSymbols.empty()) {
            eSuffix.append(tmpSymbols.pop());
        }
        return eSuffix.toString();
    }

    public String popSymbols(Stack<String> tmpSymbols, String x) {
        StringBuilder symbols = new StringBuilder();
        while (!tmpSymbols.empty() && priority.get(tmpSymbols.peek()) >= priority.get(x)) {
            if (x.equals("^") && tmpSymbols.peek().equals("^")) {
                break;
            }
            if (!tmpSymbols.peek().equals("(")) {
                symbols.append(tmpSymbols.pop());
            } else if (!x.equals(")")) {
                break;
            } else {
                tmpSymbols.pop();
                break;
            }
        }
        return symbols.toString();
    }

    public String expressionSuffix2Infix(String eSuffix) {
        Stack<String> eInfix = new Stack<>();

        for (String x : eSuffix.split("")) {
            if (symbols.contains(x)) {
                String rightExpression = getExpression(eInfix);
                String leftExpression = getExpression(eInfix);
                String expression = combination(x,leftExpression,rightExpression);
                eInfix.add(expression);
            } else {
                eInfix.add(x);
            }
        }
        return eInfix.pop();
    }

    public String getExpression(Stack<String> eInfix) {
        StringBuilder expression = new StringBuilder();
        boolean isExpressionCurrent = false;
        boolean isExpressionPrevious = false;
        while (!eInfix.isEmpty()) {
            String item = eInfix.pop();
            expression.append(item);
            if (eInfix.isEmpty()) {
                break;
            }
            isExpressionCurrent = isExpression(item);
            isExpressionPrevious = isExpression(eInfix.peek());
            if (isExpressionCurrent && isExpressionPrevious) {
                break;
            }
        }
        return expression.toString();
    }

    public boolean isExpression(String s) {
        return s.length() > 1 || !symbols.contains(s);
    }

    public String combination(String symbol,String leftExpression,String rightExpression){
        if (symbol.equals("*")||symbol.equals("/")){
            if (leftExpression.length()>1)
                leftExpression="("+leftExpression+")";
            if (rightExpression.length()>1)
                rightExpression="("+rightExpression+")";
        }
        return leftExpression+symbol+rightExpression;
    }
}
