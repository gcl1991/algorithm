import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class G1_15Test {
    G1_15.Rectangle[] rectangles = null;
    public G1_15Test (){
        int arrayLength = 5;
        int length = 1;
        int width = 2;
        G1_15.Rectangle[] rectangles = new G1_15.Rectangle[arrayLength];
        for (int i = 0; i < 5; i++){
            rectangles[i] = new G1_15.Rectangle(length+i*5, width+i*6);
        }
        this.rectangles = rectangles;
    }

    @Test
    public void findMaxAreaTest (){
        G1_15.Rectangle maxElement = new G1_15.Rectangle(21,26);
        assertEquals(G1_15.findMaxArea(rectangles),maxElement);
    }

    @Test
    public void findMaxPerimeter (){
        G1_15.Rectangle maxElement = new G1_15.Rectangle(21,26);
        assertEquals(G1_15.findMaxPerimeter(rectangles),maxElement);
    }

}
