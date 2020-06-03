import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.function.IntConsumer;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;

public class G2_8Test {
    private long startTime;
    private int n = 5;

    public int[] createBaseArray (int n){
        int[] baseArray = new int[n];
        for (int i=0;i<n;i++){
            baseArray[i] = i+1;
        }
        return baseArray;
    }

    @Before
    public void setStartTime(){
        startTime = new Date().getTime();
    }

    @After
    public void printRunTime(){
        long endTime = new Date().getTime();
        System.out.printf("RunTime:%s ms\n", endTime-startTime);
    }

    @Test
    public void repeatProblem1 (){
        int[] ns = {250, 500, 1000, 2000};
        int numberRepeat = 10;
        repeatExecute(ns, numberRepeat, G2_8::problem1);
    }

    public <T> void repeatExecute(int[] array, int numberRepeat, IntConsumer intConsumer){
        for (int n : array) {
            long startTime = new Date().getTime();
            for(int i=0; i<numberRepeat; i++) {
                intConsumer.accept(n);
            }
            long endTime = new Date().getTime();
            System.out.printf("%s avg runtime:%s ms\n",n,(endTime-startTime)/numberRepeat);
        }
    }

    @Test
    public void problem1 (){
        int[] testArray = G2_8.problem1(n);
        int[] sortTestArray = testArray.clone();
        int[] baseArray = createBaseArray(n);

        Arrays.sort(sortTestArray);

        assertArrayEquals(sortTestArray, baseArray);
        assertFalse(Arrays.equals(sortTestArray, testArray));
    }

    @Test
    public void repeatProblem2 (){
        int[] ns = {25_000, 50_000, 100_000, 200_000, 400_000, 800_000};
        int numberRepeat = 10;
        repeatExecute(ns, numberRepeat, G2_8::problem2);
    }

    @Test
    public void problem2 (){
        int[] testArray = G2_8.problem2(n);
        int[] sortTestArray = testArray.clone();
        int[] baseArray = createBaseArray(n);

        Arrays.sort(sortTestArray);

        assertArrayEquals(sortTestArray, baseArray);
        assertFalse(Arrays.equals(sortTestArray, testArray));
    }

    @Test
    public void repeatProblem3 (){
        int[] ns = {100_000,200_000,400_000,800_000,1_600_000,3_200_000,6_400_000};
        int numberRepeat = 10;
        repeatExecute(ns,numberRepeat,G2_8::problem3);
    }

    @Test
    public void problem3 (){
        int[] testArray = G2_8.problem3(n);
        int[] sortTestArray = testArray.clone();
        int[] baseArray = createBaseArray(n);

        Arrays.sort(sortTestArray);

        assertArrayEquals(sortTestArray, baseArray);
        assertFalse(Arrays.equals(sortTestArray, testArray));
    }
}
