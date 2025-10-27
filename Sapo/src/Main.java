import ui.Clientes;
import api.DataBase;
import javax.swing.*;

import java.sql.SQLException;

import static java.awt.Frame.MAXIMIZED_BOTH;

public class Main {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame();
        mainFrame.setTitle("S.A.P.O");
        mainFrame.setSize(800, 600);
        mainFrame.setExtendedState(MAXIMIZED_BOTH);


        //Inicializa o painel de clientes dentro do frame
        Clientes clienteForm = new Clientes();
        mainFrame.setContentPane(clienteForm.clientesPanel);

        mainFrame.setVisible(true);
        try {
            System.out.println(DataBase.getClients());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}