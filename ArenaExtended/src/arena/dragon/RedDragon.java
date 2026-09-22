package arena.dragon;

public class RedDragon extends Dragon {

    private static final int MIN_ATTACK_TO_DAMAGE = 60;

    public RedDragon(String name, int hp, int attack) {
        super(name, hp, attack);
    }

    @Override
    public void receiveDamage(int amount) {
        if (amount > MIN_ATTACK_TO_DAMAGE) {
            super.receiveDamage(amount);
        }
    }
}
