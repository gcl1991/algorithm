import static org.junit.Assert.*;
import org.junit.Test;

public class G2_16Test {
    private int a=1989;
    private int b=1590;
    private int result=3;

    @Test
    public void gcdRecursiveTest(){
        assertEquals(G2_16.gcdRecursive(a,b),result);
    }

    @Test
    public void gcdWhileTest(){
        assertEquals(G2_16.gcdWhile(a,b),result);
    }

    @Test
    public void gcdTest(){
        assertEquals(G2_16.gcd(1990,1590),10);
        assertEquals(G2_16.gcd(1989,1591),1);
        assertEquals(G2_16.gcd(1990,1591),1);
        assertEquals(G2_16.gcd(1989,1590),3);
    }

}
