package ui;

import javax.swing.*;
import java.awt.event.*;

import static api.DataBase.verifyCPF;

public class register extends JDialog {
    private JPanel contentPane;
    private JButton register;
    //private JButton buttonCancel;
    private JTextField namefield;
    private JTextField telephonefield;
    private JTextField CPFfield;
    private JTextField birthfield;
    private JTextField addressfield;
    private JLabel namelabel;
    private JLabel telephonelabel;
    private JLabel CPFlabel;
    private JLabel birthlabel;
    private JTextField Fieldobser;
    private JLabel JLabelobser;

    public register() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(register);
        setTitle("Cadastro");

        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Funiciona");
                String name = namefield.getText();
                String CPF = CPFfield.getText();
                String telephone = telephonefield.getText();

                System.out.println(verifyCPF(CPF));
                System.out.println(validTelephone(telephone));

                System.out.printf("%s\n%s\n%s",name,CPF,telephone);


            }
        });

        /*buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });*/

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        CPFfield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
            }
        });
    }

    public String validTelephone(String telephone){

        String telephone1 = telephone.replace("(","");
        String telephone2 = telephone1.replace(")","");
        int telephoneLength = telephone2.length();

        if(telephoneLength == 13){
            return telephone2;
        } else {
            return "Telefone invalido!";
        }
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
        register dialog = new register();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
