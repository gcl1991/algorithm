package content;


import static Helpers.StringHelpers.stringMultiplication;

class BPlusTree<T extends Comparable<T>> {
    private Node<T> root;
    private long size;
    private int height;
    private final int m;
    private final int l;

    BPlusTree(int m, int l) {
        root = null;
        this.m = m;
        this.l = l;
        size = 0;
        // 高度包括叶节点
        height = 0;
    }

    /**
     * 元素插入时仅关注节点分裂和节点元素上限即可，不需要关心节点元素融合与下限
     */
    public void add(T element) {
        if (root == null) {
            root = new Node<>(element, null, null,null);
            // 使用element=null标记边界节点
            Node<T> bound = new Node<>(null, null, root, null);
            root.nextSibling = bound;
            bound.firstChild = new Node<>(element, null, new Node<>(null, null, null));
            height++;
        } else {
            root = add(root, element);
            if (isSplitNodeNormal(root, m)) {
                root = new Node<>(null, root, null);
                splitFirstChildOfNormal(root);
                height++;
            }
        }
        size++;
    }


    private Node<T> add(Node<T> node, T element) {
        if (node == null) {
            return null;
        }
        Node<T> pointer = node;
        // 同层遍历
        while (pointer.element != null && element.compareTo(pointer.element) > 0) {
            pointer = pointer.nextSibling;
        }
        if (isLeafNode(pointer)) {
            // pointer为树叶,元素插入
            insertBefore(pointer, element);
            // 结束递归
            return node;
        } else {
            // 非叶节点
            pointer.firstChild = add(pointer.firstChild, element);
            if (!isLeafNode(pointer.firstChild) && isSplitNodeNormal(pointer.firstChild, m)) {
                // 分裂非叶节点
                splitFirstChildOfNormal(pointer);
            } else if (isLeafNode(pointer.firstChild) && isSplitNodeLeaf(pointer.firstChild, l)) {
                // 分裂叶节点
                splitFirstChildOfLeaf(pointer);
            }
        }
        // 向上传递
        return node;
    }

    /**
     * 链表元素插入（插入且排序）,只有叶节点存在插入，中间节点只存在分裂
     */
    private void insertBefore(Node<T> node, T element) {
        node.nextSibling = new Node<>(node.element, null, node.nextSibling);
        node.element = element;
    }

    /**
     * 叶节点和非叶节点判断是否需要分裂在统计方式上存在细微差别：叶节点统计自身元素数量，非叶节点统计儿子元素数量
     */
    private boolean isSplitNodeNormal(Node<T> node, int numMax) {
        int numChild = 0;
        Node<T> p = node;
        while (p != null) {
            numChild++;
            p = p.nextSibling;
        }
        return numChild > numMax;
    }

    private void splitFirstChildOfNormal(Node<T> node) {
        int rootIdx = (m + 1) / 2;
        assert rootIdx > 0;
        Node<T> upperPartEnd = node.firstChild;
        Node<T> previous = null;
        for (int i = 0; i < rootIdx; i++) {
            previous  = upperPartEnd;
            upperPartEnd = upperPartEnd.nextSibling;
        }
        // 新边界点
        previous.nextSibling = new BoundNode<>(null,upperPartEnd.firstChild,null,previous);
        upperPartEnd.firstChild = null;

        // 切分
        Node<T> lowerPartStart = upperPartEnd.nextSibling;
        upperPartEnd.nextSibling = null;

        // 采用单链表的方式，更新链，完成新增节点的插入
        node.nextSibling = new Node<>(node.element, lowerPartStart, node.nextSibling);
        // 此元素将上推到父节点层次
        node.element = upperPartEnd.element;
        upperPartEnd.element = null;
    }

    private boolean isSplitNodeLeaf(Node<T> node, int numMax) {
        return getLengthLeafNode(node) > numMax;
    }

    /**
     * 获取叶子中元素的总数
     * <p>
     * 如果节点中数据element=Null，则该节点将被忽略
     * 如果传入Null将返回 0
     *
     * @param head 必须为叶子首元素引用
     * @return 叶子元素总数
     */
    private int getLengthLeafNode(Node<T> head) {
        if (head == null) {
            return 0;
        }
        int num = 0;
        while (head.element != null) {
            num++;
            head = head.nextSibling;
        }
        return num;
    }

    private void splitFirstChildOfLeaf(Node<T> node) {
        int rootIdx = (l + 1) / 2;
        assert rootIdx > 0;
        Node<T> upperPartEnd = null;
        Node<T> lowerPartStart = node.firstChild;
        for (int i = 0; i < rootIdx; i++) {
            upperPartEnd = lowerPartStart;
            lowerPartStart = lowerPartStart.nextSibling;
        }
        // 标记叶节点结束，便于增加元素时统一处理节点
        upperPartEnd.nextSibling = new Node<>(null, null, null);

        node.nextSibling = new Node<>(node.element, lowerPartStart, node.nextSibling);
        node.element = lowerPartStart.element;
    }

    // 查询

    boolean contains(T element) {
        return contains(root, element);
    }

    private boolean contains(Node<T> node, T element) {
        if (node == null) {
            return false;
        }
        if (isLeafNode(node)) {
            for (Node<T> pointer = node; pointer.element != null; pointer = pointer.nextSibling) {
                if (element.compareTo(pointer.element) == 0) {
                    return true;
                }
            }
            return false;
        }

        // 本层进行层序遍历
        for (Node<T> pointer = node; pointer != null; pointer = pointer.nextSibling) {
            // 递归遍历下一层
            if (pointer.element == null || element.compareTo(pointer.element) < 0) {
                return contains(pointer.firstChild, element);
            }
        }
        return false;
    }

