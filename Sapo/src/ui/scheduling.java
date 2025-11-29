package ui;

import api.DataBaseAgendamentos;
import api.Schedule;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;

public class scheduling {

    private JButton btnNovoAgendamento;
    public JPanel schedulingPane;
    private JPanel agendamentoHojePanel;
    private JPanel agendamentoGeneralPanel;
    private MainScreen mainScreen;
    private List<Schedule> scheduleDB;
    private JTable tableGeneral;
    private JTable tableNow;
    private JTextField dateFinalField;
    private JTextField dateInitField;
    private JScrollPane scrollPanel;
    private DefaultTableModel tableGeneralModel;
    private DefaultTableModel tableNowModel;
    String dateInit;
    String dateFinal;

    public scheduling(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
        tableNowModel = new DefaultTableModel(new Object[]{"Numero do Cadastro", "Cliente", "Procedimento", "Detalhe", "Data/Horário", "Dentista", "Status"}, 0);
        tableGeneralModel = new DefaultTableModel(new Object[]{"Numero do Cadastro", "Cliente", "Procedimento", "Detalhe", "Data/Horário", "Dentista", "Status"}, 0);
        setFieldsSchedule();
        setupButton();
        formatDateSchedule();
        statusEdit();
        loadSchedule();
    }

    public void setupButton() {
        btnNovoAgendamento.addActionListener(e -> mainScreen.schedulingModeActivated());
    }

