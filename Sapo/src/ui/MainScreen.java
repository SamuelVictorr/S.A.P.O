package ui;

import api.Client;

import ui.Clientes;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainScreen {
    public JPanel mainPanel;
    public JPanel cardsPanel;
    private JButton btnClientes;
    private JButton btnCadastro;
    private JPanel cadastroCard;
    private JPanel clientesCard;

    public Clientes clientesPanelInstance;
    private JPanel clientesPanel;
    public JTextField searchField;
    private JList<String> clientList;
    private DefaultListModel<String> listModel;
    private List<Client> clientsDB;

    public register contentPaneInstance;
    private JPanel contentPane;
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

    private CardLayout cardLayout;

    public MainScreen() {
        initializePanels();
        setupNavigation();
        clientesPanelInstance.loadAllClients();
        showClientes();
    }

    private void initializePanels() {
        cardLayout = (CardLayout) cardsPanel.getLayout();

        clientesPanelInstance = new Clientes();
        contentPaneInstance = new register();
        clientesCard.removeAll();
        cadastroCard.removeAll();

        clientesCard.add(clientesPanelInstance.clientesPanel);
        cadastroCard.add(contentPaneInstance.contentPane);

        clientesCard.revalidate();
        clientesCard.repaint();
        cadastroCard.revalidate();
        cadastroCard.repaint();
    }

    private void setupNavigation() {
        btnClientes.addActionListener(e -> showClientes());
        btnCadastro.addActionListener(e -> showCadastro());
    }

    public void showClientes() {
        cardLayout.show(cardsPanel, "clientesCard");
    }

    public void showCadastro() {
        contentPaneInstance.clearClientsField();
        cardLayout.show(cardsPanel, "cadastroCard");
    }
}