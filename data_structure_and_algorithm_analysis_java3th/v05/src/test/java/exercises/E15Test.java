package exercises;

import org.junit.Test;
import static org.junit.Assert.*;

public class E15Test {
    @Test
    public void testFindSubstringStartIndex() {
        String string = "123456789";
        String subString = "567";
        int startIndex = E15.findSubstringStartIndex(string,subString);
        assertEquals(4,startIndex);
    }
}
