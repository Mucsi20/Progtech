package test;

import arena.orc.Defender;
import arena.orc.Fighter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DefenderTest {

    @Test
    void defenderLosesHalfTheAttack() {
        Defender defender = new Defender("Tor", 50, 1);
        Fighter attacker = new Fighter("Gruk", 10, 70);

        attacker.attack(defender);

        assertEquals(15, defender.getHp());
    }

    @Test
    void defenderLosesNothingFromAnAttackOfOne() {
        Defender defender = new Defender("Tor", 50, 1);
        Fighter attacker = new Fighter("Gruk", 10, 1);

        attacker.attack(defender);

        assertEquals(50, defender.getHp());
    }
}
