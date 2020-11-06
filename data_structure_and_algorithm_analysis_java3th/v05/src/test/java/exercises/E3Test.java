package exercises;

import org.junit.Test;

public class E3Test extends BaseTest{
    @Test
    public void testQuadraticProbing() {
        E3.QuadraticProbing<Integer> qp = new E3.QuadraticProbing<>();
        set.forEach(qp::insert);
        System.out.println(qp.clashCount);
    }


    @Test
    public void testLinearProbing() {
        E3.LinearProbing<Integer> lp = new E3.LinearProbing<>();
        set.forEach(lp::insert);
        System.out.println(lp.clashCount);
    }

    @Test
    public void testDoubleHash(){
        E3.DoubleHash<Integer> dh = new E3.DoubleHash<>();
        set.forEach(dh::insert);
        System.out.println(dh.clashCount);
    }

}
