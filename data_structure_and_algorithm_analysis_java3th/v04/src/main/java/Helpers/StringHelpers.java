package Helpers;

public class StringHelpers {
    private StringHelpers()  {
        throw new UnsupportedOperationException("工具类禁止实例化");
    }

    public static String stringMultiplication(String string, int digit) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < digit; i++) {
            stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }
}
