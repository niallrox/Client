package Listeners;

import proga.AuthorizationFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class ConnectionListener implements ActionListener {
    private JTextField host;
    private JTextField port;
    private JFrame connectFrame;

    public ConnectionListener(JTextField host, JTextField port, JFrame connectFrame) {
        this.host = host;
        this.port = port;
        this.connectFrame = connectFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            DatagramChannel datagramChannel = DatagramChannel.open();
            datagramChannel.configureBlocking(true);
            SocketAddress socketAddress = new InetSocketAddress(host.getText(), Integer.parseInt(port.getText()));
            datagramChannel.connect(socketAddress);
            System.out.println("Соединение установлено хост: " + host.getText() + "порт: " + port.getText());
            connectFrame.setVisible(false);
            AuthorizationFrame authFrame = new AuthorizationFrame("Регистрация/Вход", datagramChannel, socketAddress);
            authFrame.run();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
