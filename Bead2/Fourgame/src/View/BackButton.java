package View;

import javax.swing.*;

public class BackButton extends JButton {
    public BackButton(MainView mainView){
        super("Menu");
        this.addActionListener(e -> {
                mainView.SetView(new MenuPanel(mainView));
        });
    }
}
