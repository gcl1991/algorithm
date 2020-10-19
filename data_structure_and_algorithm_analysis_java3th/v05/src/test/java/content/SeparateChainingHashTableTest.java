package content;

import org.junit.Test;

public class SeparateChainingHashTableTest {
    @Test
    public void officeTest() {
        SeparateChainingHashTable<Integer> H = new SeparateChainingHashTable<>();

        long startTime = System.currentTimeMillis();

        final int NUMS = 2000000;
        final int GAP = 37;

        System.out.println("Checking... (no more output means success)");

        for (int i = GAP; i != 0; i = (i + GAP) % NUMS)
            H.insert(i);
        for (int i = 1; i < NUMS; i += 2)
            H.remove(i);

        for (int i = 2; i < NUMS; i += 2)
            if (!H.contains(i))
                System.out.println("Find fails " + i);

        for (int i = 1; i < NUMS; i += 2) {
            if (H.contains(i))
                System.out.println("OOPS!!! " + i);
        }

        long endTime = System.currentTimeMillis();

        System.out.println("Elapsed time: " + (endTime - startTime));
    }
}
