package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;

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
    private JLabel statuLabel;
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
    }
}
