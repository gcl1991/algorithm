import static org.junit.Assert.assertEquals;
import org.junit.Test;

interface MinSumRec{
    int apply(int[] array,int left,int right);
}

public class G2_17MinSumRecTest {
    private final int[] array = {1,4,-10,2,1,4,-8,2,7,8,1};
    private final int minSumRec = -11;

    public void minSumRec(MinSumRec getMinSumRec){
        int minSumRec;

        minSumRec = getMinSumRec.apply(array,0,array.length-1);

        assertEquals(minSumRec,this.minSumRec);
    }

    @Test
    public void minSumRec_N3Test(){
        minSumRec(G2_17MinSumRec::minSumRec_N3);
    }

    @Test
    public void minSumRec_N2Test(){
        minSumRec(G2_17MinSumRec::minSumRec_N2);
    }

    @Test
    public void minSumRec_NlogNTest(){
        minSumRec(G2_17MinSumRec::minSumRec_NlogN);
    }

    @Test
    public void minSumRec_NTest(){
        minSumRec(G2_17MinSumRec::minSumRec_N);
    }
}
