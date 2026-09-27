package Test;

import Mucsi.Circle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CircleTest {
    @Test
    void AreaTest(){
        int radius1 = 1;
        int radius2 = 2;
        int radius3 = 15;

        Circle circle1 = new Circle(0,0,radius1);
        Circle circle2 = new Circle(0,0,radius2);
        Circle circle3 = new Circle(0,0,radius3);

        double area1 = Math.pow(radius1, 2) * Math.PI;
        double area2 = Math.pow(radius2, 2) * Math.PI;
        double area3 = Math.pow(radius3, 2) * Math.PI;

        assertEquals(area1, circle1.GetArea());
        assertEquals(area2, circle2.GetArea());
        assertEquals(area3, circle3.GetArea());
    }
    @Test
    void PerimeterTest(){
        int radius1 = 1;
        int radius2 = 2;
        int radius3 = 15;

        Circle circle1 = new Circle(0,0,radius1);
        Circle circle2 = new Circle(0,0,radius2);
        Circle circle3 = new Circle(0,0,radius3);

        double peri1 = radius1 * 2 * Math.PI;
        double peri2 = radius2 * 2 * Math.PI;
        double peri3 = radius3 * 2 * Math.PI;

        assertEquals(peri1, circle1.GetArea());
        assertEquals(peri2, circle2.GetArea());
        assertEquals(peri3, circle3.GetArea());
    }
}
