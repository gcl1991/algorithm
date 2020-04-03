import java.util.function.IntFunction;

public class G1_14 {
    public static class OrderedCollection<T extends Comparable<T>> extends G1_13.Collection<T>{
        public OrderedCollection(int arraySize, IntFunction<T[]> constr) {
            super(arraySize,constr);
        }

        public T findMin(){
            if (isEmpty())
                return null;
            return array[findMinMaxIndex()[0]];
        }

        public T findMax(){
            if (isEmpty())
                return null;
            return array[findMinMaxIndex()[1]];
        }

        private int[] findMinMaxIndex(){
            int indexMin = 0;
            int indexMax = 0;
            for(int index=1; index<size; index++){
                if (array[index].compareTo(array[indexMin]) < 0){
                    indexMin = index;
                }

                if (array[index].compareTo(array[indexMax]) > 0){
                    indexMax = index;
                }

            }
            return new int[]{indexMin,indexMax};
        }
    }
}
