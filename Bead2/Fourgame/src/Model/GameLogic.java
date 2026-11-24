package Model;

import View.GridCell;

public class GameLogic {
    private final int size;
    private final GridCellModel[][] gridCells;
    private final Player[] players;
    private int currentPlayerIndex = 0;

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

    public void clickCell(int r, int c){
        gridCells[r][c].increaseValue();

        if (r > 0) {gridCells[r - 1][c].increaseValue();}
        if (r < size - 1) {gridCells[r + 1][c].increaseValue();}
        if (c > 0) {gridCells[r][c - 1].increaseValue();}
        if (c < size - 1) {gridCells[r][c + 1].increaseValue();}

        if (!allCellsOwned()){
            currentPlayerIndex = 1 - currentPlayerIndex;
        }
    }

    public int getCellValue(int r, int c){
        return gridCells[r][c].getValue();
    }

    public void increaseScore(){
        players[currentPlayerIndex].increaseScore();
    }
    public int getCurrentPlayerIndex(){
        return currentPlayerIndex;
    }

    public int getCellOwner(int r, int c){
        return gridCells[r][c].getOwnerIndex();
    }
    public Player getWinner(){
        return (players[0].getScore() > players[1].getScore()) ? players[0] :  players[1];
    }
    private Boolean allCellsOwned(){
        for (GridCellModel[] cellRow : gridCells){
            for (GridCellModel cell : cellRow){
                if(cell.getOwnerIndex() == -1) {return false;}
            }
        }
        return true;
    }
}