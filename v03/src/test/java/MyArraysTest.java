import content.MyArrays;
import org.junit.Test;

import javax.print.DocFlavor;

public class MyArraysTest {
    @Test
    public void copyOfTest1(){
        Number[] u = new Number[10];
//        u[1] = 1;
        String[] t = MyArrays.copyOf(u,u.length,String[].class);
        t[0] = "1";
        System.out.println(t[0]);
    }

    @Test
    public void copyOfTest2(){
        Number[] u = new Number[10];
        u[0] = 1;
        String[] t = MyArrays.copyOf(u,u.length,String[].class);
    }

    @Test public void copyOfTest3(){
        String x = "123";
        Integer y = 123;
        boolean b1 = x == (Object) y;
        boolean b2 = (Object) x == (Object) y;
        boolean b3 = x.equals(y);
//        boolean b3 = x == y; // error
        boolean b4 = String.class.equals(Integer.class);
        boolean b5 = String[].class.equals(Integer.class);
        boolean b6 = Integer.class.equals(String.class);
    }

    @Test public void copyOfTest4(){
        Integer[] t = (Integer[]) new Object[10];
    }

    @Test public void copyOfTest5(){
        String[] s = {"1","2"};
        MyArrays.copyOf(s,s.length,s.getClass());
    }
}
