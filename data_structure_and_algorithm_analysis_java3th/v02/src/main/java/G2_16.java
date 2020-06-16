
public final class G2_16 {
    private final static int COMMON_DIVISOR = 2;
    public static int gcd(GcdPair pair) {
        if (pair.isBothEven())
            return gcdEvenEven(pair);
        else if (pair.isBothOdd())
            return gcdOddOdd(pair);
        else if (pair.isFirstOddSecondEven())
            return gcdOddEven(pair);
        else
            return gcdEvenOdd(pair);
    }

    public static int gcdEvenEven(GcdPair pair) {
        GcdPair newPair = new GcdPair(pair.getA()/COMMON_DIVISOR, pair.getB() / COMMON_DIVISOR);
        return COMMON_DIVISOR * gcdWhile(newPair);
    }

    public static int gcdEvenOdd(GcdPair pair) {
        GcdPair newPair = new GcdPair(pair.getA() / COMMON_DIVISOR, pair.getB());
        return gcdWhile(newPair);
    }

    public static int gcdOddEven(GcdPair pair) {
        GcdPair newPair = new GcdPair(pair.getA(), pair.getB() / COMMON_DIVISOR);
        return gcdWhile(newPair);
    }

    public static int gcdOddOdd(GcdPair pair) {
        GcdPair newPair = new GcdPair((pair.getA() + pair.getB()) / COMMON_DIVISOR, (pair.getA() - pair.getB()) / COMMON_DIVISOR);
        return gcdWhile(newPair);
    }

    public static int gcdRecursive(GcdPair pair) {
        if (pair.getA() % pair.getB() == 0)
            return pair.getB();
        else
            return gcdRecursive(new GcdPair(pair.getB(), pair.getA() % pair.getB()));
    }

    public static int gcdWhile(GcdPair pair) {
        int a = pair.getA();
        int b = pair.getB();
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    static final class GcdPair {
        private int a;
        private int b;

        GcdPair(int a, int b) {
            if (a <= b){
                this.a = b;
                this.b = a;
            }else {
                this.a = a;
                this.b = b;
            }
        }

        private boolean isBothEven() {
            return a % COMMON_DIVISOR == 0 && b % COMMON_DIVISOR == 0;
        }

        private boolean isBothOdd() {
            return a % COMMON_DIVISOR != 0 && b % COMMON_DIVISOR != 0;
        }

        private boolean isFirstOddSecondEven() {
            return a % COMMON_DIVISOR != 0 && b % COMMON_DIVISOR == 0;
        }

        private int getA() {
            return a;
        }

        private int getB() {
            return b;
        }
    }
}
