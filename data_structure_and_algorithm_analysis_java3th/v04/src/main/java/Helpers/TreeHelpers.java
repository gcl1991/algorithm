package Helpers;

import static content.BinarySearchTree.BinaryNode;

public class TreeHelpers {
    public static <T> void printTreeByPreorder(BinaryNode<T> t){
        printTreeByPreorder(t,0,"root");
    }
    private static <T> void printTreeByPreorder(BinaryNode<T> t,int level,String prefix) {
        if (t != null) {
            System.out.println(StringHelpers.stringMultiplication("\t", level)+prefix+":"+t.element);
            printTreeByPreorder(t.left,level+1,"l");
            printTreeByPreorder(t.right,level+1,"r");
        }
    }
}
