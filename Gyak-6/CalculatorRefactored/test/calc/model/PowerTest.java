package calc.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PowerTest {

    @Test
    void applyRaisesTheLeftOperandToTheRight() {
        Operation power = new Power();

        assertEquals(8.0, power.apply(2.0, 3.0));
    }

    @Test
    void symbolIsCaret() {
        Operation power = new Power();

        assertEquals("^", power.getSymbol());
    }
}
