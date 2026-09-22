package arena.item;

public abstract class Item {
    public enum Type{
        SHORTSWORD, GREATAXE, TOWERSHIELD
    }
    public String name;
    public int DamageOut(int damage){
        return damage;
    }
    public int DamageIn(int damage){
        return damage;
    }
}
