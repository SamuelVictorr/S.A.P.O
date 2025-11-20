package ui;


import javax.swing.*;
import java.awt.event.*;

public class editClient extends JDialog {
    public JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField4;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JLabel editClintLabel;
    private JLabel nameLabel;
    private JLabel cpfLabel;
    private JLabel telephoneLabel;
    private JLabel birthLabel;
    private MainScreen mainScreen;

    public editClient(MainScreen mainScreen) {
        this.mainScreen = mainScreen;

    }
}
