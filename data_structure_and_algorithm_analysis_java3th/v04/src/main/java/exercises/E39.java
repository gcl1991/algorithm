package exercises;

import content.BinarySearchTree.BinaryNode;

import java.util.*;

public class E39<T> {
    private BinaryNode<T> root;
    private int x;
    private List<Element<T>> elements;

    E39(BinaryNode<T> root) {
        this.root = root;
        elements = new ArrayList<>();
    }

    public List<Element<T>> figureCompilation() {
        figureCompilation(root, 0,0);
        return elements;
    }

    private int figureCompilation(BinaryNode<T> node,int idStart, int yStart) {
        if (node == null)
            return idStart;  // 保持状态连续
        int yCoordinate = yStart + 1;
        int lId = figureCompilation(node.left,idStart, yCoordinate);
        x++;
        int xCoordinate = x;
        int rId = figureCompilation(node.right,lId, yCoordinate);
        int id = rId+1;
        elements.add(new Element<>(
                        id, node.element,
                        new Point(xCoordinate, yCoordinate),
                        Arrays.asList(
                                new Line(Integer.toString(id), Integer.toString(lId!=idStart?lId:0)),
                                new Line(Integer.toString(id), Integer.toString(rId!=lId?rId:0))
                        )
                )
        );
        return id;
    }

    public static class Element<T> {
        int id;
        T value;
        Point coordinate;
        List<Line> lines;

        Element(int id, T value, Point coordinate, List<Line> lines) {
            this.id = id;
            this.value = value;
            this.coordinate = coordinate;
            this.lines = lines;
        }

        public Point point() {
            return coordinate;
        }

        public List<Line> lines() {
            return lines;
        }

        @Override
        public String toString() {
            return "Element{" +
                    "id=" + id +
                    ", value=" + value +
                    ", coordinate=" + coordinate +
                    ", lines=" + lines +
                    '}';
        }
    }

    public static class Point {
        public final int x;
        public final int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int x() {
            return x;
        }

        public int y() {
            return y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public static class Line {
        public final String from;
        public final String to;

        Line(String from, String to) {
            this.from = from;
            this.to = to;
        }

        public String from() {
            return from;
        }

        public String to() {
            return to;
        }

        @Override
        public String toString() {
            return "Line{" +
                    "from='" + from + '\'' +
                    ", to='" + to + '\'' +
                    '}';
        }
    }

}
