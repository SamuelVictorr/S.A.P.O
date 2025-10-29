import api.Client;
import ui.Cadastre;
import api.DataBase;
import javax.swing.*;

import java.sql.SQLException;
import java.util.ArrayList;

import static java.awt.Frame.MAXIMIZED_BOTH;

public class Main {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame();
        mainFrame.setTitle("S.A.P.O");
        mainFrame.setSize(800, 600);
        mainFrame.setExtendedState(MAXIMIZED_BOTH);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Inicializa o painel de clientes dentro do frame
        Cadastre clienteForm = new Cadastre();
        mainFrame.setContentPane(clienteForm.clientesPanel);

        JButton botao = new JButton();
        botao.setBounds(200,100,100,50);

        mainFrame.setVisible(true);
        try {
            var clientsDB = DataBase.getClients();
            ArrayList<String> clientsDbString = new ArrayList<>();
            for (Client client : clientsDB) {
                clientsDbString.add(client.getName());
            }

            clienteForm.setList(clientsDbString);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}