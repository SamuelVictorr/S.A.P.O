package ui;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;
import api.DataBaseAgendamentos;
import api.Schedule;

public class editStatusScheduling extends JDialog {
    private JPanel editStatusPane;
    private JRadioButton completedCheckBox;
    private JRadioButton confirmedCheckBox;
    private JRadioButton canceledCheckBox;
    private JRadioButton rescheduledCheckBox;
    private JRadioButton pendingCheckBox;
    private List<Schedule> scheduleDB;

    //constructor for the dialog shows option for change the treatmentStatus column
    public editStatusScheduling(MainScreen mainScreen) {
        setContentPane(editStatusPane);
        setModal(true);
        setTitle("Editar Status");
        setSize(500,200);
        setLocationRelativeTo(mainScreen.mainPanel);
        setResizable(false);

    }

    //Set status on treatmentSchedule column after chose on Dialog
    public void setStatus(int idSchedule){
        ButtonGroup validCheckBox = new ButtonGroup();
        validCheckBox.add(completedCheckBox);
        validCheckBox.add(confirmedCheckBox);
        validCheckBox.add(canceledCheckBox);
        validCheckBox.add(rescheduledCheckBox);
        validCheckBox.add(pendingCheckBox);

        completedCheckBox.addActionListener(e -> updateStatus("Realizado",idSchedule));
        confirmedCheckBox.addActionListener(e -> updateStatus("Confirmado",idSchedule));
        canceledCheckBox.addActionListener(e -> updateStatus("Cancelado",idSchedule));
        rescheduledCheckBox.addActionListener(e -> updateStatus("Reagendado",idSchedule));
        pendingCheckBox.addActionListener(e -> updateStatus("Pendente",idSchedule));
    }

    //Change treatmentStatus column on scheduling screen and DB
    private void updateStatus(String status, int idSchedule) {
        try {
            scheduleDB = DataBaseAgendamentos.getSchedules();
            for (Schedule schedule : scheduleDB) {
                if (idSchedule == schedule.getIdSchedule()) {
                    DataBaseAgendamentos.updateStatusSchedule(status, idSchedule);
                    dispose();
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(editStatusPane, "Erro ao atualizar status: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

}