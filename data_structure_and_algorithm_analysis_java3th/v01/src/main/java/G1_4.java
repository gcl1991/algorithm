/*
* 实现C语言include程序预处理
* */

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 分为两步处理，1将所有# include所指文件递归分层读入数组 2 从数组中依次读取所有文件内容写入最终文件
 * 读取操作将include看成多叉树结构，采用广度优先和深度优先两类方式进行处理。
 * 其中深度优先包括三种方式：先序遍历，中序遍历，后序遍历
 * */
public class G1_4 {
    public static void readCodeFile (Path codeFile,String dirname) throws IOException {
        String[] content = TxtFile.readLines(codeFile);
        for (String line:content){
            if (line.startsWith("# include")){
                String filename = line.replace("# include","").trim();
                Path subFile = Paths.get(dirname,filename);
                readCodeFile(subFile,dirname);
            }
            else {
                System.out.println(line);
            }
        }
    }
}
