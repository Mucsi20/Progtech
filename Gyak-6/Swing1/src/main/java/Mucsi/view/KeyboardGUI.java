package Mucsi.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;

public class KeyboardGUI {
    private final JFrame frame;
    private final JPanel textPanel;
    private final JPanel buttonPanel;
    private final JTextField textField;
    private final int width = 1200;
    private final int height = 400;
    public KeyboardGUI(){
        frame = new JFrame("QWERTY");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(1920/2-width/2,1080/2-height/2,width, height);

        textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textField = new JTextField();
        textField.setBounds(0,0,1200,100);
        textField.setBorder(new LineBorder(Color.BLACK,1));
        textPanel.add(textField);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        String qwerty = "QWERTZUIOPASDFGHJKLYXCVBNM";
        for (char c : qwerty.toCharArray()){
            JButton newButton = new JButton(String.valueOf(c));
            newButton.setBorder(new LineBorder(Color.BLACK,1));
            buttonPanel.add(newButton);

        }

        frame.getContentPane().add(BorderLayout.NORTH, textPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);
        frame.setVisible(true);
    }

}
