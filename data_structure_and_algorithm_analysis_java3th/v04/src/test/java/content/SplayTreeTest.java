package content;

import org.junit.Test;

public class SplayTreeTest {

    @Test
    public void testOffice() {
        SplayTree<Integer> t = new SplayTree<Integer>();
        final int NUMS = 10;
        final int GAP = 1;

        System.out.println("Checking... (no bad output means success)");

        for (int i = GAP; i != 0; i = (i + GAP) % NUMS)
            t.insert(i);
        System.out.println("Inserts complete");

        for (int i = 1; i < NUMS; i += 2)
            t.remove(i);
        System.out.println("Removes complete");

        if (t.findMin() != 2 || t.findMax() != NUMS - 2)
            System.out.println("FindMin or FindMax error!");

        for (int i = 2; i < NUMS; i += 2)
            if (!t.contains(i))
                System.out.println("Error: find fails for " + i);

        for (int i = 1; i < NUMS; i += 2)
            if (t.contains(i))
                System.out.println("Error: Found deleted item " + i);
    }

    @Test
    public void testInsert() {
        SplayTree<Integer> t = new SplayTree<Integer>();
        final int NUMS = 10;
        for (int i = 0; i <NUMS; i++)
            t.insert(i);
    }
}
