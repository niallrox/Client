package proga;

import javax.swing.*;
import java.awt.*;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class CommandFrame extends JFrame {
    private DatagramChannel datagramChannel;
    private SocketAddress socketAddress;
    private String login;
    private String password;

    public CommandFrame(DatagramChannel datagramChannel, SocketAddress socketAddress, String login, String password) {
        this.datagramChannel = datagramChannel;
        this.socketAddress = socketAddress;
        this.login = login;
        this.password = password;
    }

    public void run() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Манагер");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        JPanel jpanelCommands = new JPanel();
        jpanelCommands.setLayout(new GridLayout(2, 0, 5, 10));
        JButton add = new JButton("add");
        add.addActionListener(e -> {
            AddFrame addFrame = new AddFrame("add", datagramChannel, socketAddress, login, password);
            addFrame.run();
        });
        jpanelCommands.add(add);

        JButton addIfMax = new JButton("add if max");
        addIfMax.addActionListener(e -> {
            AddFrame addFrame = new AddFrame("add_if_max", datagramChannel, socketAddress, login, password);
            addFrame.run();
        });

        jpanelCommands.add(addIfMax);
        JButton clear = new JButton("clear");

        jpanelCommands.add(clear);
        JButton help = new JButton("help");

        jpanelCommands.add(help);
        JButton info = new JButton("info");

        jpanelCommands.add(info);
        JButton maxByFrom = new JButton("max by from");

        jpanelCommands.add(maxByFrom);
        JButton minByDistance = new JButton("min by distance");

        jpanelCommands.add(minByDistance);
        JButton printFieldAscendingDistance = new JButton("print field ascending distance");

        jpanelCommands.add(printFieldAscendingDistance);
        JButton removeHead = new JButton("remove head");

        jpanelCommands.add(removeHead);
        JButton removeId = new JButton("remove id");

        jpanelCommands.add(removeId);
        JButton removeLower = new JButton("remove lower");
        removeLower.addActionListener(e -> {
            AddFrame addFrame = new AddFrame("remove_lower", datagramChannel, socketAddress, login, password);
            addFrame.run();
        });

        jpanelCommands.add(removeLower);
        JButton show = new JButton("show");

        jpanelCommands.add(show);
        JButton update = new JButton("update");
        update.addActionListener(e -> {
            AddFrame addFrame = new AddFrame("update", datagramChannel, socketAddress, login, password);
            addFrame.run();
        });

        jpanelCommands.add(update);
        mainPanel.add(jpanelCommands);
        frame.add(mainPanel);
        frame.setSize(1200, 1200);
        frame.setVisible(true);

    }
}
