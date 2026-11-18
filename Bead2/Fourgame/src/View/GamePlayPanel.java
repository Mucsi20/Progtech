package View;

import Model.GameLogic;
import Model.Player;

import javax.swing.*;
import java.awt.*;

public class GamePlayPanel extends JPanel {

    public GamePlayPanel(MainView mainView, int size, String[] playerNames) {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(20, 40, 30, 40));

        Player player1 = new Player(playerNames[0]);
        Player player2 = new Player(playerNames[1]);
        Font playerNameFont = new Font("SansSerif", Font.BOLD, 18);
        JLabel player1Name = new JLabel(player1.getName());
        player1Name.setFont(playerNameFont);
        player1Name.setForeground(Color.RED);
        JLabel player1Score = new JLabel(String.valueOf(player1.getScore()));
        JPanel nameScoreWrapperLeft = new JPanel();
        nameScoreWrapperLeft.setLayout(new BoxLayout(nameScoreWrapperLeft, BoxLayout.Y_AXIS));
        nameScoreWrapperLeft.add(player1Name);
        nameScoreWrapperLeft.add(player1Score);

        JLabel player2Name = new JLabel(player2.getName());
        player2Name.setFont(playerNameFont);
        player2Name.setForeground(Color.BLUE);
        JLabel player2Score = new JLabel(String.valueOf(player2.getScore()));
        JPanel nameScoreWrapperRight = new JPanel();
        nameScoreWrapperRight.setLayout(new BoxLayout(nameScoreWrapperRight, BoxLayout.Y_AXIS));
        nameScoreWrapperRight.add(player2Name);
        nameScoreWrapperRight.add(player2Score);

        JPanel buttonWrapper = new JPanel();
        buttonWrapper.add(new BackButton(mainView));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(nameScoreWrapperLeft, BorderLayout.WEST);
        topPanel.add(buttonWrapper, BorderLayout.CENTER);
        topPanel.add(nameScoreWrapperRight, BorderLayout.EAST);

        GameLogic logic = new GameLogic(size, new Player[]{player1, player2});
        GameGrid grid = new GameGrid(size, logic, () -> {
            player1Score.setText(String.valueOf(player1.getScore()));
            player2Score.setText(String.valueOf(player2.getScore()));
        });
        grid.setPreferredSize(new Dimension(500, 500));

        JPanel centerWrapper = new JPanel();
        centerWrapper.add(grid);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerWrapper, BorderLayout.CENTER);
    }
}