package Model;

public class GridCellModel {
    private int value = 0;
    private int ownerIndex = -1; // -1 = none, 0 = Player 1, 1 = Player 2
    private final GameLogic gameLogic;

    public GridCellModel(GameLogic gameLogic){
        this.gameLogic = gameLogic;
    }

    public void increaseValue(){
        if (this.value < 4){
            this.value += 1;
            if (this.value == 4){
                this.ownerIndex = this.gameLogic.getCurrentPlayerIndex();
                this.gameLogic.increaseScore();
            }
        }
    }

    public int getValue(){
        return this.value;
    }

    public int getOwnerIndex(){
        return this.ownerIndex;
    }
}