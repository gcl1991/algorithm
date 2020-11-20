package exercises;

public class E14 {
    public static int findSubstringStartIndex(String string, String substring) {
        // 1 初始化参数
        int n = string.length();
        int k = substring.length();
        // 2 计算待匹配字串哈希值
        int targetHashCode = hashCode(substring);
        // 3 for遍历n-k+1个字串
        for (int i = 0; i < n-k+1; i++) {
            // (1) 计算字串哈希
            String tmpString = string.substring(i, i+k);
            int tmpHashCode = hashCode(tmpString);
            // （2）if/else字符串相等性判断
            if((tmpHashCode == targetHashCode) && StringEquals(tmpString,substring)){
                    return i;
            }
        }
        return -1;
    }

    private static int hashCode(String string) {
        int hashVal = 0;
        int length = string.length();
        // 1 计算字符串哈希:霍纳法则
        for (int i = 0; i < length; i++) {
            hashVal = 31 * hashVal + string.charAt(i);
        }
        return hashVal;
    }

    private static boolean StringEquals(String s1,String s2){
        assert s1.length()==s2.length():"字符串长度必须相等";
        for(int j=0;j<s1.length();j++){
            if (s1.charAt(j) != s2.charAt(j)){
                return false;
            }
        }
        return true;
    }
}
