package ui;

import api.Client;
import api.DataBaseAgendamentos;
import api.Schedule;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.List;

public class customerInformation {
    private JLabel customerInformationLabel;
    private JLabel nmcLabel;
    private JLabel nameLabel;
    private JLabel fixedTeleLabel;
    private JLabel fixedAgeLabel;
    private JLabel ageLabel;
    private JLabel telephoneLabel;
    private JLabel nextAppointmentLabel;
    private JLabel consultationHistoryLabel;
    private JButton returnButton;
    private JButton editButton;
    private JPanel buttonPane;
    private JPanel customerInformationPane;
    private JPanel nextAppointmentPane;
    private JPanel consultationHistoryPane;
    public JPanel infoClientsPanel;
    private JTable tableNext;
    private JTable tableHistory;
    private List<Schedule> scheduleDB;
    private DefaultTableModel tableNextModel;
    private DefaultTableModel tableHistoryModel;

    //Classes pulled to set/get information on screen
    private MainScreen mainScreen;
    private Client client;

    //Constructor of the class
    public customerInformation(MainScreen mainScreen){
        this.mainScreen = mainScreen;
        tableNextModel = new DefaultTableModel(new Object[]{"Numero do Cadastro", "Cliente", "Procedimento", "Detalhe", "Data/Horário", "Dentista", "Status"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableHistoryModel = new DefaultTableModel(new Object[]{"Numero do Cadastro", "Cliente", "Procedimento", "Detalhe", "Data/Horário", "Dentista", "Status"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        setupButtons(mainScreen);
    }

    //Button setter to show all of them on screen
    public void setupButtons(MainScreen mainScreen){
        this.mainScreen = mainScreen;
        returnButton.addActionListener(event -> mainScreen.showClientes());
        editButton.addActionListener(event ->{
            if (this.client != null){
                editClient dialog = new editClient(mainScreen, this.client, this);
                dialog.setVisible(true);
            } else {
                System.out.println("debuug teste");;
            }
        });
    }

    //Set info on screen to it respectively label
    public void loadCustomersInformations(Client client) {
        this.client = client;
        if (client != null) {
            nameLabel.setText(client.getName());
            telephoneLabel.setText(client.getTelefone());
            ageLabel.setText(birthValueConversion(client));

        }
    }

    // Birth Converter to exactly client year agr
    public String birthValueConversion(Client client){
        String birthYear = client.getBirthDate().substring(6,10);
        int birthYearConversion = Integer.parseInt(birthYear);
        int actualYear = java.time.Year.now().getValue();
        int age = actualYear - birthYearConversion;
        return String.valueOf(age);

    }

    //Pull all clientSchedule by client's name
    public void loadScheduleClient(Client client){
        try{
            scheduleDB = DataBaseAgendamentos.getSchedules();
            tableNextModel.setRowCount(0);
            tableHistoryModel.setRowCount(0);

            for(Schedule schedule : scheduleDB){
                String dataHora = schedule.getDiaHora();
                DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/uuuu / HH:mm").withResolverStyle(ResolverStyle.STRICT);
                LocalDateTime dateNow = LocalDateTime.now();
                LocalDateTime parseDate = LocalDateTime.parse(dataHora, dateTimeFormat);

                if(schedule.getNameClient().equals(client.getName())){
                    if (parseDate.isEqual(dateNow) || parseDate.isAfter(dateNow)){
                        scheduling.getScheduleOnScreen(schedule, tableNextModel);
                    }
                    scheduling.getScheduleOnScreen(schedule, tableHistoryModel);
                }

            }

            tableNext.setModel(tableNextModel);
            tableHistory.setModel(tableHistoryModel);
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Erro ao carregar clientes: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }
}