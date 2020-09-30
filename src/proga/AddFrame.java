package proga;

import Listeners.AddListener;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class AddFrame extends JFrame {
    private JPanel contentPane;
    private JTextField nameRoute;
    private JTextField coordinatesX;
    private JTextField coordinatesY;
    private JTextField locationFromX;
    private JTextField locationFromY;
    private JTextField locationFromZ;
    private JTextField locationFromName;
    private JTextField locationToX;
    private JTextField locationToY;
    private JTextField locationToZ;
    private JTextField locationToName;
    private JTextField distance;
    public JButton send;

    public AddFrame(String title, DatagramChannel datagramChannel, SocketAddress socketAddress, String login, String password) {
        super(title);
        nameRoute.setDocument(new LimitedDocument(5));
        coordinatesX.setDocument(new LimitedDocument(5));
        coordinatesY.setDocument(new LimitedDocument(5));
        locationFromX.setDocument(new LimitedDocument(5));
        locationFromY.setDocument(new LimitedDocument(5));
        locationFromZ.setDocument(new LimitedDocument(5));
        locationFromName.setDocument(new LimitedDocument(5));
        locationToX.setDocument(new LimitedDocument(5));
        locationToY.setDocument(new LimitedDocument(5));
        locationToZ.setDocument(new LimitedDocument(5));
        locationToName.setDocument(new LimitedDocument(5));
        distance.setDocument(new LimitedDocument(5));
        this.setContentPane(contentPane);
        this.pack();
        send.addActionListener(new AddListener(this, datagramChannel, socketAddress, login, password, title));
    }

    public void run() {
        setVisible(true);
    }

    public JTextField getCoordinatesX() {
        return coordinatesX;
    }

    public JTextField getCoordinatesY() {
        return coordinatesY;
    }

    public JTextField getDistance() {
        return distance;
    }

    public JTextField getLocationFromName() {
        return locationFromName;
    }

    public JTextField getLocationFromX() {
        return locationFromX;
    }

    public JTextField getLocationFromY() {
        return locationFromY;
    }

    public JTextField getLocationFromZ() {
        return locationFromZ;
    }

    public JTextField getLocationToName() {
        return locationToName;
    }

    public JTextField getLocationToX() {
        return locationToX;
    }

    public JTextField getLocationToY() {
        return locationToY;
    }

    public JTextField getLocationToZ() {
        return locationToZ;
    }
    public JTextField getNameRoute() {
        return nameRoute;
    }

    @Override
    public JPanel getContentPane() {
        return contentPane;
    }

    static class LimitedDocument extends PlainDocument {

        private int limit;

        public LimitedDocument() {
            limit = -1;
        }

        public LimitedDocument(int limit) {
            this.limit = limit;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

            if (getLimit() < 0 || getLength() < getLimit()) {
                super.insertString(offs, str, a);
            }
        }
    }
}
