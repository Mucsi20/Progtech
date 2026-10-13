package calc.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdditionTest {

    @Test
    void applyAddsTheOperands() {
        Operation addition = new Addition();

        assertEquals(5.0, addition.apply(2.0, 3.0));
    }

    @Test
    void symbolIsPlus() {
        Operation addition = new Addition();

        assertEquals("+", addition.getSymbol());
    }
}
