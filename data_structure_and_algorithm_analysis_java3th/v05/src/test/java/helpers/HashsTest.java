package helpers;

import org.junit.Test;
import static org.junit.Assert.*;

public class HashsTest {
    @Test
    public void testMyhash(){
        assertEquals(Hashs.myhash(50,11),6);
        assertEquals(Hashs.myhash(-50,11),5);
    }
}
