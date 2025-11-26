package ui;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

import api.DataBaseAgendamentos;
import api.Schedule;

public class editStatusScheduling extends JDialog {
    private JPanel editStatusPane;
    private JButton buttonEdit;
    private JPanel buttonPane;
    private JRadioButton completedCheckBox;
    private JRadioButton confirmedCheckBox;
    private JRadioButton canceledCheckBox;
    private JRadioButton rescheduledCheckBox;
    private JRadioButton pendingCheckBox;
    private JButton buttonCancel;
    private List<Schedule> scheduleDB;
    private String status;
    private scheduling scheduling;

    public editStatusScheduling(MainScreen mainScreen) {
        setContentPane(editStatusPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonEdit);
        setTitle("Editar Status");
        setSize(500,200);
        setLocationRelativeTo(mainScreen.mainPanel);
        setResizable(false);

    }

    public void setStatus(StringBuilder idSchedule){
        try {
            scheduleDB = DataBaseAgendamentos.getSchedules();
            String StrIdSchedule = idSchedule.toString();
            int intIdSchedule = Integer.parseInt(StrIdSchedule);
            completedCheckBox.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    status = "Realizado";
                    for (Schedule schedule : scheduleDB) {
                        if (intIdSchedule == schedule.getIdSchedule()) {
                            try {
                                DataBaseAgendamentos.updateSchedule(schedule.getDiaHora(), schedule.getTypeTreatment(), schedule.getClient().getId(), schedule.getIdDentista(), status, schedule.getDetails(), schedule.getIdSchedule());
                                dispose();
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                }

            });
            confirmedCheckBox.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    status = "Confirmado";
                    for (Schedule schedule : scheduleDB) {
                        if (intIdSchedule == schedule.getIdSchedule()) {
                            try {
                                DataBaseAgendamentos.updateSchedule(schedule.getDiaHora(), schedule.getTypeTreatment(), schedule.getClient().getId(), schedule.getIdDentista(), status, schedule.getDetails(), schedule.getIdSchedule());
                                dispose();
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                }
            });
            canceledCheckBox.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    status = "Cancelado";
                    for (Schedule schedule : scheduleDB) {
                        if (intIdSchedule == schedule.getIdSchedule()) {
                            try {
                                DataBaseAgendamentos.updateSchedule(schedule.getDiaHora(), schedule.getTypeTreatment(), schedule.getClient().getId(), schedule.getIdDentista(), status, schedule.getDetails(), schedule.getIdSchedule());
                                dispose();
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                }
            });
            rescheduledCheckBox.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    status = "Reagendado";
                    for (Schedule schedule : scheduleDB) {
                        if (intIdSchedule == schedule.getIdSchedule()) {
                            try {
                                DataBaseAgendamentos.updateSchedule(schedule.getDiaHora(), schedule.getTypeTreatment(), schedule.getClient().getId(), schedule.getIdDentista(), status, schedule.getDetails(), schedule.getIdSchedule());
                                dispose();
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                }
            });
            pendingCheckBox.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    status = "Pendente";
                    for (Schedule schedule : scheduleDB) {
                        if (intIdSchedule == schedule.getIdSchedule()) {
                            try {
                                DataBaseAgendamentos.updateSchedule(schedule.getDiaHora(), schedule.getTypeTreatment(), schedule.getClient().getId(), schedule.getIdDentista(), status, schedule.getDetails(), schedule.getIdSchedule());
                                dispose();
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                }
            });
        }catch (SQLException e){
            JOptionPane.showMessageDialog(editStatusPane,"Erro ao carregar Agendamento: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }
}
