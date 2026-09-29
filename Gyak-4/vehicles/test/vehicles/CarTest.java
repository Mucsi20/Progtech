package vehicles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarTest {

    @Test
    void categoryIsC() {
        assertEquals("C", new Car("AAA-111").getCategory());
    }
}
