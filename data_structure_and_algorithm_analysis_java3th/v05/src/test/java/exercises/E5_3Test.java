package exercises;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class E5_3Test {
    public static List<Integer> array = new ArrayList<>();

    @BeforeClass
    public static void init() {
        Random rd = new Random();
        int length = 100;
        for (int i = 0; i < length; i++) {
            array.add(rd.nextInt());
        }
    }

    @Test
    public void testSeparateInsert() {
        E5_3.SeparateChaining<Integer> si = new E5_3.SeparateChaining<>();
        array.forEach(si::insert);
        System.out.println(si.clashCount);
    }

    @Test
    public void testQuadraticProbing(){
        E5_3.QuadraticProbing<Integer> qp=new E5_3.QuadraticProbing<>();
        array.forEach(qp::insert);
        System.out.println(qp.clashCount);
    }
}
