package test;

import arena.MainCharacter;
import arena.orc.Fighter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainCharacterTest {

    @Test
    void heroLosesAttackDividedByDefense() {
        MainCharacter hero = new MainCharacter("Aria", 80, 1, 2.0);
        Fighter attacker = new Fighter("Gruk", 10, 15);

        attacker.attack(hero);

        assertEquals(73, hero.getHp());
    }

    @Test
    void heroTruncatesFractionalDamageTowardZero() {
        MainCharacter hero = new MainCharacter("Aria", 80, 1, 2.0);
        Fighter attacker = new Fighter("Gruk", 10, 5);

        attacker.attack(hero);

        assertEquals(78, hero.getHp());
    }
}
