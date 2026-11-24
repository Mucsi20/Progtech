package View;

import javax.swing.*;
import java.awt.*;

/**
 * Manages the primary application {@link JFrame} and handles switching between UI panels.
 */
public class MainView {
    private final JFrame frame;
    private final double screenRate = 0.667;
    private JPanel currentView;

    /**
     * Initializes the main application window sized proportionally to the screen
     * and displays the initial {@link MenuPanel}.
     */
    public MainView()
    {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = (int)Math.round(gd.getDisplayMode().getWidth()*screenRate);
        int height = (int)Math.round(gd.getDisplayMode().getHeight()*screenRate);

        frame = new JFrame("Four game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(1920/2-width/2,1080/2-height/2,width, height);

        JPanel menuView = new MenuPanel(this);
        SetView(menuView);
        frame.setVisible(true);
    }

    /**
     * Replaces the currently displayed panel in the window with a new view.
     *
     * @param view the {@link JPanel} to display
     */
    public void SetView(JPanel view){
        if (currentView != null){
            frame.remove(currentView);
        }
        currentView = view;
        frame.add(view);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Returns the underlying application frame.
     *
     * @return the main {@link JFrame}
     */
    public JFrame getFrame(){
        return this.frame;
    }
}