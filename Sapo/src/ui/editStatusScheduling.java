package ui;

import javax.swing.*;
import java.awt.event.*;

public class editStatusScheduling extends JDialog {
    private JPanel editStatusPane;
    private JButton buttonEdit;
    private JPanel buttonPane;
    private JCheckBox performedCheckBox;
    private JCheckBox confirmedCheckBox;
    private JCheckBox canceledCheckBox;
    private JCheckBox rescheduledCheckBox;
    private JCheckBox pendingCheckBox;
    private JButton buttonCancel;

    public editStatusScheduling() {
        setContentPane(editStatusPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonEdit);

        buttonEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        editStatusPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        editStatusScheduling dialog = new editStatusScheduling();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
