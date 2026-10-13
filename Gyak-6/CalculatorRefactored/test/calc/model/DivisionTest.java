package calc.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DivisionTest {

    @Test
    void applyDividesTheLeftOperandByTheRight() {
        Operation division = new Division();

        assertEquals(2.0, division.apply(6.0, 3.0));
    }

    @Test
    void symbolIsSlash() {
        Operation division = new Division();

        assertEquals("/", division.getSymbol());
    }
}
