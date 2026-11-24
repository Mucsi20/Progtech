package View;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel{
    MainView mainView;
    public MenuPanel(MainView mainView){
        this.mainView = mainView;

        JButton playBtn = new JButton("Play");
        JButton quitBtn = new JButton("Quit");

        initLayout(playBtn, quitBtn);
        initButtonActions(playBtn, quitBtn);

    }
    private void initButtonActions(JButton playBtn, JButton quitBtn){
        quitBtn.addActionListener(e -> System.exit(0));

        playBtn.addActionListener(e -> {
            mainView.SetView(new GameStartPanel(mainView));
        });
    }
    private void initLayout(JButton playBtn, JButton quitBtn){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Dimension buttonSize = new Dimension(250, 60);
        JButton[] buttons = {playBtn, quitBtn};

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
        this.add(quitBtn);
        this.add(Box.createVerticalGlue());
    }

}
