package ui;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.*;
import java.net.http.WebSocket;
import java.sql.SQLException;
import java.text.ParseException;

import static api.DataBase.addClient;
import static api.DataBase.verifyCPF;

public class register extends JDialog {
    private JPanel contentPane;
    private JButton register;
    private JTextField namefield;
    private JTextField telephonefield;
    private JTextField CPFfield;
    private JTextField birthfield;
    private JTextField Fieldobser;
    private JLabel namelabel;
    private JLabel telephonelabel;
    private JLabel CPFlabel;
    private JLabel birthlabel;
    private JLabel JLabelobser;

    public register() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(register);
        setTitle("Cadastro");

        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Funiciona");
                String name = namefield.getText();
                String CPF = CPFfield.getText();
                String telephone = telephonefield.getText();
                String obser = Fieldobser.getText();
                String birth = birthfield.getText();

                if(!verifyCPF(CPF)){
                    System.out.println("CPF Invalido!");
                    JOptionPane.showMessageDialog(null, "CPF Inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    addClient(name,CPF,telephone,obser);
                    namefield.setText("");
                    CPFfield.setText("");
                    telephonefield.setText("");
                    Fieldobser.setText("");
                    birthfield.setText("");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        /*buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });*/

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        CPFfield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                    String cpf = CPFfield.getText().replaceAll("[^0-9]", "");

                    if (cpf.length() > 11) {
                        cpf = cpf.substring(0, 10);
                    }

                    StringBuilder formatCPF = new StringBuilder();
                    for (int i = 0; i < cpf.length(); i++) {
                        if (i == 3 || i == 6) {
                            formatCPF.append('.');
                        }
                        if (i == 9) {
                            formatCPF.append('.');
                        }
                        formatCPF.append(cpf.charAt(i));
                    }
                    CPFfield.setText(formatCPF.toString());
            }
        });
        telephonefield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                String telephone = telephonefield.getText().replaceAll("[^0-9]", "");
                if (telephone.length() > 11) {
                    telephone = telephone.substring(0, 10);
                }

                StringBuilder formatTele = new StringBuilder();
                for (int i = 0; i < telephone.length(); i++) {
                    if (i == 0) {
                        formatTele.append('(');
                    }
                    if (i == 2){
                        formatTele.append(')');
                    }
                    if (i == 3 || i == 7) {
                        formatTele.append(' ');
                    }
                    formatTele.append(telephone.charAt(i));
                }
                telephonefield.setText(formatTele.toString());
            }
        });
    }

    /*public String validTelephone(String telephone){

        String telephone1 = telephone.replace("(","");
        String telephone2 = telephone1.replace(")","");
        int telephoneLength = telephone2.length();

        if(telephoneLength == 13){
            return telephone2;
        } else {
            return "Telefone invalido!";
        }
    }*/

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        register dialog = new register();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
