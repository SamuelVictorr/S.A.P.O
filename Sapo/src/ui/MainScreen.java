package ui;

import api.Client;
import api.Schedule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
    private Schedule schedule;
    private boolean schedulingMode = false;
    private CardLayout cardLayout;
    private Client storeClient;

    //Constructor for the MainScreen class
    public MainScreen() {
        initializePanels();
        setupNavigation();
        clientesPanelInstance.loadAllClients();
        showMenuPrincipal();
        setupImageCard();
        applyNimbusToNavigationButtons();
    }

    //Initiate Panels and set them all "null" (without info on them)
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

    //Set default frog image which is shown when le program is started
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

    //Set navigation to work
    private void setupNavigation() {
        styleNavigationButton(btnClientes, "Clientes");
        styleNavigationButton(btnCadastro, "Cadastrar");
        styleNavigationButton(btnAgendamento, "Agendamento");

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

    //Auxiliar function for styleNavigationButton
    private String getCurrentCard() {
        CardLayout layout = (CardLayout) cardsPanel.getLayout();

        if (btnClientes.getBackground().equals(new Color(122, 241, 168))) {
            return "clientesCard";
        } else if (btnCadastro.getBackground().equals(new Color(122, 241, 168))) {
            return "cadastroCard";
        } else if (btnAgendamento.getBackground().equals(new Color(122, 241, 168))) {
            return "agendamentoCard";
        }
        return "imagemCard";
    }

    //Front-end effects
    private void styleNavigationButton(JButton button, String text) {
        button.setText(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));

        button.setBackground(new Color(219, 252,231));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //Hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(122, 241, 168));
                button.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (button == btnClientes && getCurrentCard().equals("clientesCard")) {
                    button.setBackground(new Color(219, 252,231));
                } else if (button == btnCadastro && getCurrentCard().equals("cadastroCard")) {
                    button.setBackground(new Color(219, 252,231));
                } else if (button == btnAgendamento && getCurrentCard().equals("agendamentoCard")) {
                    button.setBackground(new Color(219, 252,231));
                } else {
                    button.setBackground(new Color(219, 252,231));
                }
                button.setForeground(Color.BLACK);
            }
        });
    }

    //Front-end effects 2
    private void applyNimbusToNavigationButtons() {
        try {
            LookAndFeel currentLAF = UIManager.getLookAndFeel();

            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

            SwingUtilities.updateComponentTreeUI(btnClientes);
            SwingUtilities.updateComponentTreeUI(btnCadastro);
            SwingUtilities.updateComponentTreeUI(btnAgendamento);
            SwingUtilities.updateComponentTreeUI(btnSair);
            SwingUtilities.updateComponentTreeUI(navegationPanel);

            UIManager.setLookAndFeel(currentLAF);

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    //Updates color for the buttons
    private void updateButtonStates(JButton activeButton) {
        btnClientes.setBackground(new Color(219, 252,231));
        btnCadastro.setBackground(new Color(219, 252,231));
        btnAgendamento.setBackground(new Color(219, 252,231));

        activeButton.setBackground(new Color(122, 241, 168));
    }

    //Set schedulingMode On/Activated
    public void schedulingModeActivated(){
        this.schedulingMode = true;
        this.showClientes();

        clientesPanelInstance.searchField.setText("MODO AGENDAMENTO - CLIQUE EM UM CLIENTE PARA FAZER UMA CONSULTA:");
        clientesPanelInstance.searchField.setBackground(Color.YELLOW);

        JOptionPane.showMessageDialog(mainPanel, "Clique em um cliente para continuar o agendamento.", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
    }

    //Set schedulingMode off/false as you want to call
    public void setSchedulingModeFalse(){
        clientesPanelInstance.searchField.setBackground(Color.WHITE);
        clientesPanelInstance.searchField.setText("🔍 Digite o nome do cliente:");
        this.schedulingMode = false;
    }

    //Return schedulingMode status (if it's false/true)
    public boolean isSchedulingMode(){
        return schedulingMode;
    }

    public Client getStoreClient() {
        return this.storeClient;
    }

    // Set client selected or the client which got his info's changed updated in main screen
    public void setStoreClient(Client client){
        this.storeClient = client;
    }

    //Shows all clients
    public void showClientes() {
        cardLayout.show(cardsPanel, "clientesCard");
        updateButtonStates(btnClientes);
    }

    //Shows register form to add clients in DB
    public void showCadastro() {
        setSchedulingModeFalse();
        contentPaneInstance.clearClientsField();
        contentPaneInstance.initializeComponents();
        cardLayout.show(cardsPanel, "cadastroCard");
        updateButtonStates(btnCadastro);
    }

    //Shows any attendance registered in DB
    public void showAgendamento(){
        setSchedulingModeFalse();
        schedulingPanelInstance.setFieldsSchedule();
        schedulingPanelInstance.loadSchedule();
        cardLayout.show(cardsPanel, "agendamentoCard");
        updateButtonStates(btnAgendamento);
    }

    //Shows Customer Info about the client selected
    public void showCustomerInformation(Client clientSelected){
        this.storeClient = clientSelected;
        customerInformationInstance.loadCustomersInformations(clientSelected);
        customerInformationInstance.loadScheduleClient(clientSelected);
        cardLayout.show(cardsPanel, "customerInformationCard");
    }

    //Show Image Menu
    public void showMenuPrincipal(){
        cardLayout.show(cardsPanel, "imagemCard");
    }

    public void refreshClientList() {
        clientesPanelInstance.loadAllClients();
    }
}