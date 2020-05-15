import content.MyArrayList;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MyArrayListTest {
    @Test
    public void myArrayListTest(){
        MyArrayList<Integer> myArrayList= new MyArrayList<>();
        for (int i=0;i<30;i++){
            myArrayList.add(i); // 自动装箱
            System.out.println(myArrayList.size());
        }

    }
}
