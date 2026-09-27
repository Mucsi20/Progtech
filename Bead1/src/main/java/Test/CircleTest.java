package Test;

import Mucsi.Circle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CircleTest {
    private static final double DELTA = 0.0001;

    @ParameterizedTest(name = "Circle radius: {0}, area: {1}")
    @CsvSource({
            "1, 3.14159",
            "2, 12.56637",
            "15, 706.85834"
    })
    void testGetArea(int radius, double expectedArea) {
        Circle circle = new Circle(0, 0, radius);
        assertEquals(expectedArea, circle.getArea(), DELTA);
    }

    @ParameterizedTest(name = "Circle radius: {0}, perimeter: {1}")
    @CsvSource({
            "1, 6.28318",
            "2, 12.56637",
            "15, 94.24777"
    })
    void testGetPerimeter(int radius, double expectedPerimeter) {
        Circle circle = new Circle(0, 0, radius);
        assertEquals(expectedPerimeter, circle.getPerimeter(), DELTA);
    }
}
