package ui;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import static api.DataBase.verifyCPF;
import static api.DataBase.addClient;

public class register{
    public JPanel contentPane;
    private JButton register;
    private JTextField nameField;
    private JLabel nameLabel;
    private JTextField telephoneField;
    private JLabel telephoneLabel;
    private JTextField CPFfield;
    private JLabel CPFlabel;
    private JTextField birthField;
    private JLabel birthLabel;
    private JTextField FieldObser;
    private JLabel JLabelObser;
    private JComboBox statusBox;
    private MainScreen mainScreen;

    public register(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
        setupListeners();
        setupMasks();
        initializeComponents();
    }

    public void initializeComponents(){
        nameField.setText("Nome do cliente");
        telephoneField.setText("(XX)XXXXX-XXXX");
        CPFfield.setText("XXX.XXX.XXX-XX");
        birthField.setText("XX/XX/XXXX");
        FieldObser.setText("Observações sobre o cliente");

    }

    private void setupListeners() {
        register.addActionListener(e -> saveClients());

        nameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (nameField.getText().equals("Nome do cliente")){
                    nameField.setText("");
                }
            }
        });
        telephoneField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (telephoneField.getText().equals("(XX)X XXXX-XXXX")){
                    telephoneField.setText("");
                }
            }
        });
        CPFfield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (CPFfield.getText().equals("XXX.XXX.XXX-XX")){
                    CPFfield.setText("");
                }
            }
        });
        birthField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (birthField.getText().equals("XX/XX/XXXX")){
                    birthField.setText("");
                }
            }
        });
        FieldObser.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (FieldObser.getText().equals("Observações sobre o cliente")){
                    FieldObser.setText("");
                }
            }
        });
    }

    public void clearClientsField() {
        nameField.setText("");
        telephoneField.setText("");
        CPFfield.setText("");
        birthField.setText("");
        FieldObser.setText("");
    }

    private void saveClients() {
        String name = nameField.getText();
        String CPF = CPFfield.getText();
        String telephone = telephoneField.getText();
        String observation = FieldObser.getText();
        String birth = birthField.getText();
        String status = statusBox.getSelectedItem().toString();
        String idClinic = "1";
        boolean valid;

        System.out.println(birth);
        //Validação dos dados
        valid = validClient(name,telephone,CPF,birth);
        if (!valid) {
            return;
        }

        try {
            addClient(name, CPF, telephone, observation, status, birth, idClinic);

            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            clearClientsField();
            mainScreen.refreshClientList();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean validClient(String name, String telephone, String CPF, String birth){
        //validação do Nome
        if(name.isEmpty()){
            JOptionPane.showMessageDialog(null, "Nome é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }else if(name.equals("Nome do cliente")){
            JOptionPane.showMessageDialog(null, "Nome é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        //validação do Telefone
        if(telephone.isEmpty()){
            JOptionPane.showMessageDialog(null, "Telefone é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }  else if(telephone.equals("(XX)XXXXX-XXXX")) {
            JOptionPane.showMessageDialog(null, "Telefone é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (telephone.length() < 11){
            JOptionPane.showMessageDialog(null, "Telefone é inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        //validação de CPF
        if(CPF.isEmpty()){
            JOptionPane.showMessageDialog(null, "CPF é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if(CPF.equals("XXX.XXX.XXX-XX")){
            JOptionPane.showMessageDialog(null, "CPF é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if(!verifyCPF(CPF)){
            JOptionPane.showMessageDialog(null, "CPF inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        //validação da data de nascimento
        if(birth.isEmpty()){
            JOptionPane.showMessageDialog(null, "Data de Nascimento é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if(birth.equals("XX/XX/XXXX")) {
            JOptionPane.showMessageDialog(null, "Data de Nascimento é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if(birth.length() == 10){
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
            try {
                LocalDate.parse(birth, dateFormat);
                return true;
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "O campo Data de Nascimento está inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }

    public void setupMasks() {
        CPFfield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyPressed(e);
                String cpf = CPFfield.getText().replaceAll("[^0-9]", "");
                if (cpf.length() >= 11) {
                    cpf = cpf.substring(0,11);
                }

                StringBuilder formatCPF = new StringBuilder();
                for (int i = 0; i < cpf.length(); i++) {
                    if (i == 3 || i == 6) {
                        formatCPF.append('.');
                    }
                    if (i == 9) {
                        formatCPF.append('-');
                    }
                    formatCPF.append(cpf.charAt(i));
                }
                CPFfield.setText(formatCPF.toString());
            }
        });

        telephoneField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                String telephone = telephoneField.getText().replaceAll("[^0-9]", "");
                if (telephone.length() > 11) {
                    telephone = telephone.substring(0,11);
                }
                if (telephone.length() == 10 && telephone.charAt(2) != '9') {
                    telephone = telephone.substring(0, 2) + "9" + telephone.substring(2);
                } else if (telephone.length() == 10 && telephone.charAt(3) != '9'){
                    telephone = telephone.substring(0, 2) + "9" + telephone.substring(2);
                }
                StringBuilder formatTele = new StringBuilder();
                for (int i = 0; i < telephone.length(); i++) {
                    if (i == 0) {
                        formatTele.append("(");
                    }
                    if (i == 2) {
                        formatTele.append(")");
                    }
                    if (i == 7) {
                        formatTele.append("-");
                    }
                    formatTele.append(telephone.charAt(i));
                }
                telephoneField.setText(formatTele.toString());
            }
        });
        birthField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyPressed(e);
                String birth = birthField.getText().replaceAll("[^0-9]", "");
                if (birth.length() >= 8) {
                    birth = birth.substring(0,8);
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
                birthField.setText(formatBirth.toString());

            }
        });
    }
}