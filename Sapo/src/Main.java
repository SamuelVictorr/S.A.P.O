import com.formdev.flatlaf.FlatLightLaf;
import ui.MainScreen;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.plaf.nimbus.NimbusStyle;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        try {
            //Set Flatlaf using on main channel
            UIManager.setLookAndFeel(new FlatLightLaf());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        JFrame mainFrame = new JFrame();
        mainFrame.setTitle("S.A.P.O - Sistema de Agendamento e Prevenção Odontológica");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        MainScreen mainScreen = new MainScreen();
        mainFrame.setContentPane(mainScreen.mainPanel);

        mainFrame.setVisible(true);
    }
}