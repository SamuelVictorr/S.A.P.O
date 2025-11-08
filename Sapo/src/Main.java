import ui.MainScreen;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame mainFrame = new JFrame();
        mainFrame.setTitle("S.A.P.O - Sistema de Agendamento e Prevenção Odontológica");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        MainScreen mainScreen = new MainScreen();
        mainFrame.setContentPane(mainScreen.mainPanel);

        mainFrame.setVisible(true);
    }
}