package ui;

import api.Client;
import api.DataBase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import static api.DataBase.updateClient;

public class customerInformation {
    private JLabel customerInformationLabel;
    private JList consultationHistoryList;
    private JLabel nmcLabel;
    private JLabel nameLabel;
    private JLabel fixedTeleLabel;
    private JLabel fixedAgeLabel;
    private JLabel ageLabel;
    private JLabel telephoneLabel;
    private JLabel nextAppointmentLabel;
    private JLabel dateLabel;
    private JLabel timeLabel;
    private JLabel procedureLabel;
    private JLabel detailLabel;
    private JLabel dentistLabel;
    private JLabel statusLabel;
    private JList nextAppointmentList;
    private JLabel consultationHistoryLabel;
    private JButton returnButton;
    private JButton editButton;
    private JPanel buttonPane;
    private JPanel customerInformationPane;
    private JPanel nextAppointmentPane;
    private JPanel consultationHistoryPane;
    public JPanel infoClientsPanel;
    private MainScreen mainScreen;

    public customerInformation(MainScreen mainScreen){
        this.mainScreen = mainScreen;
        setupButtons(mainScreen);
    }

    public void setupButtons(MainScreen mainScreen){
        this.mainScreen = mainScreen;
        returnButton.addActionListener(event -> mainScreen.showClientes());
        editButton.addActionListener(event -> {
            Client client = mainScreen.getStoreClient();
            if (client != null){
            editClient dialog = new editClient(mainScreen, client);
            dialog.setVisible(true);
        } else {
                System.out.println("debuug teste");;
            }
        });
    }

    public void loadCustomersInformations(Client client) {
        if (client != null) {
            nameLabel.setText(client.getName());
            telephoneLabel.setText(client.getTelefone());
            ageLabel.setText(birthValueConversion(client));
        }
    }

    public String birthValueConversion(Client client){
        String birthYear = client.getBirthDate().substring(6,10);
        int birthYearConversion = Integer.parseInt(birthYear);
        int actualYear = java.time.Year.now().getValue();
        int age = actualYear - birthYearConversion;
        return String.valueOf(age);

    }
}
