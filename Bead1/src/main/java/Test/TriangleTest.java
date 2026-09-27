package Test;

import Mucsi.Triangle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TriangleTest {
    private static final double DELTA = 0.0001;

    @ParameterizedTest(name = "Triangle radius: {0}, area: {1}")
    @CsvSource({
            "1, 1.29904",
            "2, 5.19615",
            "15, 292.28357"
    })
    void testGetArea(int radius, double expectedArea) {
        Triangle triangle = new Triangle(0, 0, radius);
        assertEquals(expectedArea, triangle.getArea(), DELTA);
    }

    @ParameterizedTest(name = "Triangle radius: {0}, perimeter: {1}")
    @CsvSource({
            "1, 5.19615",
            "2, 10.39230",
            "15, 77.94229"
    })
    void testGetPerimeter(int radius, double expectedPerimeter) {
        Triangle triangle = new Triangle(0, 0, radius);
        assertEquals(expectedPerimeter, triangle.getPerimeter(), DELTA);
    }
}