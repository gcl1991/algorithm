package helpers;

public class Primes {
    public static int nextPrime(int n) {
        // 下一个素数
        if (isPrime(n))
            n++;
        // 排除偶数
        if ((n & 1) == 0)
            n++;
        // 仅从奇数中找素数
        for (; !isPrime(n); n += 2) ;
        return n;
    }

    public static boolean isPrime(int n) {
        if (n == 2 || n == 3)
            return true;
        // 排除1和偶数
        if (n == 1 || (n & 2) == 0)
            return false;
        // 测试3-平方根内奇数
        for (int i = 3; i * i <= n; i += 2)
            if (n % i == 0)
                return false;
        return true;
    }

}
