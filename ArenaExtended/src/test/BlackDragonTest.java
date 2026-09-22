package test;

import arena.dragon.BlackDragon;
import arena.orc.Fighter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlackDragonTest {

    @Test
    void blackDragonIgnoresAttackOfTwenty() {
        BlackDragon dragon = new BlackDragon("Nyx", 90, 1);
        Fighter attacker = new Fighter("Gruk", 10, 20);

        attacker.attack(dragon);

        assertEquals(90, dragon.getHp());
    }

    @Test
    void blackDragonTakesDamageAboveTwenty() {
        BlackDragon dragon = new BlackDragon("Nyx", 90, 1);
        Fighter attacker = new Fighter("Gruk", 10, 21);

        attacker.attack(dragon);

        assertEquals(69, dragon.getHp());
    }
}
