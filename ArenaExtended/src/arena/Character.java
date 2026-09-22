package arena;

import arena.item.Item;

public abstract class Character {

    private final String name;
    private int hp;
    private final int attack;
    private Item item;

    protected Character(String name, int hp, int attack) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
    }

    public void attack(Character target) {
        target.receiveDamage(this.item.DamageOut(attack));
    }

    public void receiveDamage(int amount) {
        hp -= this.item.DamageIn(amount);
    }

    public boolean isAlive() {
        return hp >= 0;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public void setItem(Item item){
        this.item = item;
    }
    public Item getItem(){
        return this.item;
    }
}
