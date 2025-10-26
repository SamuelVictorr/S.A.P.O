import javax.swing.*;

import static java.awt.Frame.MAXIMIZED_BOTH;

public class Main {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame();
        mainFrame.setVisible(true);
        mainFrame.setTitle("S.A.P.O");
        mainFrame.setSize(800,600);
        mainFrame.setExtendedState(MAXIMIZED_BOTH);
    }
}