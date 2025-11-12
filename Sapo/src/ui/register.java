package ui;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import static api.DataBase.verifyCPF;
import static api.DataBase.addClient;

public class register{
    public JPanel contentPane;
    private JButton register;
    private JTextField namefield;
    private JLabel namelabel;
    private JTextField telephonefield;
    private JLabel telephonelabel;
    private JTextField CPFfield;
    private JLabel CPFlabel;
    private JTextField birthfield;
    private JLabel birthlabel;
    private JTextField Fieldobser;
    private JLabel JLabelobser;
    private MainScreen mainScreen;

    public register(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
        setupListeners();
        setupMasks();
    }

    public JPanel getPanel() {
        return contentPane;
    }

    private void setupListeners() {
        register.addActionListener(e -> saveClients());
    }

    public void clearClientsField() {
        namefield.setText("");
        telephonefield.setText("");
        CPFfield.setText("");
        birthfield.setText("");
        Fieldobser.setText("");
    }

    private void saveClients() {
        String name = namefield.getText();
        String CPF = CPFfield.getText();
        String telephone = telephonefield.getText();
        String observation = Fieldobser.getText();

        if (name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nome é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!verifyCPF(CPF)) {
            JOptionPane.showMessageDialog(null, "CPF inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            addClient(name, CPF, telephone, observation);

            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            clearClientsField();
            mainScreen.refreshClientList();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setupMasks() {
        CPFfield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String cpf = CPFfield.getText().replaceAll("[^0-9]", "");
                if (cpf.length() >= 11) {
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
            public void keyReleased(KeyEvent e) {
                String telephone = telephonefield.getText().replaceAll("[^0-9]", "");
                if (telephone.length() >= 11) {
                    telephone = telephone.substring(0, 11);
                }

                StringBuilder formatTele = new StringBuilder();
                for (int i = 0; i < telephone.length(); i++) {
                    if (i == 0) {
                        formatTele.append('(');
                    }
                    if (i == 2) {
                        formatTele.append(")");
                    }
                    if (i == 7) {
                        formatTele.append('.');
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
                if (birth.length() >= 8) {
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
}