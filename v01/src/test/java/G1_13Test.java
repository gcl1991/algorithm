import org.junit.Test;

import static org.junit.Assert.*;

public class G1_13Test {
    public G1_13.Collection<String> collection;
    public G1_13Test(){
        collection = new G1_13.Collection<>(10,String[]::new);
        collection.insert(0,"1");
        collection.insert(1,"2");
    }
    @Test
    public void isEmptyTest(){
        collection.makEmpty();
        assertTrue(collection.isEmpty());
    }

    @Test
    public void insertTest(){
        collection.insert(1,"3");
        assertEquals(3, collection.getSize());
        assertTrue(collection.isPresent("3"));
    }

    @Test
    public void removeTest(){
        assertEquals(collection.remove(0),"1");
        assertFalse(collection.isPresent("1"));
        assertEquals(collection.getSize(),1);
    }

    @Test
    public void isPreSentTest(){
        assertTrue(collection.isPresent("2"));
        assertFalse(collection.isPresent("3"));
    }

}
