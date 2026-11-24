package Model;

import java.awt.*;

/**
 * Represents a player in the game with a display name, score, and associated color.
 */
public class Player {
    private final String name;
    private int score;
    private Color color;

    /**
     * Creates a new player with the given name and color.
     *
     * @param name  the player's display name
     * @param color the color used to represent the player in the UI
     */
    public Player(String name, Color color){
        this.name = name;
        this.color = color;
    }

    /**
     * Returns the player's name.
     *
     * @return the player's name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Returns the player's current score.
     *
     * @return the player's score
     */
    public int getScore(){
        return this.score;
    }

    /**
     * Increments the player's score by 1.
     */
    public void increaseScore(){
        this.score++;
    }

    /**
     * Returns the player's color.
     *
     * @return the player's {@link Color}
     */
    public Color getColor(){
        return this.color;
    }
}