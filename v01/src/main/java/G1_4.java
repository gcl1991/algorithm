/*
* 实现C语言include程序预处理
* */

/**
 * 分为两步处理，1将所有# include所指文件递归分层读入数组 2 从数组中依次读取所有文件内容写入最终文件
 * 读取操作将include看成多叉树结构，采用广度优先和深度优先两类方式进行处理。
 * 其中深度优先包括三种方式：先序遍历，中序遍历，后序遍历
 * */
public class G1_4 {

}