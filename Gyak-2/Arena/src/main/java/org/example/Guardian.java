package org.example;

public class Guardian extends Orc{

    @Override
    public void CalculateDamage(int damage){
        TakeDamage(damage/2);
    }
    public Guardian(String name, int hp, int attackDamage){
        super(name, hp, attackDamage);
    }
}