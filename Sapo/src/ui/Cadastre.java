package ui;

import api.*;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class Cadastre {
    public JPanel clientesPanel;
    public JTextField TextoFieldName;
    private JLabel RemarkClient;
    private JLabel NameClient;
    private JTextField TextoFieldCPF;
    private JTextField TextoFieldTelephone;
    private JTextField TextoFieldRemark;
    private JLabel CPFClient;
    private JLabel TelephoneClient;
    private JButton ButtonCadastre;
    private JList clientList;


    public void setList(ArrayList<String> list) {
        clientList.setListData(list.toArray());
    }

    public Cadastre() {
        TextoFieldName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (TextoFieldName.getText().equals("Digite o nome do cliente")) {
                    TextoFieldName.setText("");
                }
            }
        });


        TextoFieldCPF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (TextoFieldCPF.getText().equals("Digite o CPF do cliente")) {
                    TextoFieldCPF.setText("");
                }
            }
        });

        TextoFieldTelephone.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (TextoFieldTelephone.getText().equals("Digite o Telefone do cliente")) {
                    TextoFieldTelephone.setText("");
                }
                System.out.println(TextoFieldTelephone.getText());
            }
        });

        TextoFieldRemark.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                    if (TextoFieldRemark.getText().equals("Digite a Observação do cliente")) {
                    TextoFieldRemark.setText("");
                }
            }
        });

        ButtonCadastre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) throws StringIndexOutOfBoundsException{
                DataBase data = new DataBase();

                // Data input from the form
                String nameWritten = TextoFieldName.getText() + " ";
                String cpfWritten = TextoFieldCPF.getText() + " ";
                String phoneWritten = TextoFieldTelephone.getText() + " ";
                String obsWritten = TextoFieldRemark.getText() + " ";

                // Data which will be added in DB
                StringBuilder nameToDB = new StringBuilder();
                StringBuilder cpfToDB = new StringBuilder();
                StringBuilder phoneToDB = new StringBuilder();
                StringBuilder observationToDB = new StringBuilder();

                // Fucking 4 for statements to get the last char, smh it make the PC explode
                for (int i = 0; i < nameWritten.length(); i++) {
                    nameToDB.append(nameWritten.charAt(i));
                }
                for (int i = 0; i < cpfWritten.length(); i++) {
                    cpfToDB.append(cpfWritten.charAt(i));
                }
                for (int i = 0; i < phoneWritten.length(); i++) {
                    phoneToDB.append(phoneWritten.charAt(i));
                }
                for (int i = 0; i < obsWritten.length(); i++) {
                    observationToDB.append(obsWritten.charAt(i));
                }

                // Do the shittest shit of the earth, it makes the function addClient works
                try {
                    DataBase.addClient(nameToDB.toString(), cpfToDB.toString(), phoneToDB.toString(), observationToDB.toString());
                } catch (SQLException exception) {
                    throw new RuntimeException(exception);
                }
            }
        });
    }
}

