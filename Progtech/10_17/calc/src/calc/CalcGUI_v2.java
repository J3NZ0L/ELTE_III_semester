/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author bli
 */
public class CalcGUI_v2 {

    private JFrame frame;
    private JPanel numPanel;
    private JPanel buttonPanel;
    private JTextField operand1;
    private JTextField operand2;
    private JTextField result;

    public CalcGUI_v2(int fieldWidth) {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        numPanel = new JPanel();
        numPanel.setLayout(new BoxLayout(numPanel, BoxLayout.Y_AXIS));
        operand1 = new JTextField(fieldWidth);
        numPanel.add(operand1);
        operand2 = new JTextField(fieldWidth);
        numPanel.add(operand2);
        result = new JTextField(fieldWidth);
        numPanel.add(result);
        
        buttonPanel = new JPanel();

        String operations[] = {"+", "-", "*", "/", "^"};
        buttonPanel.setLayout(new GridLayout(operations.length, 1));
        for (String op : operations){
            JButton btn = new JButton (op);
            btn.addActionListener(CalcActionListener);
            buttonPanel.add(btn);
        }

        frame.getContentPane().add(BorderLayout.WEST, numPanel);
        frame.getContentPane().add(BorderLayout.EAST, buttonPanel);
        
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu calcMenu = new JMenu("Calc");
        menuBar.add(calcMenu);
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        calcMenu.add(exitMenuItem);
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        
        frame.pack(); //rekurzivan, belulrol tulajdonkeppen utasitja a blokkokat hogy helyezodjenek el a megfelelo pozicioban meretben
        frame.setVisible(true);
    }

    ActionListener CalcActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                double op1 = Double.valueOf(operand1.getText());
                double op2 = Double.valueOf(operand2.getText());
                String operation = ((JButton)ae.getSource()).getText();
                double res = 0;
                switch (operation) {
                    case "add":
                        res = op1 + op2;
                        break;
                    case "sub":
                        res = op1 - op2;
                        break;
                    case "mul":
                        res = op1 * op2;
                        break;
                    case "div":
                        res = op1 / op2;
                        break;
                    case "pow":
                        res = Math.pow(op1, op2);
                        break;
                }
                result.setText(String.valueOf(res));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Rossz formatumu szamok!", "Hiba", JOptionPane.ERROR_MESSAGE);
            }
        }
    };
}