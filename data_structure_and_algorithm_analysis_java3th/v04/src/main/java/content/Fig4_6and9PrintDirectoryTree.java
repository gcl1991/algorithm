package content;

import java.io.File;

import static Helpers.StringHelpers.stringMultiplication;

// dir file dirname filename path
public class Fig4_6and9PrintDirectoryTree {
    public static void listAll(int depth, String fileString) {
        File file = new File(fileString);
        System.out.println(stringMultiplication("\t", depth) + file.getName());
        if (file.isDirectory()) {
            for (String subFile : file.list()) {
                String subFileString = String.join("/",fileString,subFile);
                listAll(depth + 1, subFileString);
            }
        }
    }

    public static long sizeAll(int depth,String pathString){
        File file = new File(pathString);
        long totalSize =  file.length();
        if (file.isDirectory()) {
            for (String subFile : file.list()) {
                String subFileString = String.join("/",pathString,subFile);
                totalSize+=sizeAll(depth + 1, subFileString);
            }
        }
        System.out.printf("%s%s[%sByte]\n",stringMultiplication("\t",depth),file.getName(),totalSize);
        return totalSize;
    }
}
