package View;

import javax.swing.*;
import java.awt.*;

/**
 * A visual button representing a single cell in {@link GameGrid}.
 */
public class GridCell extends JButton {

    /**
     * Creates a new grid cell initialized with a display value of "0".
     */
    public GridCell() {
        super("0");
    }

    /**
     * Updates the cell's displayed number and applies the owner's color when claimed.
     *
     * @param value      the current numeric value of the cell
     * @param ownerIndex the owner index (-1 if unowned, 0 for Player 1, 1 for Player 2)
     */
    public void updateValue(int value, int ownerIndex) {
        this.setText(String.valueOf(value));
        if (ownerIndex == 0) {
            this.setBackground(Color.RED);
            this.setForeground(Color.WHITE);
        } else if (ownerIndex == 1) {
            this.setBackground(Color.BLUE);
            this.setForeground(Color.WHITE);
        }
    }
}