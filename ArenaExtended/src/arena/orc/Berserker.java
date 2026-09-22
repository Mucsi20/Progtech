package arena.orc;

public class Berserker extends Orc {

    public Berserker(String name, int hp, int attack) {
        super(name, hp, attack);
    }

    @Override
    public void receiveDamage(int amount) {
        super.receiveDamage(2 * amount);
    }
}
