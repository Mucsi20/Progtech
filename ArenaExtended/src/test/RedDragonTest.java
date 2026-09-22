package test;

import arena.dragon.RedDragon;
import arena.orc.Fighter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RedDragonTest {

    @Test
    void redDragonIgnoresAttackOfSixty() {
        RedDragon dragon = new RedDragon("Ignis", 100, 1);
        Fighter attacker = new Fighter("Gruk", 10, 60);

        attacker.attack(dragon);

        assertEquals(100, dragon.getHp());
    }

    @Test
    void redDragonTakesDamageAboveSixty() {
        RedDragon dragon = new RedDragon("Ignis", 100, 1);
        Fighter attacker = new Fighter("Gruk", 10, 61);

        attacker.attack(dragon);

        assertEquals(39, dragon.getHp());
    }
}
