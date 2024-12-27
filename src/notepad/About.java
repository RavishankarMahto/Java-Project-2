package notepad;


import javax.swing.*;
import java.awt.*;

public class About extends JFrame {


    About() {

        setBounds(500, 100, 600, 500);
        ImageIcon il = new ImageIcon(ClassLoader.getSystemResource("notepad/icon/window.png"));
        Image i2 = il.getImage().getScaledInstance(300, 60, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel headerIcon = new JLabel(i3);
        add(headerIcon);

        setVisible(true);

    }

    public static void main(String[] args) {
        new About();
    }
}