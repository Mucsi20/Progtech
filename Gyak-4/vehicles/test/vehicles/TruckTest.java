package vehicles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TruckTest {

    @Test
    void categoryIsT() {
        assertEquals("T", new Truck("FFF-888").getCategory());
    }
}
