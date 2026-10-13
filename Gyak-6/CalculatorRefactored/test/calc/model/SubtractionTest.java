package calc.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubtractionTest {

    @Test
    void applySubtractsTheRightOperand() {
        Operation subtraction = new Subtraction();

        assertEquals(-1.0, subtraction.apply(2.0, 3.0));
    }

    @Test
    void symbolIsMinus() {
        Operation subtraction = new Subtraction();

        assertEquals("-", subtraction.getSymbol());
    }
}
