package proga;

import Listeners.UsualCommandListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class CommandFrame extends JFrame implements Runnable {
    private DatagramChannel datagramChannel;
    private SocketAddress socketAddress;
    private String login;
    private String password;
    private JTextArea output = new JTextArea("Results");
    private Manager manager = new Manager();
    private DefaultTableModel defaultTableModel;
    private VisualPanel visualPanel = new VisualPanel(output);
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("resources");
    private String nameRoute;
    private String coordX;
    private String coordY;
    private String locFromX;
    private String locFromY;
    private String locFromZ;
    private String locFromName;
    private String locToX;
    private String locToY;
    private String locToZ;
    private String locToName;
    private String distance;
    private String id;


    public CommandFrame(DatagramChannel datagramChannel, SocketAddress socketAddress, String login, String password) {
        this.datagramChannel = datagramChannel;
        this.socketAddress = socketAddress;
        this.login = login;
        this.password = password;
    }

    public void createFrame() {
        output.setPreferredSize(new Dimension(500, 50));
        output.setEditable(false);
        output.setLineWrap(true);
        JScrollPane jScrollPaneOutput = new JScrollPane(output);
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Манагер");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        JTabbedPane jTabbedPane = new JTabbedPane();
        jTabbedPane.setPreferredSize(new Dimension(800, 600));
        JPanel tablePanel = new JPanel();
        String[] title = {"id", "name", "coordX", "coordY", "creationDate", "locFromX", "locFromY", "locFromZ", "locFromName", "locToX", "locToY", "locToZ", "locToName", "distance", "login"};
        defaultTableModel = new DefaultTableModel(title, 0);
        JTable jTable = new JTable(defaultTableModel);

        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() >= 1) //двойной шелчок
                {
                    try {
                        int row = jTable.rowAtPoint(e.getPoint()); //путь попроще без selectionMode
                        if (row > -1) {
                            int realRow = jTable.convertRowIndexToModel(row); //номер строки из модели данных
                            //здесь должна быть выборка объекта из модели по номеру строки и его отображение
                            setId((String) defaultTableModel.getValueAt(realRow, 0));
                            System.out.println(getId());
                            manager.setId(getId());
                            setNameRoute((String) defaultTableModel.getValueAt(realRow, 1));
                            setCoordX((String) defaultTableModel.getValueAt(realRow, 2));
                            setCoordY((String) defaultTableModel.getValueAt(realRow, 3));
                            setLocFromX((String) defaultTableModel.getValueAt(realRow, 5));
                            setLocFromY((String) defaultTableModel.getValueAt(realRow, 6));
                            setLocFromZ((String) defaultTableModel.getValueAt(realRow, 7));
                            setLocFromName((String) defaultTableModel.getValueAt(realRow, 8));
                            setLocToX((String) defaultTableModel.getValueAt(realRow, 9));
                            setLocToY((String) defaultTableModel.getValueAt(realRow, 10));
                            setLocToZ((String) defaultTableModel.getValueAt(realRow, 11));
                            setLocToName((String) defaultTableModel.getValueAt(realRow, 12));
                            setDistance((String) defaultTableModel.getValueAt(realRow, 13));
                        }
                    } catch (IndexOutOfBoundsException ignored) {
                    }
                }
            }
        });

        RowSorter<TableModel> sorter = new TableRowSorter<>(defaultTableModel);
        jTable.setRowSorter(sorter);
        jTable.setFillsViewportHeight(true);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jTabbedPane.add(getResourceBundle().getString("table"), jScrollPane);
        jTabbedPane.add(getResourceBundle().getString("visualisation"), visualPanel);
        JPanel jpanelCommands = new JPanel();
        jpanelCommands.setLayout(new GridLayout(2, 0, 5, 10));

        JButton add = new JButton("add");
        add.addActionListener(e -> {
            AddFrame addFrame = new AddFrame("add", datagramChannel, socketAddress, login, password, output, defaultTableModel, getId());
            addFrame.run();
        });
        jpanelCommands.add(add);

        JButton addIfMax = new JButton("addIfMax");
        addIfMax.addActionListener(e -> {
            AddFrame addFrame = new AddFrame("add_if_max", datagramChannel, socketAddress, login, password, output, defaultTableModel, getId());
            addFrame.run();
        });

        jpanelCommands.add(addIfMax);
        JButton clear = new JButton("clear");
        clear.addActionListener(new UsualCommandListener(datagramChannel, socketAddress, login, password, this, "clear", output, defaultTableModel));
        jpanelCommands.add(clear);
        JButton help = new JButton("help");
        help.addActionListener(new UsualCommandListener(datagramChannel, socketAddress, login, password, this, "help", output, defaultTableModel));

        jpanelCommands.add(help);
        JButton info = new JButton("info");
        info.addActionListener(new UsualCommandListener(datagramChannel, socketAddress, login, password, this, "info", output, defaultTableModel));

        jpanelCommands.add(info);
        JButton maxByFrom = new JButton("maxByFrom");
        maxByFrom.addActionListener(new UsualCommandListener(datagramChannel, socketAddress, login, password, this, "max_by_from", output, defaultTableModel));

        jpanelCommands.add(maxByFrom);
        JButton minByDistance = new JButton("minByDistance");
        minByDistance.addActionListener(new UsualCommandListener(datagramChannel, socketAddress, login, password, this, "min_by_distance", output, defaultTableModel));

        jpanelCommands.add(minByDistance);
        JButton printFieldAscendingDistance = new JButton("printFieldAscendingDistance");
        printFieldAscendingDistance.addActionListener(new UsualCommandListener(datagramChannel, socketAddress, login, password, this, "print_field_ascending_distance", output, defaultTableModel));

        jpanelCommands.add(printFieldAscendingDistance);
        JButton removeHead = new JButton("removeHead");
        removeHead.addActionListener(new UsualCommandListener(datagramChannel, socketAddress, login, password, this, "remove_head", output, defaultTableModel));

        jpanelCommands.add(removeHead);
        JButton removeId = new JButton("removeId");
        removeId.addActionListener(new UsualCommandListener(datagramChannel, socketAddress, login, password, this, "remove_by_id", output, defaultTableModel));

        jpanelCommands.add(removeId);
        JButton removeLower = new JButton("removeLower");
        removeLower.addActionListener(e -> {
            AddFrame addFrame = new AddFrame("remove_lower", datagramChannel, socketAddress, login, password, output, defaultTableModel, getId());
            addFrame.run();
        });

        jpanelCommands.add(removeLower);

        JButton update = new JButton("update");
        update.addActionListener(e -> {
            AddFrame addFrame = new AddFrame("update", datagramChannel, socketAddress, login, password, output, defaultTableModel, getId());
            manager.setId(getId());
            addFrame.setNameRoute(getNameRoute());
            addFrame.setCoordinatesX(getCoordX());
            addFrame.setCoordinatesY(getCoordY());
            addFrame.setLocationFromX(getLocFromX());
            addFrame.setLocationFromY(getLocFromY());
            addFrame.setLocationFromZ(getLocFromZ());
            addFrame.setLocationFromName(getLocFromName());
            addFrame.setLocationToX(getLocToX());
            addFrame.setLocationToY(getLocToY());
            addFrame.setLocationToZ(getLocToZ());
            addFrame.setLocationToName(getLocToName());
            addFrame.setDistance(getDistance());
            addFrame.run();
        });
        jpanelCommands.add(update);
        JButton executeScript = new JButton("executeScript");
        executeScript.addActionListener(new UsualCommandListener(datagramChannel, socketAddress, login, password, this, "execute_script", output, defaultTableModel));
        jpanelCommands.add(executeScript);

        JLabel labelLogin = new JLabel("login: \n" + login);
        String[] language = {"русский", "english", "suomalainen", "espanya"};
        JComboBox<String> languages = new JComboBox<>(language);
        languages.addActionListener(e -> {
            choseLanguage(languages);
            String[] commandsLanguage = {getResourceBundle().getString("add"), getResourceBundle().getString("addIfMax"), getResourceBundle().getString("clear"), getResourceBundle().getString("help"),
                    getResourceBundle().getString("info"), getResourceBundle().getString("maxByFrom"), getResourceBundle().getString("minByDistance"), getResourceBundle().getString("printFieldAscendingDistance"),
                    getResourceBundle().getString("removeHead"), getResourceBundle().getString("removeId"), getResourceBundle().getString("removeLower"), getResourceBundle().getString("update"), getResourceBundle().getString("executeScript")};
            for (int i = 0; i < commandsLanguage.length; i++) {
                ((JButton) jpanelCommands.getComponent(i)).setText(commandsLanguage[i]);
            }
            output.setText("");
            jTabbedPane.setTitleAt(0, getResourceBundle().getString("table"));
            jTabbedPane.setTitleAt(1, getResourceBundle().getString("visualisation"));
        });
        mainPanel.add(languages, BorderLayout.SOUTH);
        mainPanel.add(labelLogin, BorderLayout.WEST);
        mainPanel.add(jpanelCommands, BorderLayout.NORTH);
        mainPanel.add(jScrollPaneOutput, BorderLayout.EAST);
        tablePanel.add(jTabbedPane);
        mainPanel.add(tablePanel);
        frame.add(mainPanel);
        frame.setSize(1200, 1200);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        Thread thread = new Thread(this);
        thread.setDaemon(true);
        thread.start();
    }

    public JTextArea getOutput() {
        return output;
    }

    @Override
    public void run() {
        try {
            String answer;
            HashMap<String, RouteSpawner> elementsServer = new HashMap<>();
            do {
                String condition;
                getManager().choose(datagramChannel, socketAddress, "show", login, password, this, output, defaultTableModel);
                condition = getManager().getAnswerCommand();
                if (!condition.equals("Коллекция пуста")) {
                    Scanner scanner = new Scanner(condition);
                    elementsServer.clear();
                    do {
                        String elements = scanner.nextLine();
                        String[] arguments = elements.split(", ");
                        getDefaultTableModel().insertRow(0, arguments);
                        if (!(getVisualPanel().getColors().containsKey(arguments[14]))) {
                            float[] rgb = getVisualPanel().setColor();
                            getVisualPanel().getColors().put(arguments[14], new Color(rgb[0], rgb[1], rgb[2]));
                        }
                        RouteSpawner routeSpawner = new RouteSpawner(arguments[0], arguments[1], arguments[2], arguments[3], arguments[4], arguments[5],
                                arguments[6], arguments[7], arguments[8], arguments[9], arguments[10], arguments[11], arguments[12], arguments[13], arguments[14]);
                        elementsServer.put(arguments[0], routeSpawner);
                    } while (scanner.hasNextLine());
                    getVisualPanel().updateElement(elementsServer);
                    do {
                        getManager().choose(datagramChannel, socketAddress, "show", login, password, this, output, defaultTableModel);
                        answer = getManager().getAnswerCommand();
                        Thread.sleep(1000);
                    } while (answer.equals(condition));
                    for (int i = getDefaultTableModel().getRowCount() - 1; i > -1; i--) {
                        getDefaultTableModel().removeRow(i);
                    }
                } else {
                    elementsServer.clear();
                    getVisualPanel().updateElement(elementsServer);
                    Thread.sleep(1000);
                }
            } while (true);
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            JOptionPane.showMessageDialog(this, "Сервер сказал пока ");
            System.exit(0);
        }
    }

    public void choseLanguage(JComboBox<String> languages) {
        String language = (String) languages.getSelectedItem();
        switch (language) {
            case "русский":
                resourceBundle = ResourceBundle.getBundle("resources");
                break;
            case "english":
                resourceBundle = ResourceBundle.getBundle("resources", new Locale("en"));
                break;
            case "espanya":
                resourceBundle = ResourceBundle.getBundle("resources", new Locale("sp"));
                break;
            case "suomalainen":
                resourceBundle = ResourceBundle.getBundle("resources", new Locale("fi"));
                break;
        }
    }

    public DefaultTableModel getDefaultTableModel() {
        return defaultTableModel;
    }

    public VisualPanel getVisualPanel() {
        return visualPanel;
    }

    public Manager getManager() {
        return manager;
    }

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public void setNameRoute(String nameRoute) {
        this.nameRoute = nameRoute;
    }

    public String getNameRoute() {
        return nameRoute;
    }

    public void setCoordX(String coordX) {
        this.coordX = coordX;
    }

    public String getCoordX() {
        return coordX;
    }

    public void setCoordY(String coordY) {
        this.coordY = coordY;
    }

    public String getCoordY() {
        return coordY;
    }

    public void setLocFromX(String locFromX) {
        this.locFromX = locFromX;
    }

    public String getLocFromX() {
        return locFromX;
    }

    public void setLocFromY(String locFromY) {
        this.locFromY = locFromY;
    }

    public String getLocFromY() {
        return locFromY;
    }

    public void setLocFromZ(String locFromZ) {
        this.locFromZ = locFromZ;
    }

    public String getLocFromZ() {
        return locFromZ;
    }

    public void setLocFromName(String locFromName) {
        this.locFromName = locFromName;
    }

    public String getLocFromName() {
        return locFromName;
    }

    public void setLocToX(String locToX) {
        this.locToX = locToX;
    }

    public String getLocToX() {
        return locToX;
    }

    public void setLocToY(String locToY) {
        this.locToY = locToY;
    }

    public String getLocToY() {
        return locToY;
    }

    public void setLocToZ(String locToZ) {
        this.locToZ = locToZ;
    }

    public String getLocToZ() {
        return locToZ;
    }

    public void setLocToName(String locToName) {
        this.locToName = locToName;
    }

    public String getLocToName() {
        return locToName;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDistance() {
        return distance;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
