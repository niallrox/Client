package proga;

import Listeners.ConnectionListener;

import javax.swing.*;

public class ConnectionFrame extends JFrame {
    private JPanel contentPane;
    private JButton send;
    private JTextField hostInput;
    private JTextField portInput;

    public ConnectionFrame(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(contentPane);
        this.pack();
        send.addActionListener(new ConnectionListener(hostInput,portInput,this));
    }
    public static void main(String[] args) {
        JFrame jFrame = new ConnectionFrame("Подключение");
        jFrame.setVisible(true);
    }
}