package View;

import javax.swing.*;
import java.awt.*;

public class MainView {
    private final JFrame frame;
    private final double screenRate = 0.667;
    public MainView()
    {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = (int)Math.round(gd.getDisplayMode().getWidth()*screenRate);
        int height = (int)Math.round(gd.getDisplayMode().getHeight()*screenRate);
        frame = new JFrame("4 game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(1920/2-width/2,1080/2-height/2,width, height);
        frame.setVisible(true);
    }
}
