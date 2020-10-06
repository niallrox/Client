package Listeners;

import proga.AuthorizationFrame;
import proga.ConnectionFrame;
import proga.Manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class RegistrationListener implements ActionListener {
    private DatagramChannel datagramChannel;
    private JTextField login;
    private JTextField password;
    private SocketAddress socketAddress;
    private Manager manager = new Manager();
    private AuthorizationFrame authorizationFrame;
    private JTextArea output = new JTextArea();
    private ConnectionFrame connectionFrame;
    public RegistrationListener(DatagramChannel datagramChannel, JTextField login , JTextField password, SocketAddress socketAddress, AuthorizationFrame authorizationFrame, ConnectionFrame connectionFrame){
        this.datagramChannel=datagramChannel;
        this.login=login;
        this.password=password;
        this.socketAddress=socketAddress;
        this.authorizationFrame=authorizationFrame;
        this.connectionFrame=connectionFrame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            manager.work(datagramChannel,socketAddress,"reg",login.getText(),password.getText(),output,authorizationFrame,connectionFrame);
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(authorizationFrame,"Проверьте, правильно ли вы подключаетесь");
            connectionFrame.setVisible(true);
        }
    }
}
