/*
* 实现txt文件常用读写操作
* */

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

class TxtFile{
    public static void printInfo (){
        System.out.printf("search path is: %s \n",System.getProperty("user.dir"));
    }

    public static String[] readLines(Path txtFile) throws IOException {
        ArrayList<String> text = new ArrayList<>();
        Scanner inputFileRead = new Scanner(txtFile,"UTF-8");
        while (inputFileRead.hasNext()){
            text.add(inputFileRead.nextLine());
        }
        String[] result = new String[text.size()];
        return text.toArray(result);
    }
}
