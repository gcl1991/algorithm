package exercises;

import org.junit.Test;

public class E35Test {
    @Test
    public void testBuild(){
        int h=5;
        int arrayLength = E18.analyticalSolution(h);
        Integer[] array = new Integer[arrayLength];
        for (int i=0;i<arrayLength;i++){
            array[i]=i;
        }
        E35.build(h,array); // h必须与数组长度匹配，数组必须从小到大排好序
    }
}
