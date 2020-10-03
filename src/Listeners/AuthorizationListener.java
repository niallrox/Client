package Listeners;

import proga.AuthorizationFrame;
import proga.Command;
import proga.Manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class AuthorizationListener implements ActionListener {
    private DatagramChannel datagramChannel;
    private JTextField login;
    private JTextField password;
    private SocketAddress socketAddress;
    private Manager manager = new Manager();
    private AuthorizationFrame authorizationFrame;
    private JTextArea output = new JTextArea();
    public AuthorizationListener(DatagramChannel datagramChannel, JTextField login , JTextField password, SocketAddress socketAddress,AuthorizationFrame authorizationFrame){
        this.datagramChannel=datagramChannel;
        this.login=login;
        this.password=password;
        this.socketAddress=socketAddress;
        this.authorizationFrame=authorizationFrame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            authorizationFrame.setVisible(false);
            manager.work(datagramChannel,socketAddress,"sign",login.getText(),password.getText(),output);
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
