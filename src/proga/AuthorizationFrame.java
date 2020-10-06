package proga;

import Listeners.AuthorizationListener;
import Listeners.RegistrationListener;

import javax.swing.*;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.Locale;
import java.util.ResourceBundle;

public class AuthorizationFrame extends JFrame {
    private JPanel contentPane;
    private JTextField loginInput;
    private JTextField passwordInput;
    private JButton registration;
    private JButton authorization;
    private JLabel login;
    private JLabel password;
    private JComboBox<String> languages;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("resources");

    public AuthorizationFrame(String title, DatagramChannel datagramChannel, SocketAddress socketAddress, ConnectionFrame connectionFrame) throws IOException {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(contentPane);
        this.pack();
        this.setLocationRelativeTo(null);
        authorization.addActionListener(new AuthorizationListener(datagramChannel, loginInput, passwordInput, socketAddress, this, connectionFrame));
        registration.addActionListener(new RegistrationListener(datagramChannel, loginInput, passwordInput, socketAddress, this, connectionFrame));
        languages.addActionListener(e -> {
            choseLanguage(languages);
            login.setText(getResourceBundle().getString("login"));
            password.setText(getResourceBundle().getString("password"));
            registration.setText(getResourceBundle().getString("reg"));
            authorization.setText(getResourceBundle().getString("sign"));
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

    public void run() {
        this.setVisible(true);
    }
}