    public void loadSchedule() {
        try {
            dateInit = dateInitField.getText();
            dateFinal = dateFinalField.getText();
            scheduleDB = DataBaseAgendamentos.getSchedules();
            tableNowModel.setRowCount(0);
            tableGeneralModel.setRowCount(0);

            for (Schedule schedule : scheduleDB) {
                String dataHora = schedule.getDiaHora();
                String date = dataHora.substring(0, 10);
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
                LocalDate dateNow = LocalDate.now();
                LocalDate parseDate = LocalDate.parse(date, dateFormat);

                if (parseDate.isEqual(dateNow)) {
                    addNow(schedule);
                }
                addGeneral(schedule);
            }
            tableNow.setModel(tableNowModel);
            tableGeneral.setModel(tableGeneralModel);
            if (dateInit.length() == 10 || dateFinal.length() == 10) {
                searchDate();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(schedulingPane, "Erro ao carregar clientes: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setFieldsSchedule() {
        dateInitField.setText("Data Inicial:");
        dateFinalField.setText("Data Final:");
        dateInitField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (dateInitField.getText().equals("Data Inicial:")) {
                    dateInitField.setText("");
                }
            }
        });
        dateFinalField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (dateFinalField.getText().equals("Data Final:")) {
                    dateFinalField.setText("");
                }
            }
        });
    }

    public void formatDateSchedule() {
        dateInitField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyPressed(e);
                String date = dateInitField.getText().replaceAll("[^0-9]", "");
                if (date.length() >= 8) {
                    date = date.substring(0, 8);
                }

                StringBuilder formatInit = new StringBuilder();
                for (int i = 0; i < date.length(); i++) {
                    if (i == 2) {
                        formatInit.append('/');
                    }
                    if (i == 4) {
                        formatInit.append('/');
                    }
                    formatInit.append(date.charAt(i));
                }
                dateInitField.setText(formatInit.toString());
                if (dateInitField.getText().length() == 10) {
                    searchDate();
                }
                if (dateInitField.getText().length() < 10 && dateFinalField.getText().equals("Data Final:")) {
                    loadSchedule();
                } else if (dateInitField.getText().length() < 10 && dateFinalField.getText().isEmpty()) {
                    loadSchedule();
                }
            }
        });

        dateFinalField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyPressed(e);
                String date = dateFinalField.getText().replaceAll("[^0-9]", "");
                if (date.length() >= 8) {
                    date = date.substring(0, 8);
                }

                StringBuilder formatFinal = new StringBuilder();
                for (int i = 0; i < date.length(); i++) {
                    if (i == 2) {
                        formatFinal.append('/');
                    }
                    if (i == 4) {
                        formatFinal.append('/');
                    }
                    formatFinal.append(date.charAt(i));
                }
                dateFinalField.setText(formatFinal.toString());
                if (dateFinalField.getText().length() == 10) {
                    searchDate();
                }
                if (dateFinalField.getText().length() < 10 && dateInitField.getText().equals("Data Inicial:")) {
                    loadSchedule();
                } else if (dateFinalField.getText().length() < 10 && dateInitField.getText().isEmpty()) {
                    loadSchedule();
                }

            }
        });
    }

    private void addGeneral(Schedule s) {
        tableGeneralModel.addRow(new Object[]{
                s.getIdSchedule(),
                s.getClient().getName(),
                s.getTypeTreatment(),
                s.getDetails(),
                s.getDiaHora(),
                s.getNameDentist(),
                s.getStatusTreatment()
        });
    }

    private void addNow(Schedule s) {
        tableNowModel.addRow(new Object[]{
                s.getIdSchedule(),
                s.getClient().getName(),
                s.getTypeTreatment(),
                s.getDetails(),
                s.getDiaHora(),
                s.getNameDentist(),
                s.getStatusTreatment()
        });
    }

    public void searchDate() {
        try {
            DateTimeFormatter formatdate = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
            ;
            LocalDate dateInit = null;
            LocalDate dateFinal = null;

            if (dateInitField.getText().length() == 10) {
                dateInit = LocalDate.parse(dateInitField.getText(), formatdate);
            }
            if (dateFinalField.getText().length() == 10) {
                dateFinal = LocalDate.parse(dateFinalField.getText(), formatdate);
            }
            tableGeneralModel.setRowCount(0);

            for (Schedule schedule : scheduleDB) {
                LocalDate dateSchedule = LocalDate.parse(schedule.getDiaHora().substring(0, 10), formatdate);

                if (dateInit != null && dateFinal == null && !dateSchedule.isEqual(LocalDate.now())) {
                    if (!dateSchedule.isBefore(dateInit)) {
                        addGeneral(schedule);
                    }
                } else if (dateInit == null && dateFinal != null && !dateSchedule.isEqual(LocalDate.now())) {
                    if (!dateSchedule.isAfter(dateFinal)) {
                        addGeneral(schedule);
                    }
                } else if (dateInit != null && dateFinal != null && !dateSchedule.isEqual(LocalDate.now())) {
                    if (!dateSchedule.isBefore(dateInit) && !dateSchedule.isAfter(dateFinal)) {
                        addGeneral(schedule);
                    }
                }
                tableGeneral.setModel(tableGeneralModel);
            }
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(schedulingPane, "Formato de data inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void statusEdit() {
        tableGeneral.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int linha = tableGeneral.rowAtPoint(e.getPoint());
                int coluna = tableGeneral.columnAtPoint(e.getPoint());

                if (linha < 0 || coluna != 6) {
                    return;
                }
                int idSchedule = Integer.parseInt(tableGeneral.getValueAt(linha, 0).toString());
                editStatusScheduling editSchedule = new editStatusScheduling(mainScreen);
                editSchedule.setStatus(idSchedule);

                editSchedule.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadSchedule();
                    }
                });
                editSchedule.setVisible(true);
            }
        });
        tableNow.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int linha = tableNow.rowAtPoint(e.getPoint());
                int coluna = tableNow.columnAtPoint(e.getPoint());

                if (linha < 0 || coluna != 6) {
                    return;
                }
                int idSchedule = Integer.parseInt(tableNow.getValueAt(linha, 0).toString());
                editStatusScheduling editSchedule = new editStatusScheduling(mainScreen);
                editSchedule.setStatus(idSchedule);

                editSchedule.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadSchedule();
                    }
                });
                editSchedule.setVisible(true);
            }
        });
    }
}