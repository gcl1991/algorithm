import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class G1_14Test {
    public G1_14.OrderedCollection<String> collection;
    public G1_14Test(){
        collection = new G1_14.OrderedCollection<>(10,String[]::new);
        collection.insert(0,"1");
        collection.insert(1,"2");
        collection.insert(2,"3");
        collection.insert(3,"4");
    }

    @Test
    public void findMinTest(){
        String minElement = collection.findMin();
        assertEquals(minElement,"1");
    }

    @Test
    public void findMaxTest(){
        String minElement = collection.findMax();
        assertEquals(minElement,"4");
    }
}
