package helpers;

public class Hashs {
    private Hashs(){

    }
    public static <T> int myhash(T x,int length) {
        int hashVal = x.hashCode();

        hashVal %= length;
        if (hashVal < 0)
            hashVal += length;

        return hashVal;
    }

}
