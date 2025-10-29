package ui;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
    private JLabel cadastre;
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
                System.out.println(TextoFieldName.getText());
            }
        });
        TextoFieldCPF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (TextoFieldCPF.getText().equals("Digite o CPF do cliente")) {
                    TextoFieldCPF.setText("");
                }
                System.out.println(TextoFieldCPF.getText());
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
                System.out.println(TextoFieldRemark.getText());
            }
        });
    }
}

