package View;

import javax.swing.*;

public class HistoryPanel extends JPanel {
    public HistoryPanel(MainView mainView){
        this.add(new BackButton(mainView));
    }
}
