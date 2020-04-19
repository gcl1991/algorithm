import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;

interface IntArrayToIntFunction {
    int apply(int[] array);
}

public class G2_17MinSumRecPositive {
    public static int minSumRec_N3(int[] array) {
        return minSumRec(array, G2_17MinSumRecPositive::getMinSum_N3);
    }

    public static int minSumRec(int[] array, IntArrayToIntFunction getMinSum) {
        if (isAllPositive(array))
            return minValue(array); // 快速检测，如果数组为正整数数组，直接返回最小值作为最小子序和即可。
        return getMinSum.apply(array);
    }

    public static boolean isAllPositive(int[] array) {
        int minValue = minValue(array);
        return minValue > 0; // 快速检测，如果数组为正整数数组，直接返回最小值作为最小子序和即可。
    }

    public static int minValue(int[] array) {
        return IntStream.of(array).reduce(Integer::min).getAsInt();
    }


    public static int getMinSum_N3(int[] array) {
        int minSum = Integer.MAX_VALUE;
        int length = array.length;
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                int thisSum = getArraySum(array, i, j);
                if (isMinSumPositive(thisSum, minSum))
                    minSum = thisSum;
            }
        }
        return minSum;
    }

    /**
     * ！求和值包含末尾索引
     */
    public static int getArraySum(int[] array, int startIndex, int endIndex) {
        int sum = IntStream.rangeClosed(startIndex + 1, endIndex)
                .reduce(array[startIndex], (lastSum, index) -> lastSum + array[index]);
        return sum;
    }

    public static boolean isMinSumPositive(int currentSum, int lastMinSum) {
        return currentSum < lastMinSum && currentSum > 0;
    }

    public static int minSumRec_N2(int[] array) {
        return minSumRec(array, G2_17MinSumRecPositive::getMinSum_N2);
    }

    public static int getMinSum_N2(int[] array) {
        int minSum = Integer.MAX_VALUE;
        int length = array.length;
        for (int i = 0; i < length; i++) {
            int thisSum = 0;
            for (int j = i; j < length; j++) {
                thisSum += array[j];
                if (isMinSumPositive(thisSum, minSum))
                    minSum = thisSum;
            }
        }
        return minSum;

    }

    public static int minSumRec_NlogN(int[] array) {
        return minSumRec(array, G2_17MinSumRecPositive::getMinSum_NlogN);
    }

    public static int getMinSum_NlogN(int[] array) {
        return getMinSumExecute_NlogN(array, 0, array.length - 1);
    }

    public static int getMinSumExecute_NlogN(int[] array, int left, int right) {
        if (left == right)
            return Integer.max(array[left], 0);
        int center = (left + right) / 2;
        int leftMinSumPositive = getMinSumExecute_NlogN(array, left, center);
        int rightMinSumPositive = getMinSumExecute_NlogN(array, center + 1, right);
        int centerMinSumPositive = getMinCenterSumPositive(array, left, center, right);

        return IntStream.of(leftMinSumPositive, centerMinSumPositive, rightMinSumPositive).filter(a -> a > 0).min().getAsInt();
    }

    public static int getMinCenterSumPositive(int[] array, int left, int center, int right) {
        ValueAndIndex leftBorderPair = createMinValueAndIndexPair(array, left, center);
        int leftBorderMinSum = leftBorderPair.getValue();
        int leftIndex = leftBorderPair.getIndex();

        ValueAndIndex rightBorderPair = createMinValueAndIndexPair(array, center + 1, right);
        int rightBorderMinSum = rightBorderPair.getValue();
        int rightIndex = rightBorderPair.getIndex();

        int centerMinSum = leftBorderMinSum + rightBorderMinSum;
        int centerMinSumPositive = scanMinSumPositive(array, centerMinSum, leftIndex, rightIndex, left, right);

        return centerMinSumPositive;
    }

    public static ValueAndIndex createMinValueAndIndexPair(int[] array, int startIndex, int endIndex) {
        int minSum = Integer.MAX_VALUE, currentSum = 0;
        int index = 0;
        for (int i = startIndex; i <= endIndex; i++) {
            currentSum += array[i];
            if (currentSum < minSum) {
                minSum = currentSum;
                index = i;
            }
        }
        return new ValueAndIndex(minSum, index);
    }

    private static class ValueAndIndex {
        private int value;
        private int index;

        public ValueAndIndex(int value, int index) {
            this.value = value;
            this.index = index;
        }

        public int getValue() {
            return value;
        }

        public int getIndex() {
            return index;
        }
    }

    public static int scanMinSumPositive(int[] array, int centerMinSum, int leftIndex, int rightIndex, int left, int right) {
        while (notIsMinSumPositive(centerMinSum, leftIndex, rightIndex, left, right)) {
            if (bothHasValue(leftIndex, rightIndex, left, right)) {
                if (array[leftIndex - 1] <= array[rightIndex + 1]) {
                    centerMinSum += array[--leftIndex];
                } else {
                    centerMinSum += array[++rightIndex];
                }
            } else if (onlyLeftHasValue(leftIndex, left)) {
                centerMinSum += array[--leftIndex];
            } else {// only right has value
                centerMinSum += array[++rightIndex];
            }
        }
        return centerMinSum;
    }

    public static boolean notIsMinSumPositive(int sum, int leftIndex, int rightIndex, int left, int right) {
        return sum <= 0 && (leftIndex > left || rightIndex < right);
    }

    public static boolean bothHasValue(int leftIndex, int rightIndex, int left, int right) {
        return leftIndex > left && rightIndex > right;
    }

    public static boolean onlyLeftHasValue(int leftIndex, int left) {
        return leftIndex > left;
    }

    public static int minSumRec_N(int[] array) {
        return minSumRec(array, G2_17MinSumRecPositive::getMinSum_N);
    }

    public static int getMinSum_N(int[] array) {
        int minSumPositive = Integer.MAX_VALUE;
        int currentSum = 0;
        for (int value : array) {
            currentSum += value;
            if (currentSum > 0 && currentSum < minSumPositive) {
                minSumPositive = currentSum;
            }
            if (currentSum > 0) {
                currentSum = 0;
            }
        }
        return minSumPositive;
    }

}
