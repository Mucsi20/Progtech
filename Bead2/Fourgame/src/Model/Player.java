package Model;

import java.awt.*;

public class Player {
    private final String name;
    private int score;
    private Color color;
    public Player(String name, Color color){
        this.name = name;
        this.color = color;
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
    public Color getColor(){
        return this.color;
    }
}
