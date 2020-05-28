package Exercises;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;

public class E3_21Test {
    @Test(expected = AssertionError.class)
    public void test() throws IOException {
        E3_21 scanner = new E3_21(new String[]{"/*", "(","[","{"},new String[]{"*/",")","]","}"});
        String userDir = System.getProperty("user.dir");
        scanner.scanningFile(userDir+"\\src\\test\\resources\\test.txt");
    }
}
