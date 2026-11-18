package View;

import javax.swing.*;

public class GameSizeButton extends JButton {
    public GameSizeButton(MainView mainView, int n, JTextField[] playerNameFields){
        super(n + " x " + n);
        this.addActionListener(e -> {
            mainView.SetView(new GamePlayPanel(mainView, n, new String[]{playerNameFields[0].getText(), playerNameFields[1].getText()}));
        });
    }
}
