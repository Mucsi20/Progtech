package arena.item;

public class TowerShield extends Item{
    @Override
    public int DamageIn(int damage) {
        return Math.max(0, damage-10);
    }
}
