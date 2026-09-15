package org.example;

public class RedDragon extends Dragon{
    @Override
    public void CalculateDamage(int damage){
        if(damage >= 60){
            TakeDamage(damage);
        }
    }
    public RedDragon(String name, int hp, int attackDamage){
        super(name, hp, attackDamage);
    }
}