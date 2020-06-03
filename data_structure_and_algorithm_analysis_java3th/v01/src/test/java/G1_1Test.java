import java.util.*;
import java.util.function.UnaryOperator;
import java.lang.Math;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class G1_1Test {
    private long startTime;
    private Integer[][] testArray;

    public G1_1Test(){
        testArray = getTestArray();
    }

    public Integer[][] getTestArray(){
        int minLength = 10;
        int maxLength = 10000;
        int numArray = (int) Math.log10(maxLength);
        Integer[][] testArray = new Integer[numArray][];
        for (int i = minLength; i <= maxLength; i *= minLength) {
            Integer[] array = RandomIntegerArray(i);
            int index = (int) Math.log10(i) -1;
            testArray[index] = array;
        }
        return testArray;
    }

    @Test
    public void insertSortTest() {
        for (Integer[] array: testArray) {
            int actual = array.length/2-1;
            long t1 = new Date().getTime();
            int expected = getKthValue(array, G1_1.ArraySort::insertSort);
            long t2 = new Date().getTime();
            assertEquals(expected,actual);
            System.out.printf("array length is: %1$s; run time is:%2$s ms;\n", array.length, t2 - t1);
        }
    }

    @Test
    public void selectSortTest() {
        for (Integer[] array: testArray) {
            int actual = array.length/2-1;
            long t1 = new Date().getTime();
            int expected = getKthValue(array, G1_1.ArraySort::selectSort);
            long t2 = new Date().getTime();
            assertEquals(expected,actual);
            System.out.printf("array length is: %1$s; run time is:%2$s ms;\n", array.length, t2 - t1);
        }
    }

    @Test
    public void swapSortTest() {
        for (Integer[] array: testArray) {
            int actual = array.length/2-1;
            long t1 = new Date().getTime();
            int expected = getKthValue(array, G1_1.ArraySort::swapSort);
            long t2 = new Date().getTime();
            assertEquals(expected,actual);
            System.out.printf("array length is: %1$s; run time is:%2$s ms;\n", array.length, t2 - t1);
        }
    }

    @Test
    public void fastSortTest() {
        for (Integer[] array: testArray) {
            int actual = array.length/2-1;
            long t1 = new Date().getTime();
            int k = array.length/2-1;
            Integer[] readySortArray = G1_1.ArraySort.fastSort(array,0, array.length);
            int expected = readySortArray[k];
            long t2 = new Date().getTime();
            assertEquals(expected,actual);
            System.out.printf("array length is: %1$s; run time is:%2$s ms;\n", array.length, t2 - t1);
        }
    }

    @Test
    public void mergeSortTest() {
        for (Integer[] array: testArray) {
            int actual = array.length/2-1;
            long t1 = new Date().getTime();
            int k = array.length/2-1;
            Integer[] readySortArray = G1_1.ArraySort.mergeSort(array, Integer[]::new);
            int expected = readySortArray[k];
            long t2 = new Date().getTime();
            assertEquals(expected,actual);
            System.out.printf("array length is: %1$s; run time is:%2$s ms;\n", array.length, t2 - t1);
        }
    }

    public Integer[] RandomIntegerArray(int length){
        Integer[] array = new Integer[length];
        for (int i=0; i<length; i++){
            array[i] = i;
        }
        List<Integer> arrayList = Arrays.asList(array);
        Collections.shuffle(arrayList);
        return array;
    };

    public <T extends Comparable<T>> T getKthValue(T[] arrayGenericToBeSort, UnaryOperator<T[]> sort){
        int k = arrayGenericToBeSort.length/2-1;
        T[] readySortArray = sort.apply(arrayGenericToBeSort);
        return readySortArray[k];
    }

    @Before
    public void timeStart(){
        this.startTime = new Date().getTime();
    }

    @After
    public void printRuntime(){
        long endTime = new Date().getTime();
        long runTime = endTime - startTime;
        System.out.printf("run all time is: %s ms\n", runTime);
    }
}
