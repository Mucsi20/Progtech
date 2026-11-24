package View;

import javax.swing.*;
import java.awt.*;

/**
 * The main menu panel displaying options to start a game or exit the application.
 */
public class MenuPanel extends JPanel{
    /** The main application view controller. */
    private MainView mainView;

    /**
     * Constructs the main menu with Play and Quit buttons.
     *
     * @param mainView the main application view controller
     */
    public MenuPanel(MainView mainView){
        this.mainView = mainView;

        JButton playBtn = new JButton("Play");
        JButton quitBtn = new JButton("Quit");

        initLayout(playBtn, quitBtn);
        initButtonActions(playBtn, quitBtn);

    }

    /**
     * Attaches click listeners to the Play and Quit buttons.
     *
     * @param playBtn the button that opens {@link GameStartPanel}
     * @param quitBtn the button that terminates the application
     */
    private void initButtonActions(JButton playBtn, JButton quitBtn){
        quitBtn.addActionListener(e -> System.exit(0));

        playBtn.addActionListener(e -> {
            mainView.SetView(new GameStartPanel(mainView));
        });
    }

    /**
     * Configures the vertical layout and uniform sizing of the menu buttons.
     *
     * @param playBtn the Play button
     * @param quitBtn the Quit button
     */
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