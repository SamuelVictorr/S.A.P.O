package ui;

import api.Client;

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
    private JPanel imagemCard;
    private JPanel customerInformationCard;

    public Clientes clientesPanelInstance;
    public register contentPaneInstance;
    public scheduling schedulingPanelInstance;
    public customerInformation customerInformationInstance;
    private CardLayout cardLayout;

    public MainScreen() {
        initializePanels();
        setupNavigation();
        clientesPanelInstance.loadAllClients();
        showMenuPrincipal();
        setupImageCard();
    }

    private void initializePanels() {
        cardLayout = (CardLayout) cardsPanel.getLayout();

        clientesPanelInstance = new Clientes(this);
        contentPaneInstance = new register(this);
        schedulingPanelInstance = new scheduling(this);
        customerInformationInstance = new customerInformation(this);

        clientesCard.removeAll();
        cadastroCard.removeAll();
        agendamentoCard.removeAll();
        imagemCard.removeAll();
        customerInformationCard.removeAll();
        clientesCard.setLayout(new BorderLayout());
        cadastroCard.setLayout(new BorderLayout());
        agendamentoCard.setLayout(new BorderLayout());
        imagemCard.setLayout(new BorderLayout());
        customerInformationCard.setLayout(new BorderLayout());
        clientesCard.add(clientesPanelInstance.clientesPanel, BorderLayout.CENTER);
        cadastroCard.add(contentPaneInstance.contentPane, BorderLayout.CENTER);
        agendamentoCard.add(schedulingPanelInstance.schedulingPane, BorderLayout.CENTER);
        customerInformationCard.add(customerInformationInstance.infoClientsPanel, BorderLayout.CENTER);
        clientesCard.revalidate();
        clientesCard.repaint();
        cadastroCard.revalidate();
        cadastroCard.repaint();
        agendamentoCard.revalidate();
        agendamentoCard.repaint();
        imagemCard.revalidate();
        imagemCard.repaint();
        customerInformationCard.revalidate();
        customerInformationCard.repaint();

    }
    private void setupImageCard() {
        try {
            ImageIcon menuImage = new ImageIcon("Sapo/src/api/imagem/menuImagem.png");
            JLabel imageLabel = new JLabel() {
                @Override
                public void paintComponent(Graphics g) {
                    g.drawImage(menuImage.getImage(), 0, 0, getWidth(), getHeight(), this);
                }
            };

            imageLabel.setHorizontalAlignment(JLabel.CENTER);
            imageLabel.setVerticalAlignment(JLabel.CENTER);
            imageLabel.setPreferredSize(new Dimension(800, 600));

            imagemCard.setLayout(new BorderLayout());
            imagemCard.add(imageLabel, BorderLayout.CENTER);

        } catch (Exception e) {
            System.out.println("Não carregou a imagem.");
            JOptionPane.showMessageDialog(cardsPanel, "Erro ao carregar a imagem do menu " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setupNavigation() {
        btnClientes.addActionListener(e -> showClientes());
        btnCadastro.addActionListener(e -> showCadastro());
        btnAgendamento.addActionListener( e -> showAgendamento());
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
        btnAgendamento.setBackground(new Color(219,252,232));
    }

    public void showCadastro() {
        contentPaneInstance.clearClientsField();
        contentPaneInstance.initializeComponents();
        cardLayout.show(cardsPanel, "cadastroCard");
        btnClientes.setBackground(new Color(219, 252,231));
        btnCadastro.setBackground(new Color(122, 241,168));
        btnAgendamento.setBackground(new Color(219, 252, 232));
    }

    public void showAgendamento(){
        cardLayout.show(cardsPanel, "agendamentoCard");
        btnClientes.setBackground(new Color(219, 252,231));
        btnCadastro.setBackground(new Color(219, 252,232));
        btnAgendamento.setBackground(new Color(122, 241, 168));
    }
    public void showCustomerInformation(Client clientSelected){
        customerInformationInstance.loadCustomersInformations(clientSelected);
        cardLayout.show(cardsPanel, "customerInformationCard");
    }
    public void showMenuPrincipal(){
        cardLayout.show(cardsPanel, "imagemCard");
    }
    public void refreshClientList() {
        clientesPanelInstance.loadAllClients();
    }
}