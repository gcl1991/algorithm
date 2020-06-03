import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class G2_17MaxProductRecTest {
    private int[] array = {4,9,8,0,-2,3,4,-8,9,-5,6,0,3,-8,1};
    private long maxProduct = 25920;

    @Test
    public void maxProductRec_N2Test(){
        long maxProduct = G2_17MaxProductRec.maxProductRec_N2(array);

        assertEquals(maxProduct,this.maxProduct);
    }

    @Test
    public void maxProductRec_NTest(){
        long maxProduct = G2_17MaxProductRec.MaxProductRec_N.getMaxProduct(array);

        assertEquals(maxProduct,this.maxProduct);
    }
}
