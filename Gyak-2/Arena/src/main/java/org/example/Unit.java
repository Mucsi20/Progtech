package org.example;

public class Unit{
    private String name;
    private int hp;
    private int attackDamage;
    public boolean alive;
    public void Attack(Unit target){
        target.CalculateDamage(attackDamage);
    }
    public void TakeDamage(int damage){
        if(hp >= damage){
            hp-=damage;
        }
        else{
            hp = -1;
            this.alive = false;
        }
    }
    public void CalculateDamage(int damage){
        TakeDamage(damage);
    }
    public Unit(String name, int hp, int attackDamage){
        this.name = name;
        this.hp = hp;
        this.attackDamage = attackDamage;
        this.alive = true;
    }
}
