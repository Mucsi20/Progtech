package View;

import Model.GameLogic;

import javax.swing.*;
import java.awt.*;

public class GameGrid extends JPanel {
    private final int size;
    private final GridCell[][] cells;
    private final GameLogic gameLogic;

    public GameGrid(int size, GameLogic gameLogic, Runnable onMoveComplete){
        this.size = size;
        this.gameLogic = gameLogic;
        this.cells = new GridCell[size][size];
        this.setLayout(new GridLayout(size, size));

        for (int r = 0; r < size; r++){
            for (int c = 0; c < size; c++){
                GridCell cell = new GridCell();
                int row = r;
                int col = c;

                cell.addActionListener(e -> {
                    gameLogic.clickCell(row, col);
                    refreshGrid();
                    onMoveComplete.run();
                    if (allCellsComplete()){
                        disableGrid();
                        GamePlayPanel parent = (GamePlayPanel) SwingUtilities.getAncestorOfClass(GamePlayPanel.class, this);
                        parent.showVictoryOverlay(gameLogic.getWinner());
                    }
                });

                cells[r][c] = cell;
                this.add(cell);
            }
        }
    }

    private void refreshGrid(){
        for (int r = 0; r < size; r++){
            for (int c = 0; c < size; c++){
                cells[r][c].updateValue(
                        gameLogic.getCellValue(r, c),
                        gameLogic.getCellOwner(r, c)
                );
            }
        }
    }

    private Boolean allCellsComplete(){
        for (GridCell[] cellRow : cells){
            for (GridCell cell : cellRow){
                if (!cell.getText().equals("4")){
                    return false;
                }
            }
        }
        return true;
    }
    private void disableGrid(){
        for (GridCell[] cellRow : cells){
            for (GridCell cell : cellRow){
                cell.setEnabled(false);
            }
        }
    }
}