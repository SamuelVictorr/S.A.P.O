package ui;

import api.Client;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import static api.DataBaseAgendamentos.addSchedule;

public class registerAppointment extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextField typeField;
    private JComboBox dentistBox;
    private JTextField detailField;
    private JTextField dateField;
    private JButton agendarButton;
    private JTextField timeField;
    private JLabel typeLabel;
    private JLabel dentistLabel;
    private JLabel detailLabel;
    private JLabel dateLabel;
    private JLabel timeLabel;
    private JButton buttonCancel;
    boolean valid;
    private Client clientScheduling;
    private MainScreen mainScreen;


    public registerAppointment(Client client) {
        this.clientScheduling = client;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Agendamento para " + clientScheduling.getName());
        setSize(500, 400);
        registerAction();
        mask();
    }

    private void registerAction(){
        agendarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String type = typeField.getText();
                String dentist = dentistBox.getSelectedItem().toString();
                String detail = detailField.getText();
                String date = dateField.getText();
                String time = timeField.getText();
                String status = "Pendente";
                String idDetista = "";
                String clinicID = "1";

                //Validação dos dados
                valid = validSchedule(type, dentist, date, time);
                if (!valid) {
                    return;
                }
                if (dentist.equals("Miguel")) {
                    idDetista = "1";
                } else if (dentist.equals("Samuel")) {
                    idDetista = "2";
                } else if (dentist.equals("Davi")) {
                    idDetista = "3";
                } else if (dentist.equals("Gabriel")) {
                    idDetista = "4";
                }
                String dataTime = date + " / " + time;

                try {
                    addSchedule(dataTime, detail, status, clinicID, String.valueOf(clientScheduling.getId()), dentist, type, idDetista);
                    JOptionPane.showMessageDialog(null, "Agendamento cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    clearText();
                    dispose();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar Agendamento", "ERRO", JOptionPane.INFORMATION_MESSAGE);
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public void clearText(){
        typeField.setText("");
        detailField.setText("");
        dateField.setText("");
        timeField.setText("");
    }

    public void mask(){

        typeField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (typeField.getText().equals("Tipo de Consulta")){
                    typeField.setText("");
                }
            }
        });
        detailField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (detailField.getText().equals("Detalhe sobra a consulta")){
                    detailField.setText("");
                }
            }
        });
        dateField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (dateField.getText().equals("XX/XX/XXXX")){
                    dateField.setText("");
                }
            }
        });

        dateField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyPressed(e);
                String date = dateField.getText().replaceAll("[^0-9]", "");
                if (date.length() >= 8) {
                    date = date.substring(0,8);
                }

                StringBuilder formatDate = new StringBuilder();
                for (int i = 0; i < date.length(); i++) {
                    if (i == 2) {
                        formatDate.append('/');
                    }
                    if (i == 4){
                        formatDate.append('/');
                    }
                    formatDate.append(date.charAt(i));
                }
                dateField.setText(formatDate.toString());

            }
        });
        timeField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (timeField.getText().equals("XX:XX")){
                    timeField.setText("");
                }
            }
        });
        timeField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyPressed(e);
                String time = timeField.getText().replaceAll("[^0-9]", "");
                if (time.length() >= 4) {
                    time = time.substring(0, 4);
                }

                StringBuilder formatTime = new StringBuilder();
                for (int i = 0; i < time.length(); i++) {
                    if (i == 2) {
                        formatTime.append(':');
                    }
                    formatTime.append(time.charAt(i));
                }
                timeField.setText(formatTime.toString());
            }
        });

    }

    public boolean validSchedule(String type, String dentist, String date, String time){
        //validação do Tipo
        if(type.isEmpty()){
            JOptionPane.showMessageDialog(null, "Tipo de Consulta é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }else if(type.equals("Tipo de Consulta")){
            JOptionPane.showMessageDialog(null, "Tipo de Consulta é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        //validação do dentista
        if(dentist.isEmpty()){
            JOptionPane.showMessageDialog(null, "Tipo de Consulta é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }else if(dentist.equals("Nome do Dentista")){
            JOptionPane.showMessageDialog(null, "Tipo de Consulta é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        //validação da data
        if (date.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Data é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
        }else if(dentist.equals("XX/XX/XXXX")){
            JOptionPane.showMessageDialog(null, "Data é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if (date.length() == 10){
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
            try {
                LocalDate parseDate = LocalDate.parse(date, dateFormat);
                LocalDate dateNow = LocalDate.now();

                if (parseDate.isBefore(dateNow)) {
                    JOptionPane.showMessageDialog(null, "A Data não pode ser anterior a hoje!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "Data está inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        //validação da horario
        if (time.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Horário é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
        }else if(time.equals("XX:XX")){
            JOptionPane.showMessageDialog(null, "Horário é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }else if(time.length() == 5){
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm").withResolverStyle(ResolverStyle.STRICT);
            try{
                LocalTime.parse(time, timeFormat);
                return true;
            }catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "Horário está inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }
}