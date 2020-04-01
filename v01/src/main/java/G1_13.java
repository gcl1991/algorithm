// import lombok.Data;

public class G1_13 {
//    @Data
    public static class Collection<T>{
        private Object[] array;
        public final int length;
        private int size;

        public Collection (int arraySize){
            this.size = 0;
            this.array = new Object[arraySize];
            this.length = arraySize;
        }
        public boolean isEmpty (){
            return size==0;
        }

        public void makEmpty (){
            size = 0;
            array = new Object[array.length];
        }

        public void insert (int index,T value) throws ArrayIndexOutOfBoundsException {
            if(index<0 || index>=length){
                throw new ArrayIndexOutOfBoundsException();
            }
            else if(size == length){
                throw new ArrayFullException();
            }
            else if(index>=size){
                array[index] = value;
                size+=1;
            }
            else {
                for (int i = size; i>index; i--){
                    array[i] = array[i-1];
                }
                array[index] = value;
                size+=1;
            }
        }

        public T remove (int index) throws ArrayIndexOutOfBoundsException{
            if(index<0 || index>=size)
                throw new ArrayIndexOutOfBoundsException();
            else{
                T element = (T) array[index];
                for (int i = index; i<size; i++){
                    array[i] = array[i+1];
                }
                size-=1;
                return element;
            }

        }

        public boolean isPresent (T other){
            // 非空性
            if (other == null)
                return false;
            // 内容比较
            for (int index=0;index<size;index++){
                T theElement = (T) array[index];
                if (theElement.equals(other))
                    return true;
            }
            return false;
        }

        public int getSize (){
            return size;
        }
    }
}

class ArrayFullException extends ArrayStoreException{
    public ArrayFullException(){};
    public ArrayFullException(String gripe){
        super(gripe);
    }
}
