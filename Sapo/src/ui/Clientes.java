package ui;

import api.Client;
import api.DataBase;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.List;

public class Clientes {
    public JPanel clientesPanel;
    public JTextField searchField;
    private JList<String> clientList;
    private DefaultListModel<String> listModel;
    private List<Client> clientsDB;

    public Clientes() {
        listModel = new DefaultListModel<>();
        initializeComponents();
        setupListeners();
    }

    public JPanel getPanel() {
        return clientesPanel;
    }

    private void initializeComponents() {
        clientList.setModel(listModel);
        searchField.setText("Digite o nome do cliente:");
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
                if (searchField.getText().equals("Digite o nome do cliente:")) {
                    searchField.setText("");
                }
            }
        });
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

    private void searchClients(String searchText) {
        listModel.clear();
        if (clientsDB != null) {
            for (Client client : clientsDB) {
                boolean search = client.getName().toLowerCase().contains(searchText) || client.getCpf().toLowerCase().contains(searchText);
                if (search) {
                    listModel.addElement(client.toString());
                }
            }
        }
    }
}