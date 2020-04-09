public class G2_13 {
    public static long simpleF (int a,int x,int n){
        long sum = 0;
        for(int i=0;i<n+1;i++){
            sum += a*Math.pow(x,i);
        }
        return sum;
    }

    public static long recursiveF (int a,int x,int n){
        long sum = 0;
        for(int i=0;i<n+1;i++){
            sum += a*pow(x,i);
        }
        return sum;
    }

    public static long pow (long x, long n){
        if (n==0)
            return 1;
        else if (n==1)
            return x;
        else if (n % 2 == 0)
            return pow(x*x,n/2);
        else
            return x*pow(x*x,(n-1)/2);

    }
}
