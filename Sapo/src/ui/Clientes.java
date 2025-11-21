package ui;

import api.Client;
import api.DataBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class Clientes {
    public JPanel clientesPanel;
    public JTextField searchField;
    private JList<String> clientList;
    private JButton btnNovoCadastro;
    private JPanel searchPanel;
    private DefaultListModel<String> listModel;
    private List<Client> clientsDB;
    private MainScreen mainScreen;

    public Clientes(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
        listModel = new DefaultListModel<>();
        initializeComponents();
        setupListeners();
    }

    private void initializeComponents() {
        clientList.setModel(listModel);
        searchField.setText("🔍 Digite o nome do cliente:");
        setupButtons();
    }

    private void setupButtons(){
        btnNovoCadastro.addActionListener(e -> {
            mainScreen.showCadastro();
        });
    }

    private void setupListeners() {
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String searchText = searchField.getText().toLowerCase().trim();

                if (searchText.isEmpty()) {
                    listModel.clear();
                    for (Client client : clientsDB) {
                        listModel.addElement(client.toString());
                    }
                } else {
                    searchClients(searchText);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (searchField.getText().equals("🔍 Digite o nome do cliente:")) {
                    searchField.setText("");
                }
            }
        });

        clientList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel boxClient = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (!isSelected) {
                    boxClient.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
                }
                else {
                    boxClient.setBorder(BorderFactory.createLineBorder(new Color(100, 150, 255), 2));
                }

                boxClient.setOpaque(false);
                boxClient.setBackground(Color.WHITE);
                return boxClient;
            }
        });

        clientList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clientIndex = clientList.getSelectedIndex();
                if (clientIndex >= 0) {
                    String clientString = listModel.getElementAt(clientIndex);
                    Client clientSelected = searchClientString(clientString);
                    System.out.println(clientString);
                    System.out.println(clientSelected);
                    if(clientSelected != null){
                        mainScreen.showCustomerInformation(clientSelected);
                        mainScreen.showToUpdateCLientData(clientSelected);
                    }
                }
                System.out.println("teste de puxar o clientInformation");
                System.out.println(clientIndex);
            }
        });
    }

    private Client searchClientString(String clientString) {
        if (clientsDB != null) {
            for (Client client : clientsDB) {
                if (client.toString().equals(clientString)) {
                    return client;
                }
            }
        }
        return null;
    }

    public void loadAllClients() {
        try {
            clientsDB = DataBase.getClients();
            listModel.clear();
            for (Client client : clientsDB) {
                listModel.addElement(client.toString());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(clientesPanel, "Erro ao carregar clientes: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Search function, sa prr procura tudo
    private void searchClients(String searchText) {
        listModel.clear();
        if (clientsDB != null) {
            for (Client client : clientsDB) { // verificação 1:1 de info dos clientes no banco de dados
                boolean search = client.getName().toLowerCase().contains(searchText) || client.getCpf().toLowerCase().contains(searchText);
                if (search) {
                    listModel.addElement(client.toString());
//                    searchText = client.getName();
                }
            }
        }
    }
}