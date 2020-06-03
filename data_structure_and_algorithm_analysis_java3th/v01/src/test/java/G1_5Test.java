import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class G1_5Test {

    @Test
    public void getNumberOneBinary(){
        assertEquals(G1_5.getNumberOneBinary(1),1);
        assertEquals(G1_5.getNumberOneBinary(2),1);
        assertEquals(G1_5.getNumberOneBinary(3),2);
        assertEquals(G1_5.getNumberOneBinary(4),1);
        assertEquals(G1_5.getNumberOneBinary(5),2);
        assertEquals(G1_5.getNumberOneBinary(6),2);
        assertEquals(G1_5.getNumberOneBinary(7),3);
        assertEquals(G1_5.getNumberOneBinary(8),1);
        assertEquals(G1_5.getNumberOneBinary(9),2);
        assertEquals(G1_5.getNumberOneBinary(10),2);
    }
}
