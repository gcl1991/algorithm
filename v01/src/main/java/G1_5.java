public class G1_5 {
    public static int getNumberOneBinary (int number){
        if (number == 1){
            return 1;
        }
        else if(number%2 == 0){
            return getNumberOneBinary(number/2);
        }
        else {
            return getNumberOneBinary(number/2) + 1;
        }
    }
}
