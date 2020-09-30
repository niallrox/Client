package proga;

import Listeners.AuthorizationListener;
import Listeners.RegistrationListener;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.event.*;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class AuthorizationFrame extends JFrame {
    private JPanel contentPane;
    private JTextField loginInput;
    private JTextField passwordInput;
    private JButton registration;
    private JButton authorization;

    public AuthorizationFrame(String title , DatagramChannel datagramChannel, SocketAddress socketAddress) throws IOException {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(contentPane);
        this.pack();
        authorization.addActionListener(new AuthorizationListener(datagramChannel,loginInput,passwordInput,socketAddress,this));
        registration.addActionListener(new RegistrationListener(datagramChannel,loginInput,passwordInput,socketAddress,this));
    }
    public void run(){
        this.setVisible(true);
    }
}
