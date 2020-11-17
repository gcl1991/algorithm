package exercises;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class E13Test {
    // sp1: 2 x^1 + 3 x^3 + 8 x^5 + 3 x^5 + 1 x^2
    // sp2: 5 x^9 + 2 x^1 + 4 x^7 + 7 x^1
    List<E13.Monomial> sp1 = new LinkedList<>();

    {
        sp1.add(new E13.Monomial(2, 1));
        sp1.add(new E13.Monomial(3, 3));
        sp1.add(new E13.Monomial(8, 5));
        sp1.add(new E13.Monomial(3, 5));
        sp1.add(new E13.Monomial(1, 2));
    }

    List<E13.Monomial> sp2 = new LinkedList<>();

    {
        sp2.add(new E13.Monomial(5, 9));
        sp2.add(new E13.Monomial(2, 1));
        sp2.add(new E13.Monomial(4, 7));
        sp2.add(new E13.Monomial(7, 1));
    }


    @Test
    public void testPolynomialMultiplication() {
        List<E13.Monomial> resultA = E13.Polynomial.multiplyA(sp1,sp2);
        System.out.println(resultA.toString());
        List<E13.Monomial> resultB = E13.Polynomial.multiplyB(sp1,sp2);
        System.out.println(resultB.toString());
    }

    @Test
    public void testToString(){
        System.out.println(sp2.toString());
    }
}
