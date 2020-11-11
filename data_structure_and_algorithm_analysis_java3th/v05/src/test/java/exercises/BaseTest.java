package exercises;

import org.junit.BeforeClass;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class BaseTest {
    public static Set<Integer> set = new HashSet<>();
    @BeforeClass
    public static void init() {
        Random rd = new Random();
        int length = 219;
        while (set.size() < length) {
            set.add(rd.nextInt());
        }
    }
}
