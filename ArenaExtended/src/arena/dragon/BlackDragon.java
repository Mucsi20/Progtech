package arena.dragon;

public class BlackDragon extends Dragon {

    private static final int MIN_ATTACK_TO_DAMAGE = 20;

    public BlackDragon(String name, int hp, int attack) {
        super(name, hp, attack);
    }

    @Override
    public void receiveDamage(int amount) {
        if (amount > MIN_ATTACK_TO_DAMAGE) {
            super.receiveDamage(amount);
        }
    }
}
