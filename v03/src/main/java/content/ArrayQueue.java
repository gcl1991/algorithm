package content;

import java.util.NoSuchElementException;

public class ArrayQueue<T> {
    private int theSize;
    private int maxSize;
    private T[] theItems;
    private int front;
    private int back;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayQueue() {
        theItems = (T[]) new Object[DEFAULT_CAPACITY];
        theSize = 0;
        back = -1;
        front = 0;
        maxSize = DEFAULT_CAPACITY;
    }

    // 入队
    public void push(T x) {
        if (!isFull()) {
            back = (back + 1) % maxSize;
            theItems[back] = x;
            theSize++;
        } else {
            throw new UnsupportedOperationException("未实现数组扩展");
            // todo 扩张
        }
    }

    private boolean isFull() {
        return maxSize == theSize;
    }

    // 出队
    public T pop() {
        if (!isEmpty()) {
            T data = theItems[front];
            front = (front + 1) % maxSize;
            theSize--;
            return data;
        } else
            throw new NoSuchElementException();
    }

    private boolean isEmpty() {
        return size() == 0;
    }

    private int size() {
        return theSize;
    }
}
