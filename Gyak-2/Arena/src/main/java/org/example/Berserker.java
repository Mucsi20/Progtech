package org.example;

public class Berserker extends Orc{
    @Override
    public void CalculateDamage(int damage){
        TakeDamage(damage*2);
    }
    public Berserker(String name, int hp, int attackDamage){
        super(name, hp, attackDamage);
    }
}
