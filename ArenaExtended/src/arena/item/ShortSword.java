package arena.item;

public class ShortSword extends Item{
    @Override
    public int DamageOut(int damage) {
        return damage + 10;
    }
}
