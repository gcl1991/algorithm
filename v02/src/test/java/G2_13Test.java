import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class G2_13Test {
    private long startTime;
    private int a;
    private int x;
    private int n;
    {
        a=1;
        x=2;
        n=10000000;
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
    public void simpleFTest (){
        System.out.println(G2_13.simpleF(a,x,n));
    }

    @Test
    public void recursiveFTest(){
        System.out.println(G2_13.recursiveF(a,x,n));
    }

    @Test // 数字过大会造成越界
    public void powTest (){
        System.out.println(G2_13.pow(x,n));
    }
}
