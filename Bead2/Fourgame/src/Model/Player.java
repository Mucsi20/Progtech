package Model;

public class Player {
    private final String name;
    private int score;
    public Player(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public int getScore(){
        return this.score;
    }
    public void increaseScore(){
        this.score++;
    }
}
