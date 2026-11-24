package View;

import javax.swing.*;

/**
 * A reusable navigation button that returns the user to the main menu.
 */
public class BackButton extends JButton {

    /**
     * Creates a "Menu" button that switches the active view back to {@link MenuPanel}.
     *
     * @param mainView the main application view controller
     */
    public BackButton(MainView mainView){
        super("Menu");
        this.addActionListener(e -> {
            mainView.SetView(new MenuPanel(mainView));
        });
    }
}