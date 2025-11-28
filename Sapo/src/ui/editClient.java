package ui;

import api.Client;
import api.DataBase;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import static ui.register.validInformation;

public class editClient extends JDialog {
    public JPanel contentPane;
    private JButton btnRemove;
    private JButton btnSave;
    private JTextField birthField;
    private JTextField telephoneField;
    private JTextField cpfField;
    private JTextField nameField;
    private JLabel editClientLabel;
    private JLabel nameLabel;
    private JLabel cpfLabel;
    private JLabel telephoneLabel;
    private JLabel birthLabel;
    private MainScreen mainScreen;
    private Client client;
    private customerInformation customerInfo;

    public editClient(MainScreen mainScreen, Client client, customerInformation customerInfo) {
        this.mainScreen = mainScreen;
        this.client = client;
        this.customerInfo = customerInfo;
        setContentPane(contentPane);
        setModal(true);
        setTitle("Editar Paciente");
        setSize(500, 400);
        setLocationRelativeTo(mainScreen.mainPanel);
        setResizable(false);

        loadCustomersInformationsEdit(client);
        setupButtons();
        setupMasksEdit();
    }
    public void setupButtons() {
        btnRemove.addActionListener(e -> removeClient());
        btnSave.addActionListener(e -> saveInformations());

    }
    public void loadCustomersInformationsEdit(Client client){
        nameField.setText(client.getName());
        cpfField.setText(client.getCpf());
        telephoneField.setText(client.getTelefone());
        birthField.setText(client.getBirthDate());

    }
    public void removeClient() {
            int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja remover o cliente?", "Confirmar Remoção", JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    DataBase.removeClient(String.valueOf(client.getId()));
                    JOptionPane.showMessageDialog(this, "Cliente removido com sucesso!");
                    mainScreen.refreshClientList();
                    dispose();
                    mainScreen.showClientes();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao remover cliente: " + ex.getMessage());
                }
            }
            else{
                dispose();
            }
    }

    public void saveInformations(){
        String name = nameField.getText();
        String CPF = cpfField.getText();
        String telephone = telephoneField.getText();
        String birth = birthField.getText();
        if (!validInformation(name, telephone, CPF, birth)) {
            return;
        }

        try {
            DataBase.updateClient(
                    name, CPF, telephone,
                    client.getObservacao(),
                    client.getActiveStatus(),
                    birth,
                    client.getClinicId(),
                    String.valueOf(client.getId())
            );

            JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso!");
            mainScreen.refreshClientList();

            Client updatedClient = new Client(client.getId(), name, CPF, telephone, client.getObservacao(), client.getActiveStatus(), birth, client.getClinicId());

            mainScreen.setStoreClient(updatedClient);
            if(customerInfo != null){
                customerInfo.loadCustomersInformations(updatedClient);
            }

            dispose();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar cliente: " + ex.getMessage());
        }
    }
    public void setupMasksEdit() {
        cpfField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyPressed(e);
                String cpf = cpfField.getText().replaceAll("[^0-9]", "");
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
                cpfField.setText(formatCPF.toString());
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

