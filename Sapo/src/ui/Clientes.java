package ui;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import api.DataBase;
import api.Client;
import java.sql.SQLException;

public class Clientes {
    public JPanel clientesPanel;
    public JTextField searchField;
    private JList<String> clientList;
    private final DefaultListModel<String> listModel;
    private List<Client> clientsDB;

    public Clientes() {
        listModel = new DefaultListModel<>();
        clientList.setModel(listModel);

        searchField.setText("Digite o nome do cliente:");

        loadAllClients();

        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String searchText = searchField.getText().toLowerCase().trim();
                //se o campo estiver vázio, mostra todos os clientes
                if (searchText.isEmpty() || searchText.equals("digite o nome do cliente:")) {
                    loadAllClients();
                } else {
                    searchClient(searchText);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //se o campo começar a ser escrito, ele fica vázio
                if (searchField.getText().equals("Digite o nome do cliente:")) {
                    searchField.setText("");
                }
            }
        });
    }

    private void loadAllClients() {
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

    private void searchClient(String searchText) {
        listModel.clear();
        for (Client client : clientsDB) {
            boolean search = client.getName().toLowerCase().contains(searchText) || client.getCpf().toLowerCase().contains(searchText);
            if(search){
                listModel.addElement(client.toString());
            }
        }
    }
}
