package test;

import arena.orc.Berserker;
import arena.orc.Fighter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BerserkerTest {

    @Test
    void berserkerLosesTwiceTheAttack() {
        // Arrange
        Berserker berserker = new Berserker("Rash", 80, 1);
        Fighter attacker = new Fighter("Gruk", 10, 30);

        // Act
        attacker.attack(berserker);

        // Assert
        assertEquals(20, berserker.getHp());
    }
}
