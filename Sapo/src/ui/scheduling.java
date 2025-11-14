package ui;

import javax.swing.*;

public class scheduling {

    private JList<String> atualList;
    private JList<String> futuroList;
    private JButton btnNovoAgendamento;
    public JPanel schedulingPane;
    private MainScreen mainScreen;
    private DefaultListModel<String> listAgendamento;

    public scheduling(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
        listAgendamento = new DefaultListModel<>();
    }

    public void setupButton(){
    }
}
