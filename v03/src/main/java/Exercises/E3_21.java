package Exercises;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Paths;
import java.util.*;

public class E3_21 {
    private Map<String, String> pairSymbolsC2O;
    private Set<String> openSymbols;
    private Set<String> closeSymbols;
    private Stack<String> symbolsRecord;

    public E3_21(String[] openSymbols, String[] closeSymbols) {
        assert openSymbols.length == closeSymbols.length : "符号必须成对出现";
        pairSymbolsC2O = new HashMap<>();
        for (int idx = 0; idx < openSymbols.length; idx++) {
            pairSymbolsC2O.put(closeSymbols[idx], openSymbols[idx]);
        }
        symbolsRecord = new Stack<>();
        this.openSymbols = new HashSet<>();
        this.openSymbols.addAll(Arrays.asList(openSymbols));
        this.closeSymbols = new HashSet<>();
        this.closeSymbols.addAll(Arrays.asList(closeSymbols));
    }

    // 无法处理/**/这种多字符情况
    public void scanningFile(String file) throws IOException {
        System.out.println(file);
        Scanner in = new Scanner(Paths.get(file), "utf-8");
        int numberRow = 0;
        while (in.hasNextLine()) {
            numberRow++;
            int numberColumn = 0;
            String line = in.nextLine();
            for (String symbol : line.split("")) {
                numberColumn++;
                if (openSymbols.contains(symbol)) {
                    symbolsRecord.push(symbol);
                } else if (closeSymbols.contains(symbol)) {
                    String actualOpenSymbol = symbolsRecord.pop();
                    String expectedOpenSymbol = pairSymbolsC2O.get(symbol);
                    assert actualOpenSymbol.equals(expectedOpenSymbol) :
                            String.format("第%s行，第%s列符号%s配对错误,期望匹配%s,实际匹配%s",
                                    numberRow, numberColumn, symbol,expectedOpenSymbol,actualOpenSymbol);
                }
            }
        }
    }
}