    private boolean isLeafNode(Node<T> node) {
        if (node == null) {
            return false;
        }
        return (node.nextSibling != null && node.nextSibling.firstChild == null)
                || (node.element == null && node.firstChild == null);
    }

    //  打印

    void levelPrint() {
        print(root, 0);

    }

    /**
     * 中序遍历B树，以检查插入是否正确
     * while遍历本层+递归遍历下一层
     */
    private void print(Node<T> node, int numLevel) {
        if (node == null) {
            return;
        }
        // 本层进行层序遍历
        for (Node<T> pointer = node; pointer != null; pointer = pointer.nextSibling) {
            // 递归遍历下一层
            print(pointer.firstChild, numLevel + 1);
            System.out.printf("%s第%s层:%s\n", stringMultiplication("\t", numLevel), numLevel, pointer.element);
        }
    }

    public boolean remove(T element) {
        return remove(root, element);
    }

    private boolean remove(Node<T> node, T element) {
        // 特殊情况
        if (node == null) {
            return false;
        }

        // 基准情况
        if (isLeafNode(node)) {
            for (Node<T> pointer = node; pointer.element != null; pointer = pointer.nextSibling) {
                if (element.equals(pointer.element)) {
                    // 删除元素
                    pointer.element = pointer.nextSibling.element;
                    pointer.nextSibling = pointer.nextSibling.nextSibling;
                    return true;
                }
            }
            return false;
        }

        // 层序遍历
        for (Node<T> pointer = node; pointer != null; pointer = pointer.nextSibling) {
            // 递归遍历
            if (isInNextLevel(pointer,element)) {
                boolean isRemove = remove(pointer.firstChild, element);
                if (isNeedAdopt(pointer.firstChild)) {
                    adoptForFirstChild(pointer);
                }
                return isRemove;
            }
        }
        return false;
    }

    private boolean isInNextLevel(Node<T> node,T element){
        return node.element == null || element.compareTo(node.element) < 0;
    }

    private boolean isNeedAdopt(Node<T> node){
        return isLeafNode(node) && getLengthLeafNode(node) < (l + 1) / 2;
    }

    /**
     * 每一节点领养其下一邻近节点首元素
     * 尾部节点领养其上一邻近节点尾元素（因为尾部节点没有下一邻近节点）
     * */
    private void adoptForFirstChild(Node<T> parent) {
        // 尾部节点
        if (parent.getClass() == BoundNode.class) {
            Node<T> prev = ((BoundNode<T>) parent).prevSibling;
            if (isCanBeAdopted(prev.firstChild)) {
                T toBeAdoptElement = removeTail(parent.firstChild);
                parent.element = toBeAdoptElement;
                appstart(parent.firstChild, toBeAdoptElement);
            }
            // 合并
            else {
                Node<T> prevF = prev.firstChild;
                while (prevF.nextSibling.element!=null){
                    prevF = prevF.nextSibling;
                }
                prevF.nextSibling = parent.firstChild;

            }
        }
        // 非尾部节点
        else {
            if (isCanBeAdopted(parent.nextSibling.firstChild)) {
                T toBeAdoptElement = removeHead(parent.nextSibling.firstChild);
                parent.element = parent.nextSibling.firstChild.element;
                append(parent.firstChild, toBeAdoptElement);
            }
            // 合并
            else {

            }

        }
    }

    private boolean isCanBeAdopted(Node<T> node) {
        return getLengthLeafNode(node) > (l + 1) / 2;
    }

    /**
     * 移除尾节点并返回尾节点中元素
     * 注意最后一个节点为边界标记节点，尾节点是边界节点前一节点
     */
    private T removeTail(Node<T> leafHead) {
        while (leafHead.nextSibling.element!=null){
            leafHead = leafHead.nextSibling;
        }
        T element = leafHead.element;
        leafHead.nextSibling = null;
        leafHead.element = null;
        return element;
    }

    private void appstart(Node<T> node,T element){
        node.nextSibling = new Node<T>(node.element,node.firstChild,node.nextSibling);
        node.element = element;
    }

    private void append(Node<T> node, T element) {
        Node<T> pointer = node;
        while (pointer.element != null) {
            pointer = pointer.nextSibling;
        }
        pointer.element = element;
        pointer.nextSibling = new Node<>(null, null, null);
    }

    /**
     * 移除首节点并返回首节点中元素
     * 不可以传入尾节点
     */
    private T removeHead(Node<T> leafHead) {
        T element = leafHead.element;
        leafHead.element = leafHead.nextSibling.element;
        leafHead.nextSibling = leafHead.nextSibling.nextSibling;
        return element;
    }

    static class Node<T extends Comparable<T>> {
        T element;
        Node<T> firstChild;
        Node<T> nextSibling;
        // 此字段用于简化领养和合并过程的编程难度
        Node<T> prevSibling;

        Node(T element, Node<T> firstChild, Node<T> prevSibling, Node<T> nextSibling) {
            this.element = element;
            this.firstChild = firstChild;
            this.prevSibling = prevSibling;
            this.nextSibling = nextSibling;
        }
        NodeType getType(){
            if(prevSibling==null){
                return NodeType.Head;
            }else if (nextSibling==null){
                return NodeType.Tail;
            }else {
                return NodeType.Middle;
            }
        }

        boolean is

        private enum NodeType{
            Head,Tail,Middle
        }
    }

}
