package ui;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

import static api.DataBase.addClient;
import static api.DataBase.verifyCPF;

public class register extends JDialog {
    private JPanel contentPane;

    // Jbutton to activate method addclient
    private JButton register;

    // JTextField that receives customer date
    private JTextField namefield;
    private JTextField telephonefield;
    private JTextField CPFfield;
    private JTextField birthfield;
    private JTextField Fieldobser;

    // JLabels that receives customer date
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
                String name = namefield.getText();
                String CPF = CPFfield.getText();
                String telephone = telephonefield.getText();
                String obser = Fieldobser.getText();
                String birth = birthfield.getText();

                //method call verify CPF top DataBase,to verify if the CFP is valid.
                if(!verifyCPF(CPF)){
                    System.out.println("CPF Invalido!");
                    JOptionPane.showMessageDialog(null, "CPF Inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    addClient(name,CPF,telephone,obser);

                    //Clear the TextFields after adding a client.
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
        birthfield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                String birth = birthfield.getText().replaceAll("[^0-9]", "");
                if (birth.length() > 8) {
                    birth = birth.substring(0, 7);
                }

                StringBuilder formatBirth = new StringBuilder();
                for (int i = 0; i < birth.length(); i++) {
                    if (i == 2) {
                        formatBirth.append('/');
                    }
                    if (i == 4){
                        formatBirth.append('/');
                    }
                    formatBirth.append(birth.charAt(i));
                }
                birthfield.setText(formatBirth.toString());

            }
        });
    }

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
