package ui;

import javax.swing.*;

import api.Client;
import ui.Clientes.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class scheduling {

    private JList<String> atualList;
    private JList<String> futuroList;
    private JButton btnNovoAgendamento;
    public JPanel schedulingPane;
    private JLabel clientCurrent;
    private JLabel procedureCurrent;
    private JLabel detailCurrent;
    private JLabel timeCurrent;
    private JLabel dentistCurrent;
    private JLabel statuCurrent;
    private JLabel clientFuture;
    private JLabel procedureFuture;
    private JLabel detailFuture;
    private JLabel timeFuture;
    private JLabel dentistFuture;
    private JLabel statuFuture;
    private MainScreen mainScreen;
    private DefaultListModel<String> listAgendamento;;

    public scheduling(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
        listAgendamento = new DefaultListModel<>();
        setupButton();
    }

    public void setupButton(){
        btnNovoAgendamento.addActionListener(e -> mainScreen.showClientes());

    }
}
