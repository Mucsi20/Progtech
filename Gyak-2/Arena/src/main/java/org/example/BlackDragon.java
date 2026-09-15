package org.example;

public class BlackDragon extends Dragon{
    @Override
    public void CalculateDamage(int damage){
        if(damage >= 20){
            TakeDamage(damage);
        }
    }
    public BlackDragon(String name, int hp, int attackDamage){
        super(name, hp, attackDamage);
    }
}