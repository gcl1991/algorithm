package exercises;

public class E35 {
    public static <T> Node<T> build(int h, T[] array) {
        return build(h, -1, array);
    }

    private static <T> Node<T> build(int h, int start, T[] array) {
        if (h == 0)
            return new Node<T>(array[start + 1], null, null);
        if (h == -1)
            return null;
        int offset = E18.analyticalSolution(h - 2) + 1;
        int index = start + offset;
        Node<T> node = new Node<>(array[index]);
        node.left = build(h - 2, start, array);
        node.right = build(h - 1, index, array);
        return node;
    }

    private static <T> Node<T> new0() {
        return new Node<T>(null);
    }


    static class Node<T> {
        T element;
        Node<T> left;
        Node<T> right;

        Node(T element) {
            this.element = element;
            left = null;
            right = null;
        }

        Node(T element, Node<T> left, Node<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }
    }
}
