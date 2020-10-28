package exercises;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

public class E5_3Test {
    public static Set<Integer> set = new HashSet<>();

    @BeforeClass
    public static void init() {
        Random rd = new Random();
        int length = 108;
        while (set.size() < length) {
            set.add(rd.nextInt());
        }
    }

    @Test
    public void testQuadraticProbing() {
        E5_3.QuadraticProbing<Integer> qp = new E5_3.QuadraticProbing<>();
        set.forEach(qp::insert);
        System.out.println(qp.clashCount);
    }


    @Test
    public void testLinearProbing() {
        E5_3.LinearProbing<Integer> lp = new E5_3.LinearProbing<>();
        set.forEach(lp::insert);
        System.out.println(lp.clashCount);
    }

    @Test
    public void testDoubleHash(){
        E5_3.DoubleHash<Integer> dh = new E5_3.DoubleHash<>();
        set.forEach(dh::insert);
        System.out.println(dh.clashCount);
    }

}
