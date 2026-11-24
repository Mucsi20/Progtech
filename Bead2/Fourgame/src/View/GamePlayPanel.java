package View;

import Model.GameLogic;
import Model.Player;

import javax.swing.*;
import java.awt.*;

public class GamePlayPanel extends JPanel {
    private final MainView mainView;
    private final Player player1;
    private final Player player2;
    private final int size;
    private final JPanel victoryOverlay;

    public GamePlayPanel(MainView mainView, int size, String[] playerNames) {
        this.mainView = mainView;
        this.size = size;
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(20, 40, 30, 40));

        this.victoryOverlay = new JPanel(new BorderLayout());

        player1 = new Player(playerNames[0], Color.RED);
        player2 = new Player(playerNames[1], Color.BLUE);
        Font playerNameFont = new Font("SansSerif", Font.BOLD, 18);
        JLabel player1Name = new JLabel(player1.getName());
        player1Name.setFont(playerNameFont);
        player1Name.setForeground(player1.getColor());
        JLabel player1Score = new JLabel(String.valueOf(player1.getScore()));
        JPanel nameScoreWrapperLeft = new JPanel();
        nameScoreWrapperLeft.setLayout(new BoxLayout(nameScoreWrapperLeft, BoxLayout.Y_AXIS));
        nameScoreWrapperLeft.add(player1Name);
        nameScoreWrapperLeft.add(player1Score);

        JLabel player2Name = new JLabel(player2.getName());
        player2Name.setFont(playerNameFont);
        player2Name.setForeground(player2.getColor());
        JLabel player2Score = new JLabel(String.valueOf(player2.getScore()));
        JPanel nameScoreWrapperRight = new JPanel();
        nameScoreWrapperRight.setLayout(new BoxLayout(nameScoreWrapperRight, BoxLayout.Y_AXIS));
        nameScoreWrapperRight.add(player2Name);
        nameScoreWrapperRight.add(player2Score);

        JPanel buttonWrapper = new JPanel();
        BackButton backBtn = new BackButton(this.mainView);
        backBtn.addActionListener(e -> {
            victoryOverlay.setVisible(false);
        });
        buttonWrapper.add(backBtn);

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
    public void showVictoryOverlay(Player winner){
        victoryOverlay.setOpaque(false);

        JLabel victoryLabel = new JLabel(winner.getName() + " won!");
        victoryLabel.setFont(new Font("SansSerif", Font.BOLD, 64));
        victoryLabel.setForeground(winner.getColor());

        JButton playAgainBtn = new JButton("Play Again");
        playAgainBtn.addActionListener(e -> {
            victoryOverlay.setVisible(false);
            mainView.SetView(new GamePlayPanel(mainView, this.size, new String[]{this.player1.getName(), this.player2.getName()}));
        });

        JPanel bottomWrapper = new JPanel(new FlowLayout());
        bottomWrapper.setOpaque(false);

        bottomWrapper.add(victoryLabel);
        bottomWrapper.add(playAgainBtn);

        victoryOverlay.add(bottomWrapper, BorderLayout.SOUTH);

        mainView.getFrame().setGlassPane(victoryOverlay);
        victoryOverlay.setVisible(true);
    }
}