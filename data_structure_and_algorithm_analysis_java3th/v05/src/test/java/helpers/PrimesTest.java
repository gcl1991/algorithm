package helpers;

import org.junit.Test;
import static org.junit.Assert.*;

public class PrimesTest {
    @Test
    public void testNextPrime() {
        assertEquals(11, Primes.nextPrime(7));
    }
}
