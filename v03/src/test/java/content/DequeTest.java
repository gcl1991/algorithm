package content;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.NoSuchElementException;

public class DequeTest {
    Deque<Integer> deque = new Deque<>();

    @Test
    public void testConstructorNoParameters(){
        assertTrue(deque.isEmpty());
    }

    @Test
    public void testPush(){
        deque.push(1);

        assertFalse(deque.isEmpty());
        assertEquals(deque.size(),1);

        deque.push(2);
        assertEquals(deque.size(),2);
    }

    @Test
    public void testPop(){
        deque.push(1);
        deque.push(2);
        int v = deque.pop();

        assertEquals(v,2);
        assertEquals(deque.size(),1);
    }


    @Test(expected = NoSuchElementException.class)
    public void testPopExceptionNoElements(){
        deque.pop();
    }

    @Test(expected = NoSuchElementException.class)
    public void testPopExceptionHasElements(){
        deque.push(1);
        deque.push(2);
        deque.pop();
        deque.pop();
        deque.pop();
    }

    @Test
    public void testInject(){
        deque.inject(1);
        deque.inject(2);

        assertEquals(deque.size(),2);
    }

    @Test
    public void testEject(){
        deque.inject(1);
        deque.inject(2);

        int data = deque.eject();

        assertEquals(data,2);
        assertEquals(deque.size(),1);
    }
}
