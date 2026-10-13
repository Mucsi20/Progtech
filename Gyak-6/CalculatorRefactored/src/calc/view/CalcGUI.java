package calc.view;

import calc.model.Operation;
import calc.model.Operations;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalcGUI {

    private static final int OPERATION_BUTTON_COLUMNS = 1;

    private final JFrame frame;
    private final JTextField firstOperand;
    private final JTextField secondOperand;
    private final JTextField resultField;

    public CalcGUI(int fieldWidth) {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        firstOperand = new JTextField(fieldWidth);
        secondOperand = new JTextField(fieldWidth);
        resultField = new JTextField(fieldWidth);

        frame.add(createNumberPanel(), BorderLayout.WEST);
        frame.add(createOperationPanel(), BorderLayout.EAST);
        frame.setJMenuBar(createMenuBar());
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel createNumberPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(firstOperand);
        panel.add(secondOperand);
        panel.add(resultField);
        return panel;
    }

    private JPanel createOperationPanel() {
        List<Operation> operations = Operations.all();
        JPanel panel = new JPanel(new GridLayout(operations.size(), OPERATION_BUTTON_COLUMNS));
        for (Operation operation : operations) {
            panel.add(createOperationButton(operation));
        }
        return panel;
    }

    private JButton createOperationButton(final Operation operation) {
        JButton button = new JButton(operation.getSymbol());
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                calculate(operation);
            }
        });
        return button;
    }

    private void calculate(Operation operation) {
        try {
            showResult(evaluate(operation));
        } catch (NumberFormatException exception) {
            showNumberFormatError();
        }
    }

    private double evaluate(Operation operation) {
        double left = readNumber(firstOperand);
        double right = readNumber(secondOperand);
        return operation.apply(left, right);
    }

    private double readNumber(JTextField field) {
        return Double.parseDouble(field.getText());
    }

    private void showResult(double value) {
        resultField.setText(Double.toString(value));
    }

    private void showNumberFormatError() {
        JOptionPane.showMessageDialog(
                frame,
                "Wrong number format!",
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    private JMenuBar createMenuBar() {
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                exit();
            }
        });

        JMenu calcMenu = new JMenu("Calc");
        calcMenu.add(exitItem);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(calcMenu);
        return menuBar;
    }

    private void exit() {
        System.exit(0);
    }
}
