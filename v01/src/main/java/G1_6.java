import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class G1_6 {
    public static void permute (String str){
        permute(str.toCharArray(),"",0,str.length());
    }

    public static void permute (char[] str,String suffix,int low, int high){
        if(low == high){
            System.out.println(suffix);
        }
        for (int i = 0;i < str.length; i++){
            char[] subStr = removeChar(str,i);
            String subSuffix = suffix+str[i];
            permute(subStr,subSuffix,0,subStr.length);
        }
    }

    public static char[] removeChar(char[] array,int index){
        char[] newArray = new char[array.length-1];
        int i = 0;
        for (char element:array){
            if (element != array[index]) {
                newArray[i] = element;
                i++;
            }
        }
        return newArray;
    }
}
