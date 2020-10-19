package exercises;

import com.sun.istack.internal.NotNull;

import java.util.Objects;


public class E50<T extends Comparable<T>> {
    private TrailBinaryNode<T> root;

    public void insert(T element) {
        if (root == null) {
            // 左右线索标识为True，以作为迭代终点。
            root = new TrailBinaryNode<>(element,null,null,true,true);
        }
        insert(root, element);
    }

    /**
     * 插入左子树：新节点右指针指向父节点(升序/中序迭代的下一节点为父节点),新节点左指针继承父节点左指针(新节点代替父节点指向降序迭代的下一节点)
     * 插入右子树：新节点左指针指向父节点，新节点右指针继承父节点右指针，原理与左子树插入相同。
     */
    private void insert(TrailBinaryNode<T> node, T element) {
        // NULL指针检测
        if (node == null) {
            return;
        }
        TrailBinaryNode<T> insertPoint = getInsertPoint(node, element);
        if(insertPoint == null){
            return;
        }
        if (element.compareTo(insertPoint.element) < 0) {
            insertPoint.left = new TrailBinaryNode<>(element, insertPoint.left, insertPoint, insertPoint.isLeftTrail, true);
            insertPoint.isLeftTrail = false;
        } else if (element.compareTo(insertPoint.element) > 0) {
            insertPoint.right = new TrailBinaryNode<>(element, insertPoint, insertPoint.right, true, insertPoint.isRightTrail);
            insertPoint.isRightTrail = false;
        } // 如果元素已经存在，则什么都不做
    }

    private TrailBinaryNode<T> getInsertPoint(TrailBinaryNode<T> node, T element) {
        TrailBinaryNode<T> insertPoint = node;
        while (node != null) {
            if (element.compareTo(node.element) < 0) {
                insertPoint = node;
                node = node.left;
            } else if (element.compareTo(node.element) > 0) {
                insertPoint = node;
                node = node.right;
            } else {
                return null;
            }
        }
        return insertPoint;
    }

    public boolean contains(T element) {
        return contains(root, element);
    }

    private boolean contains(TrailBinaryNode<T> node, T element) {
        // 基准情况+定义域检测（NULL指针检测+泛型自动类型检测）
        if (node == null || element == null) {
            return false;
        }

        // 递归查询
        if (Objects.equals(node.element, element)) {
            return true;
        } else if (element.compareTo(node.element) < 0) {
            return contains(node.left, element);
        } else {
            return contains(node.right, element);
        }
    }

    void printAsc() {
        // 定义域检测
        if (root == null) {
            return;
        }
        // 获取起始节点
        TrailBinaryNode<T> startPoint = getBestLeftNode(root);
        printAsc(startPoint);
    }

    /**
     * 右线索与右连接的区分依据
     * 方法1（失败）
     * 因为右线索会形成简单圈，可以据此进行查询。
     * 查询当前节点元素是否在右指针所指的树中，如果在则为右线索（会走子节点左链，形成误判），如果不在则为右链。
     * 方法2
     * 引入布尔标识符
     */
    private void printAsc(TrailBinaryNode<T> node) {
        if (node == null) {
            return;
        }
        // 为右线索，下一节点为右链指向的节点
        System.out.println(node);
        if (node.isRightTrail) {
            printAsc(node.right);
        } else{
        // 为右链，下一节点为右子树中最左节点(即右子树最小节点，本轮列迭结束/新一轮迭代的开始)
            TrailBinaryNode<T> newStart=getBestLeftNode(node.right);
            printAsc(newStart);
        }
    }

    /**
     * 包内使用，调用者保证输入非null
     */
    TrailBinaryNode<T> getBestLeftNode(@NotNull TrailBinaryNode<T> node) {
        TrailBinaryNode<T> startPoint = node;
        // 需要排除左线索，避免死循环
        while (startPoint.left != null && !startPoint.isLeftTrail) {
            startPoint = startPoint.left;
        }
        return startPoint;
    }

    static class TrailBinaryNode<T> {
        T element;
        TrailBinaryNode<T> left;
        TrailBinaryNode<T> right;
        boolean isLeftTrail = false;
        boolean isRightTrail = false;

        TrailBinaryNode(T theElement) {
            this(theElement, null, null, false, false);
        }

        TrailBinaryNode(T theElement, TrailBinaryNode<T> lt, TrailBinaryNode<T> rt, boolean isLeftTrail, boolean isRightTrail) {
            element = theElement;
            left = lt;
            right = rt;
            this.isLeftTrail = isLeftTrail;
            this.isRightTrail = isRightTrail;
        }

        @Override
        public String toString() {
            return "TrailBinaryNode{" +
                    "element=" + element +
                    ", isLeftTrail=" + isLeftTrail +
                    ", isRightTrail=" + isRightTrail +
                    '}';
        }
    }
}
