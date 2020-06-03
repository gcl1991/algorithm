public class G2_16 {
    public static int gcd(int a, int b) {
        abPair pair = swapIfALessB(a, b);
        a = pair.getA();
        b = pair.getB();

        if (a % 2 == 0 && b % 2 == 0)
            return gcdEvenEven(a, b);
        else if (a % 2 != 0 && b % 2 != 0)
            return gcdOddOdd(a, b);
        else if (a % 2 != 0)
            return gcdOddEven(a, b);
        else
            return gcdEvenOdd(a, b);
    }

    public static int gcdEvenEven(int a, int b) {
        return 2 * gcdWhile(a / 2, b / 2);
    }

    public static int gcdEvenOdd(int a, int b) {
        return gcdWhile(a / 2, b);
    }

    public static int gcdOddEven(int a, int b) {
        return gcdWhile(a, b / 2);
    }

    public static int gcdOddOdd(int a, int b) {
        return gcdWhile((a + b) / 2, (a - b) / 2);
    }

    public static int gcdRecursive(int a, int b) {
        abPair pair = swapIfALessB(a, b);
        a = pair.getA();
        b = pair.getB();

        if (a % b == 0)
            return b;
        else
            return gcdRecursive(b, a % b);
    }

    public static int gcdWhile(int a, int b) {
        abPair pair = swapIfALessB(a, b);
        a = pair.getA();
        b = pair.getB();

        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    public static abPair swapIfALessB(int a, int b) {
        if (a <= b) {
            int temp = a;
            a = b;
            b = temp;
        }
        return new abPair(a, b);
    }

    static class abPair {
        public int a;
        public int b;

        public abPair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }
    }
}
