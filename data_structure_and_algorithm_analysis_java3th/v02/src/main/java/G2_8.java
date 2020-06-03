import java.util.Random;

public class G2_8 {

    public static int[] problem1 (int n){
        Random random = new Random();
        int[] array = new int[n];
        array[0] = random.nextInt(n) + 1;
        for (int i = 1; i < n; i++){
            while (true){
                int nextRandomInt = random.nextInt(n) + 1;
                boolean flag = true;

                for (int j=0; j < i; j++){
                    if (nextRandomInt == array[j]){
                        flag = false;
                    }
                }

                if (flag){
                    array[i] = nextRandomInt;
                    break;
                }
            }
        }
        return array;
    }

    public static int[] problem2 (int n){
        Random random = new Random();
        int[] array = new int[n];
        boolean[] used = new boolean[n];

        for (int i = 0; i < n; i++){
            while (true){
                int nextRandomInt = random.nextInt(n) + 1;
                if (!used[nextRandomInt-1]){
                    array[i] = nextRandomInt;
                    used[nextRandomInt-1]=true;
                    break;
                }
            }
        }
        return array;
    }

    public static int[] problem3 (int n){
        Random random = new Random();
        int[] array = new int[n];

        for (int i = 0; i < n; i++){
            array[i] = i+1;
        }

        for(int i=1; i < n; i++){
            int swapIndex = random.nextInt(i);
            int temp = array[i];
            array[i] = array[swapIndex];
            array[swapIndex] = temp;
        }
        return array;
    }

}
