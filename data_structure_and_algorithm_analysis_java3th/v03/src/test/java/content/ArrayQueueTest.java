package content;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.NoSuchElementException;

public class ArrayQueueTest {
    ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();

    @Test(expected = NoSuchElementException.class)
    public void testConstructorNoParameter() {
        arrayQueue.pop();
    }

    @Test
    public void testPushEmpty() {
        arrayQueue.push(1);
    }

    @Test
    public void testPush1Circle() {
        for (int i = 0; i < 10; i++) {
            arrayQueue.push(i);
        }
        arrayQueue.pop();
        arrayQueue.push(1);
    }


    @Test(expected = UnsupportedOperationException.class)
    public void testPush1CircleException() {
        for (int i = 0; i < 10; i++) {
            arrayQueue.push(i);
        }
        arrayQueue.push(10);
    }


    @Test
    public void testPush2Circle() {
        for (int i = 0; i < 10; i++) {
            arrayQueue.push(i);
        }

        for (int i = 0; i < 10; i++) {
            arrayQueue.pop();
        }

        for (int i = 0; i < 10; i++) {
            arrayQueue.push(i);
        }

        arrayQueue.pop();
        arrayQueue.push(1);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testPush2CircleException() {
        for (int i = 0; i < 10; i++) {
            arrayQueue.push(i);
        }

        for (int i = 0; i < 10; i++) {
            arrayQueue.pop();
        }

        for (int i = 0; i < 10; i++) {
            arrayQueue.push(i);
        }

        arrayQueue.push(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void testPopEmpty() {
        arrayQueue.pop();
    }

    @Test
    public void testPop1Circle() {
        for (int i = 0; i < 10; i++) {
            arrayQueue.push(i);
        }
        for (int i = 0; i < 10; i++) {
            int item = arrayQueue.pop();
            assertEquals(item, i);
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void testPop1CircleException() {
        for (int i = 0; i < 10; i++) {
            arrayQueue.push(i);
        }
        for (int i = 0; i < 10; i++) {
            arrayQueue.pop();
        }
        arrayQueue.pop();
    }

    @Test
    public void testPop2Circle() {
        for (int i = 0; i < 10; i++) {
            arrayQueue.push(i);
        }

        for (int i = 0; i < 10; i++) {
            int item = arrayQueue.pop();
            assertEquals(item,i);
        }

        for (int i = 0; i < 10; i++) {
            arrayQueue.push(i);
        }

        for (int i = 0; i < 10; i++) {
            int item = arrayQueue.pop();
            assertEquals(item,i);
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void testPop2CircleException() {
        for (int i = 0; i < 10; i++) {
            arrayQueue.push(i);
        }

        for (int i = 0; i < 10; i++) {
            arrayQueue.pop();
        }

        for (int i = 0; i < 10; i++) {
            arrayQueue.push(i);
        }

        for (int i = 0; i < 10; i++) {
            arrayQueue.pop();
        }
        arrayQueue.pop();
    }
}
