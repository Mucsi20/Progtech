package arena;

public class MainCharacter extends Character {

    private final double defense;

    public MainCharacter(String name, int hp, int attack, double defense) {
        super(name, hp, attack);
        this.defense = defense;
    }

    @Override
    public void receiveDamage(int amount) {
        super.receiveDamage((int) (amount / defense));
    }
}
