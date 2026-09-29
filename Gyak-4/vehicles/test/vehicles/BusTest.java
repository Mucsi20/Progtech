package vehicles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BusTest {

    @Test
    void categoryIsB() {
        assertEquals("B", new Bus("ABC-123").getCategory());
    }
}
