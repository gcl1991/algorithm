package Extended;

import java.util.Arrays;

public class KMP {
    public static void main(String[] args) {
        String t = "aaaaabb";
        int[] next = new int[t.length()];
        getNext(t,next);
        System.out.println(Arrays.toString(next));
    }
    static void getNext(String patten,int[] next){
        int pattenIndex, maxValue;
        pattenIndex=0; maxValue=-1; next[0]=-1;
        while (pattenIndex < patten.length()-1){
            try {
                System.out.println("比较"+"位置"+pattenIndex+"值"+patten.charAt(pattenIndex)+"和"+"位置"+maxValue+"值"+patten.charAt(maxValue));
            }
            catch (StringIndexOutOfBoundsException e){
                System.out.println("数组越界:"+"位置"+pattenIndex+"和"+"位置"+maxValue);
            }
            if (maxValue == -1 || patten.charAt(pattenIndex) == patten.charAt(maxValue)){
                pattenIndex++; maxValue++;
                next[pattenIndex] = maxValue;
                System.out.println("next位置"+pattenIndex+"被设置为值"+maxValue);
            }
            else {
                System.out.println("从next中位置"+maxValue+"取出值"+next[maxValue]);
                maxValue = next[maxValue];
            };
        }
    }
}
