package Test;

import Mucsi.Hexagon;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HexagonTest {
    private static final double DELTA = 0.0001;

    @ParameterizedTest(name = "Hexagon radius: {0}, area: {1}")
    @CsvSource({
            "1, 2.59808",
            "2, 10.39230",
            "15, 584.56715"
    })
    void testGetArea(int radius, double expectedArea) {
        Hexagon hexagon = new Hexagon(0, 0, radius);
        assertEquals(expectedArea, hexagon.getArea(), DELTA);
    }

    @ParameterizedTest(name = "Hexagon radius: {0}, perimeter: {1}")
    @CsvSource({
            "1, 6.0",
            "2, 12.0",
            "15, 90.0"
    })
    void testGetPerimeter(int radius, double expectedPerimeter) {
        Hexagon hexagon = new Hexagon(0, 0, radius);
        assertEquals(expectedPerimeter, hexagon.getPerimeter(), DELTA);
    }
}