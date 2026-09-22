package test;

import arena.orc.Fighter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FighterTest {

    @Test
    void fighterLosesHpEqualToTheAttack() {
        Fighter fighter = new Fighter("Gruk", 60, 1);
        Fighter attacker = new Fighter("Aria", 10, 25);

        attacker.attack(fighter);

        assertEquals(35, fighter.getHp());
    }
}
