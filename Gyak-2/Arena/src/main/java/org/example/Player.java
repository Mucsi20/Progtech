package org.example;

public class Player extends Unit{
    private int defense;
    @Override
    public void CalculateDamage(int damage){
        TakeDamage(damage/defense);
    }
    public Player(String name, int hp, int attackDamage){
        super(name, hp, attackDamage);
    }
}