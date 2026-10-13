package calc.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MultiplicationTest {

    @Test
    void applyMultipliesTheOperands() {
        Operation multiplication = new Multiplication();

        assertEquals(6.0, multiplication.apply(2.0, 3.0));
    }

    @Test
    void symbolIsAsterisk() {
        Operation multiplication = new Multiplication();

        assertEquals("*", multiplication.getSymbol());
    }
}
