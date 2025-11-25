package ui;

import javax.swing.*;
import java.awt.event.*;

public class editStatusScheduling extends JDialog {
    private JPanel editStatusPane;
    private JButton buttonEdit;
    private JPanel buttonPane;
    private JRadioButton performedCheckBox;
    private JRadioButton confirmedCheckBox;
    private JRadioButton canceledCheckBox;
    private JRadioButton rescheduledCheckBox;
    private JRadioButton pendingCheckBox;
    private JButton buttonCancel;

    public editStatusScheduling() {
        setContentPane(editStatusPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonEdit);

    }


    public static void main(String[] args) {
        editStatusScheduling dialog = new editStatusScheduling();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
