package ui;

import api.DataBaseAgendamentos;
import api.Schedule;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.List;

public class scheduling {

    private JButton btnNovoAgendamento;
    public JPanel schedulingPane;
    private JPanel agendamentoHojePanel;
    private JPanel agendamentoFuturoPanel;
    private JList<String> listNameClientNow;
    private JList listTypeNow;
    private JList listDetailsNow;
    private JList listDateTimeNow;
    private JList listNameDentistNow;
    private JList listStatusNow;
    private MainScreen mainScreen;
    private List<Schedule> scheduleDB;
    private JTable tableFuture;
    private JTable tableNow;
    private JScrollPane scrollPanel;
    private DefaultTableModel tableFutureModel;
    private DefaultTableModel tableNowModel;


    public scheduling(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
        tableNowModel = new DefaultTableModel(new Object[]{"Cliente", "Procedimento", "Detalhe", "Data/Horário", "Dentista", "Status"}, 0);
        tableFutureModel = new DefaultTableModel(new Object[]{"Cliente","Procedimento","Detalhe","Data/Horário","Dentista","Status"}, 0);
        setupButton();
        loadSchedule();
    }

    public void setupButton(){
        btnNovoAgendamento.addActionListener(e -> mainScreen.showClientes());
    }

    public void loadSchedule(){
        try {
            scheduleDB = DataBaseAgendamentos.getSchedule();
            for (Schedule schedule : scheduleDB) {
                String dataHora = schedule.getDiaHora();
                String date = dataHora.substring(0,10);
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
                LocalDate dateNow = LocalDate.now();
                LocalDate parseDate = LocalDate.parse(date, dateFormat);

                if(parseDate.isEqual(dateNow)) {
                    tableNowModel.addRow(new Object[]{
                            schedule.getIdClient(),
                            schedule.getTypeTreatment(),
                            schedule.getDetails(),
                            schedule.getDiaHora(),
                            schedule.getNameDentist(),
                            schedule.getStatusTreatment()
                    });
                }
                if(parseDate.isAfter(dateNow)){
                    tableFutureModel.addRow(new Object[]{
                            schedule.getIdClient(),
                            schedule.getTypeTreatment(),
                            schedule.getDetails(),
                            schedule.getDiaHora(),
                            schedule.getNameDentist(),
                            schedule.getStatusTreatment()
                    });
                }
            }
            tableNow.setModel(tableNowModel);
            tableFuture.setModel(tableFutureModel);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(schedulingPane, "Erro ao carregar clientes: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
   }

    public void searchDate(){

    }
}
