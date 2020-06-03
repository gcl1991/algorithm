public class G2_7 {
    public static void a1 (long n){
        long sum = 0;
        for(long i=0; i < n; i++){
            sum++;
        }
    }

    public static void a2 (long n){
        long sum = 0;
        for(long i=0; i < n; i++){
            for(long j=0; j<n; j++){
                sum++;
            }
        }
    }

    public static void a3 (long n){
        long sum = 0;
        for(long i=0; i < n; i++){
            for(long j=0; j < n*n; j++){
                sum++;
            }
        }
    }

    public static void a4 (long n){
        long sum = 0;
        for(long i=0; i < n; i++){
            for(long j=0; j < i; j++){
                sum++;
            }
        }
    }


    public static void a5 (long n){
        long sum = 0;
        for(long i=0; i < n; i++){
            for(long j=0; j < i*i; j++){
                for (long k=0; k < j; k++){
                    sum++;
                }
            }
        }
    }

    public static void a6 (long n){
        long sum = 0;
        for(long i=1; i < n; i++){
            for(long j=1; j < i*i; j++){
                if(j % i ==0){
                    for (long k=0; k < j; k++){
                        sum++;
                    }
                }
            }
        }
    }
}
