import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.Assert.assertEquals;

public class G1_2Test {
    @Test
    public void  isExistWordTest() throws IOException {
        Path txtPathFile = Paths.get("src/test/resources/G1_2.txt");
        assertEquals(true,G1_2.isExistWord("this",txtPathFile));
    }
}
