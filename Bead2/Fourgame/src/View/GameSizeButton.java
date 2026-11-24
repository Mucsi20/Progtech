package View;

import javax.swing.*;

/**
 * A button that starts a new game with a specific board size and the entered player names.
 */
public class GameSizeButton extends JButton {

    /**
     * Creates a button labeled {@code n x n} that opens a {@link GamePlayPanel} when clicked.
     *
     * @param mainView         the main application view controller
     * @param n                the width and height of the square game grid
     * @param playerNameFields text fields containing Player 1's and Player 2's names
     */
    public GameSizeButton(MainView mainView, int n, JTextField[] playerNameFields){
        super(n + " x " + n);
        this.addActionListener(e -> {
            mainView.SetView(new GamePlayPanel(mainView, n, new String[]{playerNameFields[0].getText(), playerNameFields[1].getText()}));
        });
    }
}