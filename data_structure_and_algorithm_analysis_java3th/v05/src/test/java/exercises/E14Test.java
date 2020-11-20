package exercises;

import org.junit.Test;
import static org.junit.Assert.*;

public class E14Test {
    @Test
    public void testFindSubstringStartIndex() {
        String string = "123456789";
        String subString = "567";
        int startIndex = E14.findSubstringStartIndex(string,subString);
        assertEquals(4,startIndex);
    }
}
