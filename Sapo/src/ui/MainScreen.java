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
    private JPanel navegationPanel;
    private JLabel textSAPO;
    private JLabel textUser;

    public Clientes clientesPanelInstance;
    public register contentPaneInstance;
    private CardLayout cardLayout;

    public MainScreen() {
        initializePanels();
        setupNavigation();
        clientesPanelInstance.loadAllClients();
        showClientes();
    }

    private void initializePanels() {
        cardLayout = (CardLayout) cardsPanel.getLayout();

        clientesPanelInstance = new Clientes(this);
        contentPaneInstance = new register(this);

        clientesCard.removeAll();
        cadastroCard.removeAll();
        clientesCard.setLayout(new BorderLayout());
        cadastroCard.setLayout(new BorderLayout());
        clientesCard.add(clientesPanelInstance.clientesPanel, BorderLayout.CENTER);
        cadastroCard.add(contentPaneInstance.contentPane, BorderLayout.CENTER);
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
        btnClientes.setBackground(new Color(122, 241,168));
        btnCadastro.setBackground(new Color(219, 252,231));
    }

    public void showCadastro() {
        contentPaneInstance.clearClientsField();
        cardLayout.show(cardsPanel, "cadastroCard");
        btnClientes.setBackground(new Color(219, 252,231));
        btnCadastro.setBackground(new Color(122, 241,168));
    }
    public void refreshClientList() {
        clientesPanelInstance.loadAllClients();
    }
}