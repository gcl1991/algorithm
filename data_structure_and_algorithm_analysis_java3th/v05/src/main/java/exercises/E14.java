package exercises;

public class E14 {
    public static int findSubstringStartIndex(String string, String substring) {
        final int BASE = 31;
        // 1 定义权重常数
        // 2 计算待匹配字串哈希值
        // 3 外层循环：遍历n-k+1个字串
        //   3.1 内层顺序：
        //      (1) 计算字串哈希
        //      (2) 分支判断：判断哈希是否相同
        //          1) 如果相同计算字符串是否相等
        //              分支判断：如果相等结束函数计算，返回该字串起始索引
        //                      如果不相等，
    }
}
