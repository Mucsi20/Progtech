package View;

import Model.GameLogic;

import javax.swing.*;
import java.awt.*;

/**
 * A Swing panel displaying the interactive grid of {@link GridCell} buttons.
 */
public class GameGrid extends JPanel {
    /** The number of rows and columns in the grid. */
    private final int size;
    /** The 2D array of visual cell buttons. */
    private final GridCell[][] cells;
    /** The game logic controller managing board state. */
    private final GameLogic gameLogic;

    /**
     * Constructs the visual game grid and wires cell click listeners to the game logic.
     *
     * @param size           the number of rows and columns in the grid
     * @param gameLogic      the {@link GameLogic} instance managing board state
     * @param onMoveComplete a callback executed after each cell click to update UI elements
     */
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

    /**
     * Updates every cell button in the grid to reflect the current state in {@link GameLogic}.
     */
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

    /**
     * Checks whether all cells on the visual grid have reached a value of 4.
     *
     * @return {@code true} if every cell displays "4", {@code false} otherwise
     */
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

    /**
     * Disables all cell buttons in the grid once the game ends.
     */
    private void disableGrid(){
        for (GridCell[] cellRow : cells){
            for (GridCell cell : cellRow){
                cell.setEnabled(false);
            }
        }
    }
}