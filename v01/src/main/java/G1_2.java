/*
*求解猜字谜游戏
* */
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class G1_2 {
    public static boolean isExistWord(String roBeCheckWord, Path alphabetFile) throws IOException {
        String[][] txt = readTextFile2Array(alphabetFile);
        System.out.printf("read alphabet is :%s\n",Arrays.deepToString(txt));
        ArrayList<String> maybeWords = getMaybeWords(txt);
        return maybeWords.contains(roBeCheckWord);
    }

    public static String[][] readTextFile2Array(Path txtFile) throws IOException {
        String DELIMITER = ",";
        String[] lines = TxtFile.readLines(txtFile);
        String[][] result = new String[lines.length][];
        for (int rowNum = 0; rowNum < lines.length; rowNum++){
            result[rowNum] = lines[rowNum].split(DELIMITER);
        }
        return result;
    }

    public static ArrayList<String> getMaybeWords(String[][] text){
        ArrayList<String> maybeWords = new ArrayList<>();
        maybeWords.addAll(checkRows(text));
        maybeWords.addAll(checkColumns(text));
        maybeWords.addAll(checkFrontDiagonal(text));
        maybeWords.addAll(checkBackDiagonal(text));
        return maybeWords;
    }

    public static ArrayList<String> checkRows(String[][] text){
        ArrayList<String> maybeWords = new ArrayList<>();
        for (int i=0; i<text.length; i++){
            maybeWords.addAll(checkRow(text[i]));
        }
        return maybeWords;
    }

    public static ArrayList<String> checkRow(String[] aline){
        ArrayList<String> maybeWords = new ArrayList<>();
        for (int start = 0; start < aline.length; start++){
            for (int i = start; i < aline.length; i++){
                String maybeWord = String.join("",Arrays.copyOfRange(aline, start, i+1));
                maybeWords.add(maybeWord);
            }
        }
        return maybeWords;
    }

    public static ArrayList<String> checkColumns(String[][] text){
        text = Matrix.TransposeSquareMatrix(text);
        return checkRows(text);
    }

    public static ArrayList<String> checkFrontDiagonal(String[][] text){
        ArrayList<String[]> rows = new ArrayList<>();
        for (int num = 0; num < text.length; num++){
            ArrayList<String> diagonalAlphabet= new ArrayList<>();
            for (int rowNum = num, columnNum = 0; rowNum >= 0 && columnNum < text.length; rowNum--, columnNum++){
                diagonalAlphabet.add(text[rowNum][columnNum]);
            }
            rows.add(diagonalAlphabet.toArray(new String[0]));

            if (num + 1 < text.length){
                diagonalAlphabet= new ArrayList<>();
                for (int rowNum = text.length - 1, columnNum = num + 1; rowNum >= 0 && columnNum < text.length; rowNum--, columnNum++){
                    diagonalAlphabet.add(text[rowNum][columnNum]);
                }
                rows.add(diagonalAlphabet.toArray(new String[0]));
            }
        }
        return checkRows(rows.toArray(new String[text.length][0]));
    }

    public static ArrayList<String> checkBackDiagonal(String[][] text){
        return checkFrontDiagonal(Matrix.leftRotate90Degrees(text));
    }

}


