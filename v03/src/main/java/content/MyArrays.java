package content;

import java.lang.reflect.Array;

public class MyArrays {
    public static <T,U> T[] copyOf(U[] original, int newLength, Class<? extends T[]> newType) {
        @SuppressWarnings("unchecked")
        T[] copy = ((Object)newType == (Object)Object[].class) // 不同类型对象之间不可用==判断
                ? (T[]) new Object[newLength]
                // 应该有类型转换错误，不过由于类型擦除T[]为Object[]所以没关系,为什么要做这个判断，因为Array.newInstance代价较高吗？？?
                : (T[]) Array.newInstance(newType.getComponentType(), newLength);
        System.arraycopy(original, 0, copy, 0,
                Math.min(original.length, newLength));
        return copy;
    }
}
