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

                // Data input pulled from the form fields
                String nameWritten = TextoFieldName.getText() + " ";
                String cpfWritten = TextoFieldCPF.getText() + " ";
                String phoneWritten = TextoFieldTelephone.getText() + " ";
                String obsWritten = TextoFieldRemark.getText() + " ";

                // Data which will be added in DB, it's for transform the data on the field into a properly words
                StringBuilder nameToDB = new StringBuilder();
                StringBuilder cpfToDB = new StringBuilder();
                StringBuilder phoneToDB = new StringBuilder();
                StringBuilder observationToDB = new StringBuilder();

                // each for takes the last char of the field where the data got placed
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

                //if sequence to verify the data before that got insert on DB
                if (DataBase.verifyText(nameToDB.toString()) && DataBase.verifyCPF(cpfToDB.toString())){
                    try {
                        DataBase.addClient(nameToDB.toString(), cpfToDB.toString(), phoneToDB.toString(), observationToDB.toString());
                    } catch (SQLException exception) {
                        throw new RuntimeException(exception);
                    }
                } else {
                    JFrame telaMensagemErro = new JFrame("Mensagem de erro");
                    telaMensagemErro.setBounds(240, 250, 400, 300);
                    telaMensagemErro.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    telaMensagemErro.setVisible(true);
                    telaMensagemErro.setLayout(null);

                    JLabel messageError = new JLabel("Nome ou Cpf inválidos: " );
                    JLabel errType1 = new JLabel(" \n 1. Veja se o nome está com caracteres especiais");
                    JLabel errType2 = new JLabel(" \n 2. Veja se o cpf está com algum número errado");
                    messageError.setBounds(10, 0, 300, 30);
                    errType1.setBounds(10, 20, 300, 30);
                    errType2.setBounds(10, 50, 300, 30);

                    JButton closeButton = new JButton("Ok");
                    closeButton.setBounds(170, 200, 50, 40);
                    closeButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            telaMensagemErro.dispose();
                        }
                    });


                    telaMensagemErro.add(messageError);
                    telaMensagemErro.add(closeButton);
                    telaMensagemErro.add(errType1);
                    telaMensagemErro.add(errType2);
                }


                //It makes the function addClient works
            }
        });
    }
}

