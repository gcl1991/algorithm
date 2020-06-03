import java.util.Objects;
import java.util.function.ToIntFunction;

public class G1_15 {
    public static Rectangle findMaxArea (Rectangle[] rectangles){
        return findSpecifyMaxElement(rectangles,(Rectangle element) -> element.getWidth()*element.getLength());
    }

    public static Rectangle findMaxPerimeter (Rectangle[] rectangles){
        return findSpecifyMaxElement(rectangles,(Rectangle element) -> element.getWidth()+element.getLength());
    }

    public static <T> T findSpecifyMaxElement (T[] array, ToIntFunction<T> getQuota){
        int maxValue = 0;
        T maxElement = null;
        for (T element:
                array) {
            int value = getQuota.applyAsInt(element);
            if (value > maxValue){
                maxValue= value;
                maxElement = element;
            }
        }
        return maxElement;

    }

    public static class Rectangle{
        private int length;
        private int width;

        public Rectangle(int length, int width){
            this.length = length;
            this.width = width;
        }

        public int getLength (){
            return length;
        }

        public int getWidth(){
            return width;
        }

        @Override
        public boolean equals(Object otherObject){
            // 1 自反性
            if (this == otherObject) return true;
            // 2 非空性
            if (this == null) return false;
            // 3 类型测试与转换
            if (getClass() != otherObject.getClass()) return false;
            Rectangle other = (Rectangle) otherObject;
            // 4 开始比较
            return Objects.equals(width,other.width) && Objects.equals(length,other.length);
        }

    }
}
