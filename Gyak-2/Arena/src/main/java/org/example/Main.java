package org.example;

import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ArrayList<Unit> ArenaList = new ArrayList<>();

        Player player = new Player("Hestheros", 200, 20);
        Warrior warrior = new Warrior("Samuro", 210, 18);
        Guardian guardian = new Guardian("Abrahem", 250, 10);
        Berserker berserker = new Berserker("Grom", 200, 15);
        RedDragon redDragon = new RedDragon("Syndragosa", 200, 20);
        BlackDragon blackDragon = new BlackDragon("Neltharion", 200, 20);

        ArenaList.add(player);
        ArenaList.add(warrior);
        ArenaList.add(guardian);
        ArenaList.add(berserker);
        ArenaList.add(redDragon);
        ArenaList.add(blackDragon);

        int aliveCounter = ArenaList.size();
        while(aliveCounter > 1){
            for (int i = 0; i < ArenaList.size(); i++) {
                if(ArenaList.get(i).alive){
                    int j = -1;
                    do {
                        j = (int)(Math.random() * aliveCounter);
                    }while(i == j);
                    ArenaList.get(i).Attack(ArenaList.get(j));
                }
            }
        }
    }
}