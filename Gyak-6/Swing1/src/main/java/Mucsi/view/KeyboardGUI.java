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
    private final int width = 700;
    private final int height = 250;
    public KeyboardGUI(){
        frame = new JFrame("QWERTY");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(1920/2-width/2,1080/2-height/2,width, height);

        textPanel = new JPanel();
        textPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(500, 30));
        textPanel.add(textField);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        String qwerty = "QWERTZUIOPASDFGHJKLYXCVBNM";
        int i = 0;

        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        while (i < 10){
            JButton newButton = new JButton(String.valueOf(qwerty.charAt(i)));
            newButton.setPreferredSize(new Dimension(50,50));
            row1.add(newButton);
            i++;
        }
        buttonPanel.add(row1);

        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        row2.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        while (i<19){
            JButton newButton = new JButton(String.valueOf(qwerty.charAt(i)));
            newButton.setPreferredSize(new Dimension(50,50));
            row2.add(newButton);
            i++;
        }
        buttonPanel.add(row2);

        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        row3.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        while (i<26){
            JButton newButton = new JButton(String.valueOf(qwerty.charAt(i)));
            newButton.setPreferredSize(new Dimension(50,50));
            row3.add(newButton);
            i++;
        }
        buttonPanel.add(row3);

        JPanel centerWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerWrapper.add(buttonPanel);

        frame.getContentPane().add(BorderLayout.NORTH, textPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, centerWrapper);
        frame.setVisible(true);
    }

}
