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
        setTitle("Editar Status");

    }

    public void setStatus(){
        performedCheckBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        confirmedCheckBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        canceledCheckBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        rescheduledCheckBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        pendingCheckBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });


    }

    public static void main(String[] args) {
        editStatusScheduling dialog = new editStatusScheduling();
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        System.exit(0);
    }
}
