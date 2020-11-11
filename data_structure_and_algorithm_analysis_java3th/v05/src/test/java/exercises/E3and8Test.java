package exercises;

import org.junit.Test;

public class E3and8Test extends BaseTest{
    @Test
    public void testQuadraticProbing() {
        E3and8.QuadraticProbing<Integer> qp = new E3and8.QuadraticProbing<>();
        set.forEach(qp::insert);
        System.out.println(qp.clashCount);
    }

    @Test
    public void testCubeProbing() {
        E3and8.CubeProbing<Integer> qp = new E3and8.CubeProbing<>();
        set.forEach(qp::insert);
        System.out.println(qp.clashCount);
    }

    @Test
    public void testLinearProbing() {
        E3and8.LinearProbing<Integer> lp = new E3and8.LinearProbing<>();
        set.forEach(lp::insert);
        System.out.println(lp.clashCount);
    }

    @Test
    public void testDoubleHash(){
        E3and8.DoubleHash<Integer> dh = new E3and8.DoubleHash<>();
        set.forEach(dh::insert);
        System.out.println(dh.clashCount);
    }

}
