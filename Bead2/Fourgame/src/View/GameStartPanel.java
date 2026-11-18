package View;

import javax.swing.*;
import java.awt.*;

public class GameStartPanel extends JPanel {

    public GameStartPanel(MainView mainView){
        JTextField[] playerNameFields = createPlayerNameFields();

        int gameSizes = 3;
        JButton[] buttons = new JButton[gameSizes +1];
        for (int i = 0; i < gameSizes; i++){
            int n = 2 * (i+1) + 1;
            buttons[i] = new GameSizeButton(mainView, n, playerNameFields);
        }

        JButton backBtn = new BackButton(mainView);
        buttons[gameSizes] = backBtn;

        initLayout(buttons, playerNameFields);
    }
    private void initLayout(JButton[] buttons, JTextField[] playerNameFields){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Dimension buttonSize = new Dimension(250, 60);

        JPanel playerNamesPanel = new JPanel();
        playerNamesPanel.add(playerNameFields[0]);
        playerNamesPanel.add(playerNameFields[1]);

        this.add(Box.createVerticalGlue());
        this.add(playerNamesPanel);

        for (int i = 0; i < buttons.length; i++) {
            JButton btn = buttons[i];

            btn.setPreferredSize(buttonSize);
            btn.setMaximumSize(buttonSize);
            btn.setMinimumSize(buttonSize);

            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(btn);

            if (i < buttons.length - 1){
                this.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }
        this.add(Box.createVerticalGlue());
    }
    private JTextField[] createPlayerNameFields(){
        JTextField player1Field = new JTextField("Player 1",10);
        JTextField player2Field = new JTextField("Player 2", 10);
        Font playerNameFont = new Font("SansSerif", Font.BOLD, 18);
        player1Field.setFont(playerNameFont);
        player1Field.setForeground(Color.RED);
        player2Field.setFont(playerNameFont);
        player2Field.setForeground(Color.BLUE);

        return new JTextField[]{player1Field, player2Field};
    }
}
