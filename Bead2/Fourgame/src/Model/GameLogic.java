package Model;

/**
 * Manages the core state, rules, turns, and scoring of the game board.
 */
public class GameLogic {
    private final int size;
    private final GridCellModel[][] gridCells;
    private final Player[] players;
    private int currentPlayerIndex = 0;

    /**
     * Initializes the game board with the specified size and players.
     *
     * @param size    the width and height of the square grid
     * @param players the array of participating players
     */
    public GameLogic(int size, Player[] players){
        this.size = size;
        this.players = players;
        this.gridCells = new GridCellModel[size][size];
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                gridCells[i][j] = new GridCellModel(this);
            }
        }
    }

    /**
     * Processes a move at the given grid coordinates by incrementing the clicked cell
     * and its orthogonal neighbors, then advancing the turn if unowned cells remain.
     *
     * @param rowIndex the row index of the clicked cell
     * @param columnIndex the column index of the clicked cell
     */
    public void clickCell(int rowIndex, int columnIndex){
        gridCells[rowIndex][columnIndex].increaseValue();

        if (rowIndex > 0) {gridCells[rowIndex - 1][columnIndex].increaseValue();}
        if (rowIndex < size - 1) {gridCells[rowIndex + 1][columnIndex].increaseValue();}
        if (columnIndex > 0) {gridCells[rowIndex][columnIndex - 1].increaseValue();}
        if (columnIndex < size - 1) {gridCells[rowIndex][columnIndex + 1].increaseValue();}

        if (!allCellsOwned()){
            currentPlayerIndex = 1 - currentPlayerIndex;
        }
    }

    /**
     * Returns the numeric value of the cell at the specified position.
     *
     * @param rowIndex the row index
     * @param columnIndex the column index
     * @return the current value of the cell
     */
    public int getCellValue(int rowIndex, int columnIndex){
        return gridCells[rowIndex][columnIndex].getValue();
    }

    /**
     * Increments the score of the currently active player.
     */
    public void increaseScore(){
        players[currentPlayerIndex].increaseScore();
    }

    /**
     * Returns the index of the player whose turn it currently is.
     *
     * @return the current player's index (0 or 1)
     */
    public int getCurrentPlayerIndex(){
        return currentPlayerIndex;
    }

    /**
     * Returns the owner index of the cell at the specified position.
     *
     * @param r the row index
     * @param c the column index
     * @return the owner index (-1 if unowned, 0 for Player 1, 1 for Player 2)
     */
    public int getCellOwner(int r, int c){
        return gridCells[r][c].getOwnerIndex();
    }

    /**
     * Determines the winning player based on the highest score.
     *
     * @return the {@link Player} with the higher score (defaults to Player 2 on a tie)
     */
    public Player getWinner(){
        return (players[0].getScore() > players[1].getScore()) ? players[0] : players[1];
    }

    /**
     * Checks whether every cell on the board has been claimed by a player.
     *
     * @return {@code true} if all cells have an owner, {@code false} otherwise
     */
    private Boolean allCellsOwned(){
        for (GridCellModel[] cellRow : gridCells){
            for (GridCellModel cell : cellRow){
                if(cell.getOwnerIndex() == -1) {return false;}
            }
        }
        return true;
    }
}