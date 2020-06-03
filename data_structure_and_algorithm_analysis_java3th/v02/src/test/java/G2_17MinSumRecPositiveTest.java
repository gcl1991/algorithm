import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class G2_17MinSumRecPositiveTest {
    private int[] array = {4,9,8,0,-2,3,4,-8,9,-5,6,0,3,-8,1};
    private int minSumRecPositive = 1;
    @Test
    public void minSumRec_N3Test(){
        int minSumRecPositive = G2_17MinSumRecPositive.minSumRec_N3(array);

        assertEquals(minSumRecPositive,this.minSumRecPositive);
    }

    @Test
    public void minSumRec_N2Test(){
        int minSumRecPositive = G2_17MinSumRecPositive.minSumRec_N2(array);

        assertEquals(minSumRecPositive,this.minSumRecPositive);
    }

    @Test
    public void minSumRec_NlogN(){
        int minSumRecPositive = G2_17MinSumRecPositive.minSumRec_NlogN(array);

        assertEquals(minSumRecPositive,this.minSumRecPositive);
    }

    @Test
    public void minSumRec_N(){
        int minSumRecPositive = G2_17MinSumRecPositive.minSumRec_N(array);

        assertEquals(minSumRecPositive,this.minSumRecPositive);
    }
}
