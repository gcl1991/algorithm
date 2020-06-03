import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class G1_4Test {

    @Test
    public void readCodeFileTest() throws IOException {
        Path codeFile = Paths.get("src/test/resources/G1_4/root.txt");
        G1_4.readCodeFile(codeFile,"D:/learn/algorithm/data_structure_and_algorithm_analysis_java3th/v01/src/test/resources/G1_4");
    }
}
