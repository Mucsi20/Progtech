package arena.item;

public class Greataxe extends Item{
    @Override
    public int DamageOut(int damage) {
        return damage * 2;
    }
}
