package proga;

import Listeners.ConnectionListener;

import javax.swing.*;
import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

public class ConnectionFrame extends JFrame {
    private JPanel contentPane;
    private JButton send;
    private JTextField hostInput;
    private JTextField portInput;
    private JLabel host;
    private JLabel port;
    private JComboBox<String> languages;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("resources");

    public ConnectionFrame(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(contentPane);
        this.pack();
        this.setLocationRelativeTo(null);
        send.addActionListener(new ConnectionListener(hostInput, portInput, this));
        languages.addActionListener(e -> {
            choseLanguage(languages);
            port.setText(getResourceBundle().getString("port"));
            host.setText(getResourceBundle().getString("host"));
            send.setText(getResourceBundle().getString("send"));
        });
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

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public static void main(String[] args) {
        JFrame jFrame = new ConnectionFrame("Подключение");
        jFrame.setVisible(true);
    }

}