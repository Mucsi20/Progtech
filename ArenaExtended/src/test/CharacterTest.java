package test;

import arena.orc.Fighter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CharacterTest {

    @Test
    void getNameReturnsTheGivenName() {
        Fighter fighter = new Fighter("Gruk", 60, 25);

        assertEquals("Gruk", fighter.getName());
    }

    @Test
    void zeroHpIsAlive() {
        Fighter fighter = new Fighter("Gruk", 0, 25);

        assertTrue(fighter.isAlive());
    }

    @Test
    void negativeHpIsDead() {
        Fighter fighter = new Fighter("Gruk", -1, 25);

        assertFalse(fighter.isAlive());
    }
}
