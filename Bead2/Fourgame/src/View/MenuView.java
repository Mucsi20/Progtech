package View;

import javax.swing.*;
import java.awt.*;

public class MenuView extends JPanel{
    public MenuView(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JButton playBtn = new JButton("Play");
        JButton historyBtn = new JButton("Match History");
        JButton quitBtn = new JButton("Quit");

        this.add(playBtn);
        this.add(historyBtn);
        this.add(quitBtn);
    }

}
