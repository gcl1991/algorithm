/*
* 递归打印整数和浮点数各位数字
* */
import java.math.BigDecimal;
import java.math.RoundingMode;

public class G1_3 {
    public static void printInt (int digit){
        // 我的实现
//        if (digit < 10){
//            System.out.println(digit);
//        }
//        else {
//            printDigit(digit/10);
//            System.out.println(digit%10);
//        }
        // 答案，更简洁
        byte CARDINAL_NUMBER = 10;
        if (digit >= CARDINAL_NUMBER) printInt(digit / CARDINAL_NUMBER);
        System.out.println(digit % CARDINAL_NUMBER);
    }

    public static void printDouble (double digit){
        printInt(doubleToInt(digit));
    }

    public static int doubleToInt(double digit){
        float CARDINAL_NUMBER = 0.1F;
        int result;
        if (BigDecimal.valueOf(digit).subtract(BigDecimal.valueOf((int) digit)).doubleValue() != 0){
            result = doubleToInt(BigDecimal.valueOf(digit).divide(BigDecimal.valueOf(CARDINAL_NUMBER), RoundingMode.HALF_UP).doubleValue());
        }
        else {
            result = (int) digit;
        }
        return result;
    }
}
