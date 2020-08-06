package exercises;

import content.BinarySearchTree.BinaryNode;

public class E38<T> {
    private int x;
    private int y;
    private int count = 0;
    private BinaryNode<T> root;

    E38(BinaryNode<T> root) {
        this.root = root;
    }

    public int getCoordinateXStateful(T element) {
        x = -1;
        count = 0;
        getCoordinateXStateful(root, element);
        return x;
    }

    private void getCoordinateXStateful(BinaryNode<T> node, T element) {
        if (node == null)
            return;
        getCoordinateXStateful(node.left, element);
        count++;
        if (node.element.equals(element)) {
            x = count;
        }
        getCoordinateXStateful(node.right, element);
    }

    public int getCoordinateXNoStatus(T element) {
        getCoordinateXNoStatus(root, element, 0);
        return x;
    }

    /**
     * 中序遍历
     * 假设：起始坐标x0
     * 对任意节点A，其父节点的起始坐标为xi（根节点xi=x0）
     * 则A左子树起始坐标为xi,左子树中节点数量为k,则左子树结束坐标为xi+k
     * 得出A节点坐标为xa = xi+k+1
     * A右子树起始坐标为xa，右子树中节点数量为j,则右子树结束坐标为xa+j
     * 返回xa+j给A节点的父节点，通知父节点A子树的结束坐标为xa+j
     * 即对于任意节点x其左子树起始坐标为x父节点的起始坐标，x右子树的起始坐标为x节点的坐标。
     */
    private int getCoordinateXNoStatus(BinaryNode<T> node, T element, int start) {
        if (node == null)
            return start;
        int coordinate = getCoordinateXNoStatus(node.left, element, start);
        coordinate++;
        if (node.element.equals(element)) {
            x = coordinate;
        }
        return getCoordinateXNoStatus(node.right, element, coordinate);
    }

    public int getCoordinateYNoStatus(T element) {
        y = 0;
        updateYCoordinateNoStatus(root, element, -1);
        return y;
    }

    private void updateYCoordinateNoStatus(BinaryNode<T> node, T element, int start) {
        // 基准
        if (node==null)
            return;
        // 治
        int yCoordinate = start + 1;
        if (element.equals(node.element))
            y=yCoordinate;
        // 分
        updateYCoordinateNoStatus(node.left, element, yCoordinate);
        updateYCoordinateNoStatus(node.right, element, yCoordinate);
    }

    public void print() {
        printAndCount(root);
    }

    private int printAndCount(BinaryNode<T> node) {
        if (node == null)
            return 0;
        int leftCoordinateX = printAndCount(node.left);
        int nodeX = leftCoordinateX + 1;
        int rightCoordinateX = printAndCount(node.right);
        System.out.println("节点:" + node.element);
        System.out.println("高度" + leftCoordinateX + 1 + rightCoordinateX + "=" + (leftCoordinateX + 1 + rightCoordinateX));
        return nodeX + rightCoordinateX;
    }
}
