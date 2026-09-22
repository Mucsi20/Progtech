package arena.orc;

public class Defender extends Orc {

    public Defender(String name, int hp, int attack) {
        super(name, hp, attack);
    }

    @Override
    public void receiveDamage(int amount) {
        super.receiveDamage(amount / 2);
    }
}
