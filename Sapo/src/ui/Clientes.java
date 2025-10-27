package ui;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Clientes {
    public JPanel clientesPanel;
    public JTextField seacrhField;
    private JList clientList;


    public void setList(ArrayList<String> list) {
        clientList.setListData(list.toArray());
    }

    public Clientes() {
        seacrhField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (seacrhField.getText().equals("Digite o nome do cliente")) {
                    seacrhField.setText("");
                }
                System.out.println(seacrhField.getText());
            }
        });

    }
}
