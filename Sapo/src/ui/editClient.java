package ui;


import api.Client;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

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
    private List<Client> clientsDB;

    public editClient(MainScreen mainScreen) {
        this.mainScreen = mainScreen;

    }

    public void toUpdateClientData(Client clientToUpdate) {
        if (clientToUpdate != null) {
            textField3.setText(clientToUpdate.getName());
            textField1.setText(clientToUpdate.getTelefone());
            textField4.setText(clientToUpdate.getBirthDate());
            textField2.setText(clientToUpdate.getCpf());
        }
    }
}
