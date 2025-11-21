package ui;

import api.DataBaseAgendamentos;
import api.Schedule;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.sql.SQLException;
import java.util.List;

public class scheduling {

    private JList<String> futuroList;
    private JButton btnNovoAgendamento;
    public JPanel schedulingPane;
    private JLabel clientCurrent;
    private JLabel procedureCurrent;
    private JLabel detailCurrent;
    private JLabel dateTimeCurrent;
    private JLabel dentistCurrent;
    private JLabel statuCurrent;
    private JLabel clientFuture;
    private JLabel procedureFuture;
    private JLabel detailFuture;
    private JLabel dateTimeFuture;
    private JLabel dentistFuture;
    private JLabel statuFuture;
    private JPanel agendamentoHojePanel;
    private JPanel agendamentoFuturoPanel;
    private JList<String> listNameClientNow;
    private JList listTypeNow;
    private JList listDetailsNow;
    private JList listDateTimeNow;
    private JList listNameDentistNow;
    private JList listStatusNow;
    private MainScreen mainScreen;
    private DefaultListModel<String> atualModel;
    private DefaultListModel<String> futureModel;
    private List<Schedule> scheduleDB;


    public scheduling(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
        atualModel = new DefaultListModel<>();
        futureModel = new DefaultListModel<>();
        setupButton();
        listNameClientNow.addComponentListener(new ComponentAdapter() {});
        loadSchedule();
    }

    public void setupButton(){
        btnNovoAgendamento.addActionListener(e -> mainScreen.showClientes());

    }

    public void loadSchedule(){
        try {
            scheduleDB = DataBaseAgendamentos.getSchedule();
            atualModel.clear();
            for (Schedule schedule : scheduleDB) {
                atualModel.addElement(schedule.toString());
            }
            listNameClientNow.setModel(atualModel);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(schedulingPane, "Erro ao carregar clientes: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
   }
}
