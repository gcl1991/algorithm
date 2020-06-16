import static org.junit.Assert.*;
import org.junit.Test;

public class G2_16Test {
    private G2_16.GcdPair pair=new G2_16.GcdPair(1989,1590);
    private int abCommonDivisor=3;

    @Test
    public void gcdRecursiveTest(){
        assertEquals(G2_16.gcdRecursive(pair),abCommonDivisor);
    }

    @Test
    public void gcdWhileTest(){
        assertEquals(G2_16.gcdWhile(pair),abCommonDivisor);
    }

    @Test
    public void gcdTest(){
        assertEquals(G2_16.gcd(new G2_16.GcdPair(1990,1590)),10);
        assertEquals(G2_16.gcd(new G2_16.GcdPair(1989,1591)),1);
        assertEquals(G2_16.gcd(new G2_16.GcdPair(1990,1591)),1);
        assertEquals(G2_16.gcd(new G2_16.GcdPair(1989,1590)),3);
    }

}
