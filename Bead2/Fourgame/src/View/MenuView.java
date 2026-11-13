package View;

import javax.swing.*;
import java.awt.*;

public class MenuView extends JPanel{
    public MenuView(){
        JButton playBtn = new JButton("Play");
        JButton historyBtn = new JButton("Match History");
        JButton quitBtn = new JButton("Quit");

        initButtonLayout(playBtn, historyBtn, quitBtn);
        initButtonActions(playBtn, historyBtn, quitBtn);

    }
    private void initButtonActions(JButton playBtn, JButton historyBtn, JButton quitBtn){
        quitBtn.addActionListener(e -> System.exit(0));

        playBtn.addActionListener(e -> {
            System.out.println("Play clicked!");
            // Switch to game view
        });

        historyBtn.addActionListener(e -> {
            System.out.println("History clicked!");
            // Switch to history view
        });
    }
    private void initButtonLayout(JButton playBtn, JButton historyBtn, JButton quitBtn){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Dimension buttonSize = new Dimension(250, 60);
        JButton[] buttons = {playBtn, historyBtn, quitBtn};

        for (JButton btn : buttons) {
            //btn.setFont(buttonFont);
            btn.setPreferredSize(buttonSize);
            btn.setMaximumSize(buttonSize);
            btn.setMinimumSize(buttonSize);

            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        this.add(Box.createVerticalGlue());
        this.add(playBtn);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(historyBtn);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(quitBtn);
        this.add(Box.createVerticalGlue());
    }

}
