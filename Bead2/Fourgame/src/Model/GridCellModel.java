package Model;

/**
 * Represents the state of a single cell on the game grid, including its value and owner.
 */
public class GridCellModel {
    private int value = 0;
    private int ownerIndex = -1; // -1 = none, 0 = Player 1, 1 = Player 2
    private final GameLogic gameLogic;

    /**
     * Creates a new cell linked to the controlling game logic.
     *
     * @param gameLogic the {@link GameLogic} instance managing this cell
     */
    public GridCellModel(GameLogic gameLogic){
        this.gameLogic = gameLogic;
    }

    /**
     * Increments the cell's value by 1 up to a maximum of 4.
     * When the value reaches 4, assigns ownership to the current player and increments their score.
     */
    public void increaseValue(){
        if (this.value < 4){
            this.value += 1;
            if (this.value == 4){
                this.ownerIndex = this.gameLogic.getCurrentPlayerIndex();
                this.gameLogic.increaseScore();
            }
        }
    }

    /**
     * Returns the current value of this cell.
     *
     * @return the cell value (0 to 4)
     */
    public int getValue(){
        return this.value;
    }

    /**
     * Returns the index of the player who owns this cell.
     *
     * @return the owner index (-1 if unowned, 0 for Player 1, 1 for Player 2)
     */
    public int getOwnerIndex(){
        return this.ownerIndex;
    }
}