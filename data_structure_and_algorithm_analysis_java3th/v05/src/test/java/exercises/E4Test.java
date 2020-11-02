package exercises;

import org.junit.Test;

import static org.junit.Assert.*;

public class E4Test extends BaseTest {
    E4.SeparateChainingWithSingle<Integer> sc = new E4.SeparateChainingWithSingle<>();

    @Test
    public void testInsert() {
        set.forEach(sc::insert);
    }

    @Test
    public void testContains() {
        set.forEach(sc::insert);
        long num = set.stream().filter(sc::contains).count();
        assertEquals(set.size(), num);
    }

    @Test
    public void testRemove() {
        set.forEach(sc::insert);
        set.forEach(x -> {
            sc.remove(x);
            assertFalse(sc.contains(x));
        });
        assertEquals(sc.size(), 0);
    }
}
