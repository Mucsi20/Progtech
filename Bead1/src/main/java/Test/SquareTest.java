package Test;

import Mucsi.Square;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SquareTest {
    private static final double DELTA = 0.0001;

    @ParameterizedTest(name = "Square radius: {0}, area: {1}")
    @CsvSource({
            "1, 2.0",
            "2, 8.0",
            "15, 450.0"
    })
    void testGetArea(int radius, double expectedArea) {
        Square square = new Square(0, 0, radius);
        assertEquals(expectedArea, square.getArea(), DELTA);
    }

    @ParameterizedTest(name = "Square radius: {0}, perimeter: {1}")
    @CsvSource({
            "1, 5.65685",
            "2, 11.31371",
            "15, 84.85281"
    })
    void testGetPerimeter(int radius, double expectedPerimeter) {
        Square square = new Square(0, 0, radius);
        assertEquals(expectedPerimeter, square.getPerimeter(), DELTA);
    }
}