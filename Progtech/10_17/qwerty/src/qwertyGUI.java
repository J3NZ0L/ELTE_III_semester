import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class qwertyGUI {
    private JFrame frame;
    private JPanel keyBoardPanel;
    private JTextField textField;
    private boolean capsLockEnabled;

    public qwertyGUI(){
        frame = new JFrame("QWERTY Keyboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(800, 200));

        keyBoardPanel = new JPanel();
        String buttonChars[] = {"Q", "W", "E", "R", "T", "Y"};
        keyBoardPanel.setLayout(new GridLayout(1, buttonChars.length+2));
        for (String c : buttonChars) {
            JButton btn = new JButton (c);
            btn.addActionListener(KeyboardActionListener);
            keyBoardPanel.add(btn);
        }
        JButton backSpace = new JButton("<#");
        JButton clr = new JButton("CLR");
        JButton capsLock = new JButton("CAPS LOCK");
        backSpace.addActionListener(KeyboardActionListener);
        clr.addActionListener(KeyboardActionListener);
        capsLock.addActionListener(KeyboardActionListener);
        keyBoardPanel.add(backSpace);
        keyBoardPanel.add(clr);
        keyBoardPanel.add(capsLock);
        keyBoardPanel.setMinimumSize(new Dimension(100, 20));
        keyBoardPanel.setPreferredSize(new Dimension(100, 20));


        textField = new JTextField();
        textField.setMinimumSize(new Dimension(400, 100));
        textField.setFont(new Font("Serif",Font.BOLD,30));
        textField.setForeground(Color.GRAY);


        frame.getContentPane().add(BorderLayout.NORTH, textField);
        frame.getContentPane().add(BorderLayout.CENTER, keyBoardPanel);


        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu qwertyMenu = new JMenu("Qwerty");
        menuBar.add(qwertyMenu);
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        qwertyMenu.add(exitMenuItem);
        exitMenuItem.addActionListener(ae -> System.exit(0));

        frame.pack();
        frame.setVisible(true);
    }

    ActionListener KeyboardActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                String key = ((JButton)e.getSource()).getText();
                if (key.equals("CLR")){
                    textField.setText("");
                    return;
                }

                String currentText=textField.getText();

                if (key.equals("<#")){
                    if (currentText == null || currentText.length() == 0) {
                        return;
                    }
                    textField.setText( currentText.substring(0, currentText.length()-1));
                    return;
                }

                textField.setText(currentText+key);
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            };
        }
    };
}
