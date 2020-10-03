package Listeners;

import proga.Manager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class UsualCommandListener implements ActionListener {
    private DatagramChannel datagramChannel;
    private SocketAddress socketAddress;
    private String login;
    private String password;
    private JFrame jFrame;
    private String command;
    private Manager manager = new Manager();
    private JTextArea output;
    private DefaultTableModel defaultTableModel;
    public UsualCommandListener(DatagramChannel datagramChannel, SocketAddress socketAddress, String login, String password,JFrame jFrame, String command ,JTextArea output,DefaultTableModel defaultTableModel ){
        this.datagramChannel=datagramChannel;
        this.socketAddress=socketAddress;
        this.login=login;
        this.password=password;
        this.jFrame=jFrame;
        this.command=command;
        this.output=output;
        this.defaultTableModel=defaultTableModel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            manager.choose(datagramChannel,socketAddress,command,login,password,jFrame,output,defaultTableModel);
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
