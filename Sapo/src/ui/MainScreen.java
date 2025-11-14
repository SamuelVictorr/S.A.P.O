package ui;

import javax.swing.*;
import java.awt.*;

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
    private JButton btnSair;
    private JPanel agendamentoCard;
    private JButton btnAgendamento;

    public Clientes clientesPanelInstance;
    public register contentPaneInstance;
    public scheduling SchedulingPanelInstance;
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
        SchedulingPanelInstance = new scheduling(this);

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
        btnSair.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(mainPanel, "Tem certeza que deseja sair?", "Confirmar Saída", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }

    public void showClientes() {
        cardLayout.show(cardsPanel, "clientesCard");
        btnClientes.setBackground(new Color(122, 241,168));
        btnCadastro.setBackground(new Color(219, 252,231));
    }

    public void showCadastro() {
        contentPaneInstance.clearClientsField();
        contentPaneInstance.initializeComponents();
        cardLayout.show(cardsPanel, "cadastroCard");
        btnClientes.setBackground(new Color(219, 252,231));
        btnCadastro.setBackground(new Color(122, 241,168));
    }
    public void refreshClientList() {
        clientesPanelInstance.loadAllClients();
    }
}