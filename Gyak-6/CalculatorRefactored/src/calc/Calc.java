package calc;

import calc.view.CalcGUI;
import javax.swing.SwingUtilities;

public class Calc {

    private static final int FIELD_WIDTH = 30;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CalcGUI(FIELD_WIDTH);
            }
        });
    }
}
