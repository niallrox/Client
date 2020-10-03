package Listeners;

import Foundation.Coordinates;
import Foundation.Location;
import Foundation.Route;
import proga.AddFrame;
import proga.Manager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class AddListener implements ActionListener {
    private AddFrame jFrame;
    private DatagramChannel datagramChannel;
    private SocketAddress socketAddress;
    private String login;
    private String password;
    private String command;
    private Manager manager = new Manager();
    private int id = 0;
    private JTextArea output;
    private DefaultTableModel defaultTableModel;

    public AddListener(AddFrame jFrame, DatagramChannel datagramChannel, SocketAddress socketAddress, String login, String password, String command, JTextArea output, DefaultTableModel defaultTableModel) {
        this.jFrame = jFrame;
        this.datagramChannel = datagramChannel;
        this.socketAddress = socketAddress;
        this.login = login;
        this.password = password;
        this.command = command;
        this.output=output;
        this.defaultTableModel=defaultTableModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (jFrame.getNameRoute().getText().equals("")) {
                throw new NumberFormatException();
            }
            if (Integer.parseInt(jFrame.getCoordinatesX().getText())< -310) {
                throw new NumberFormatException();
            }
            if (Integer.parseInt(jFrame.getCoordinatesY().getText()) < -921) {
                throw new NumberFormatException();
            }
            if (Integer.parseInt(jFrame.getLocationFromZ().getText()) < -623) {
                throw new NumberFormatException();
            }
            if (Integer.parseInt(jFrame.getLocationToZ().getText())< -623) {
                throw new NumberFormatException();
            }
            Route route = new Route(id, jFrame.getNameRoute().getText(), new Coordinates(Integer.parseInt(jFrame.getCoordinatesX().getText()), Integer.parseInt(jFrame.getCoordinatesY().getText())), new Location(Float.parseFloat(jFrame.getLocationFromX().getText()), Double.parseDouble(jFrame.getLocationFromY().getText()), Integer.parseInt(jFrame.getLocationFromZ().getText()), jFrame.getLocationFromName().getText()), new Location(Float.parseFloat(jFrame.getLocationToX().getText()), Double.parseDouble(jFrame.getLocationToY().getText()), Integer.parseInt(jFrame.getLocationToZ().getText()), jFrame.getLocationToName().getText()), Long.parseLong(jFrame.getDistance().getText()), "");
            manager.setRoute(route);
            manager.choose(datagramChannel, socketAddress, command, login, password,jFrame,output,defaultTableModel);
            jFrame.setVisible(false);

        } catch (NumberFormatException el) {
            JOptionPane.showMessageDialog(jFrame.getContentPane(), "Ознакомьтесь с ОДЗ\n name can't be empty\n coordinates x should be>=-310\n coordinates y should be>=-921\n location z should be>=-623");
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

}
