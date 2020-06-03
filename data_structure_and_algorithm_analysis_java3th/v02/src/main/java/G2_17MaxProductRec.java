public class G2_17MaxProductRec {
    public static long maxProductRec_N2(int[] array) {
        long maxProduct = 0;
        for (int i = 0; i < array.length; i++) {
            long product = 1;
            for (int j = i; j < array.length; j++) {
                if (array[j] == 0)
                    break;
                product *= array[j];
                if (product > maxProduct) {
                    maxProduct = product;
                }
            }
        }
        return maxProduct;
    }

    public static class MaxProductRec_N {
        private static long maxProduct = Integer.MIN_VALUE;
        private static int numberNegative;
        private static int productHead;
        private static int firstNegative;
        private static int productCenter;
        private static int lastNegative;
        private static int product;

        private static void resetStatus() {
            numberNegative = 0;
            productHead = 1;
            firstNegative = 1;
            productCenter = 1;
            lastNegative = 1;
            product = 1;

        }

        public static long getMaxProduct(int[] array) {
            resetStatus();
            for (int value : array) {
                if (value == 0) {
                    maxProduct = computeMaxProduct();
                    resetStatus();
                } else {
                    product *= value;
                    if (value < 0) {
                        updateStatus(value);
                    }
                }
            }
            return maxProduct;
        }

        public static long computeMaxProduct() {
            long currentMaxProduct = computeCurrentMaxProduct();
            if (currentMaxProduct > maxProduct) {
                maxProduct = currentMaxProduct;
            }
            return maxProduct;
        }

        public static long computeCurrentMaxProduct (){
            long currentMaxProduct = Integer.MIN_VALUE;
            if (isZero(numberNegative)) {
                currentMaxProduct = product;
            } else if (isEven(numberNegative)) {
                currentMaxProduct = computeMaxProductWhenEven();
            } else { // is odd
                currentMaxProduct = computeMaxProductWhenOdd();
            }
            return currentMaxProduct;
        }

        public static boolean isZero(int value) {
            return value == 0;
        }

        public static boolean isEven(int value) {
            return value % 2 == 0 && value != 0;

        }

        public static long computeMaxProductWhenEven() {
            long productTail = product / productCenter;
            long maxProduct = productHead * productCenter * productTail;
            return maxProduct;
        }

        public static long computeMaxProductWhenOdd() {
            long productTail = product / productCenter;
            long leftMaxProduct = productHead * productCenter / lastNegative;
            long rightMaxProduct = productTail * productCenter / firstNegative;
            long maxProduct = Long.max(leftMaxProduct, rightMaxProduct);
            return maxProduct;
        }

        public static void updateStatus(int value) {
            numberNegative += 1;
            if (numberNegative == 1) {
                productHead = product;
                firstNegative = value;
                lastNegative = value;
                productCenter = value;
            } else if (numberNegative > 1) {
                productCenter = product;
                lastNegative = value;
            }
        }
    }
}
