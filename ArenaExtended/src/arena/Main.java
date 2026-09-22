package arena;

import arena.dragon.BlackDragon;
import arena.dragon.RedDragon;
import arena.item.Item;
import arena.item.ShortSword;
import arena.orc.Berserker;
import arena.orc.Defender;
import arena.orc.Fighter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        List<Character> fighters = createFighters();
        Character winner = fightUntilOneRemains(fighters, new Random());
        System.out.println(winner.getName());
    }

    private static List<Character> createFighters() {
        List<Character> fighters = new ArrayList<>();
        fighters.add(new MainCharacter("Aria", 80, 15, 2.0));
        fighters.add(new Fighter("Gruk", 60, 25));
        fighters.add(new Berserker("Rash", 80, 30));
        fighters.add(new Defender("Tor", 50, 70));
        fighters.add(new RedDragon("Ignis", 100, 65));
        fighters.add(new BlackDragon("Nyx", 90, 22));
        return fighters;
    }

    private static Character fightUntilOneRemains(List<Character> fighters, Random random) {
        List<Character> living = livingCharacters(fighters);
        while (living.size() > 1) {
            Character attacker = living.get(random.nextInt(living.size()));
            Character defender = pickDifferent(living, attacker, random);
            int hpBefore = defender.getHp();
            attacker.attack(defender);
            reportStrike(attacker, defender, hpBefore);
            living = livingCharacters(fighters);
        }
        return living.get(0);
    }

    private static void reportStrike(Character attacker, Character defender, int hpBefore) {
        int damage = hpBefore - defender.getHp();
        System.out.println(attacker.getName() + " hits " + defender.getName()
                + " for " + damage + " (" + defender.getHp() + " HP)");
        if (!defender.isAlive()) {
            System.out.println(defender.getName() + " is defeated");
        }
    }

    private static List<Character> livingCharacters(List<Character> fighters) {
        List<Character> living = new ArrayList<>();
        for (Character fighter : fighters) {
            if (fighter.isAlive()) {
                living.add(fighter);
            }
        }
        return living;
    }

    private static Character pickDifferent(List<Character> living, Character attacker, Random random) {
        Character defender;
        do {
            defender = living.get(random.nextInt(living.size()));
        } while (defender == attacker);
        return defender;
    }

    private List<Item> Items(int n){
        Random random = new Random();
        for (int i = 0; i < n; i++){
            enum itemType =
        }
    }
}
