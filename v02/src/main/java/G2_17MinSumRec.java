import java.util.Arrays;

public class G2_17MinSumRec {
    public static void argsCheck(int[] array, int left, int right) {
        if (array == null) throw new IllegalArgumentException();
        if (left > right) throw new IllegalArgumentException();
        if (left < 0) throw new ArrayIndexOutOfBoundsException();
        if (right >= array.length) throw new ArrayIndexOutOfBoundsException();
    }

    public static int minSumRec_N3(int[] array, int left, int right) {
        argsCheck(array, left, right);

        int minValue = minValue(array, left, right);
        if (minValue >= 0) return minValue; // 快速检测，如果数组为正整数数组，直接返回最小值作为最小子序和即可。

        int minSum = 0;
        for (int i = left; i <= right; i++) {
            for (int j = i; j <= right; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++)
                    sum += array[k];
                if (sum < minSum)
                    minSum = sum;
            }
        }
        return minSum;
    }

    public static int minValue(int[] array, int left, int right) {
        int minValue = array[left];
        for (int i = left + 1; i <= right; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
            }
        }
        return minValue;
    }

    public static int minSumRec_N2(int[] array, int left, int right) {
        argsCheck(array, left, right);

        int minValue = minValue(array, left, right);
        if (minValue >= 0) return minValue;

        int minSum = 0;
        for (int i = left; i <= right; i++) {
            int sum = 0;
            for (int j = i; j <= right; j++) {
                sum += array[j];
                if (sum < minSum)
                    minSum = sum;
            }
        }
        return minSum;
    }

    public static int minSumRec_NlogN(int[] array, int left, int right) {
        argsCheck(array, left, right); // 避免递归中消耗时间在检查参数上，以提高效率。

        int minValue = minValue(array, left, right);
        if (minValue >= 0) return minValue;

        return minSumRec_NlogN_execute(array, left, right);
    }

    public static int minSumRec_NlogN_execute(int[] array, int left, int right) {
        if (left == right)
            return array[left];

        int center = (left + right) / 2;
        int leftMinSum = minSumRec_NlogN_execute(array, left, center);// include center
        int rightMinSum = minSumRec_NlogN_execute(array, center + 1, right);

        int leftBorderMinSum = 0, leftBorderSum = 0;
        for (int i = center; i >= left; i--) {
            leftBorderSum += array[i];
            if (leftBorderSum < leftBorderMinSum)
                leftBorderMinSum = leftBorderSum;
        }

        int rightBorderMinSum = 0, rightBorderSum = 0;
        for (int i = center+1; i <= right; i++) {
            rightBorderSum += array[i];
            if (rightBorderSum < rightBorderMinSum)
                rightBorderMinSum = rightBorderSum;
        }

        return minValue(leftBorderMinSum + rightBorderMinSum, leftMinSum, rightMinSum);
    }

    public static int minValue(int... x) {
        return minValue(x, 0, x.length-1);
    }

    public static int minSumRec_N(int[] array, int left, int right) {
        argsCheck(array, left, right);

        int minValue = minValue(array, left, right);
        if (minValue >= 0) return minValue;

        int minSum = 0, thisSum = 0;
        for (int i = left; i <= right; i++) {
            thisSum += array[i];
            if (thisSum > 0)
                thisSum = 0;
            else if (thisSum < minSum)
                minSum = thisSum;
        }

        return minSum;
    }
}
