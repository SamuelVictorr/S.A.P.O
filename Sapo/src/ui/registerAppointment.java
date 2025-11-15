package ui;

import javax.swing.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class registerAppointment extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextField typeField;
    private JTextField dentistField;
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

    public registerAppointment() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Agendamento");
        mask();

        agendarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String type = typeField.getText();
                String dentist = dentistField.getText();
                String detail = detailField.getText();
                String date = dateField.getText();
                String time = timeField.getText();

                verifytype(type);
                verifydentist(dentist);
                verifydate(date);
                verifytime(time);

                clearText();
            }
        });
    }

    public void clearText(){
        typeField.setText("");
        dentistField.setText("");
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
        dentistField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (typeField.getText().equals("Nome do Dentista")){
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
        timeField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (timeField.getText().equals("XX:XX")){
                    timeField.setText("");
                }
            }
        });

    }

    public void verifytype(String type){
        if (type.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tipo de Consulta é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
    public void verifydentist(String dentist){
        if (dentist.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Dentista é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
    public void verifydate(String date){
        if (date.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Data é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
        return;
        }
    }

    public void verifytime(String time){
        if (time.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Time é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    public static void main(String[] args) {
        registerAppointment dialog = new registerAppointment();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}