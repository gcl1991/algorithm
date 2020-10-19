package exercises;

import java.util.Objects;

import static content.BinarySearchTree.BinaryNode;

class E47 {
    /**
     * 前置条件
     * 后验条件
     *
     * 异常
     * */
    static <T> boolean isIsomorphismTree(BinaryNode<T> tree1, BinaryNode<T> tree2) {
        if(tree1 == tree2){
            return true;
        }else if(tree1 == null || tree2==null){
            return false;
        }else {
            boolean isSame = Objects.equals(tree1,tree2);
            boolean isSameSubNode = Objects.equals(tree1.left, tree2.left)
                    && Objects.equals(tree1.right, tree2.right);
            boolean isIsomorphismSubNode = Objects.equals(tree1.left,tree2.right)
                    && Objects.equals(tree1.right,tree2.left);
            // 递归下一层
            if (!isSame){
                return false;
            }else if(isSameSubNode){
                return isIsomorphismTree(tree1.left,tree2.left)
                        && isIsomorphismTree(tree1.right,tree2.right);
            }else if (isIsomorphismSubNode){
                return isIsomorphismTree(tree1.left,tree2.right)
                        && isIsomorphismTree(tree1.right,tree2.left);
            }else {
                return false;
            }
        }
    }
}
