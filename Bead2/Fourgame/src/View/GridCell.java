package View;

import javax.swing.*;
import java.awt.*;

public class GridCell extends JButton {
    public GridCell() {
        super("0");
    }

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