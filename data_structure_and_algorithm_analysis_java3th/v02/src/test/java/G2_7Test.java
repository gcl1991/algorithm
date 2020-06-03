import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class G2_7Test {
    private long n;
    private long startTime;
    public G2_7Test(){
        this.n = 128;
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
    public void a1 (){
        G2_7.a1(n);
    }

    @Test
    public void a2 (){
        G2_7.a2(n);
    }

    @Test
    public void a3 (){
        G2_7.a3(n);
    }

    @Test
    public void a4(){
        G2_7.a4(n);
    }

    @Test
    public void a5 (){
        G2_7.a5(n);
    }

    @Test
    public void a6 (){
        G2_7.a6(n);
    }
}
