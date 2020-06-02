package content;

import org.junit.Test;

public class PrintDirectoryTreeTest {
    @Test
    public void testListAll(){
        PrintDirectoryTree.listAll(0,"./");
    }

    @Test
    public void testSizeAll(){
        PrintDirectoryTree.sizeAll(0,"./");
    }
}
