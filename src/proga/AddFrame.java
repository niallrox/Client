package proga;

import Listeners.AddListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    private String id;
    private Manager manager = new Manager();

    public AddFrame(String title, DatagramChannel datagramChannel, SocketAddress socketAddress, String login, String password, JTextArea output, DefaultTableModel defaultTableModel,String id) {
        super(title);
        nameRoute.setDocument(new LimitedDocument(10));
        coordinatesX.setDocument(new LimitedDocument(3));
        coordinatesY.setDocument(new LimitedDocument(3));
        locationFromX.setDocument(new LimitedDocument(3));
        locationFromY.setDocument(new LimitedDocument(3));
        locationFromZ.setDocument(new LimitedDocument(3));
        locationFromName.setDocument(new LimitedDocument(3));
        locationToX.setDocument(new LimitedDocument(3));
        locationToY.setDocument(new LimitedDocument(3));
        locationToZ.setDocument(new LimitedDocument(3));
        locationToName.setDocument(new LimitedDocument(3));
        distance.setDocument(new LimitedDocument(3));
        this.id=id;
        this.setContentPane(contentPane);
        this.pack();
        this.setLocationRelativeTo(null);
        send.addActionListener(new AddListener(this, datagramChannel, socketAddress, login, password, title,output,defaultTableModel,id));
    }

    public void run() {
        setVisible(true);
    }

    public void setCoordinatesX(String coordinatesX) {
        this.coordinatesX.setText(coordinatesX);
    }

    public void setCoordinatesY(String coordinatesY) {
        this.coordinatesY.setText(coordinatesY);
    }

    public void setDistance(String distance) {
        this.distance.setText(distance);
    }

    public void setLocationFromName(String locationFromName) {
        this.locationFromName.setText(locationFromName);
    }

    public void setLocationFromX(String locationFromX) {
        this.locationFromX.setText(locationFromX);
    }

    public void setLocationFromY(String locationFromY) {
        this.locationFromY.setText(locationFromY);
    }


    public void setNameRoute(String nameRoute) {
        this.nameRoute.setText(nameRoute);
    }

    public void setLocationFromZ(String locationFromZ) {
        this.locationFromZ.setText(locationFromZ);
    }

    public void setLocationToName(String locationToName) {
        this.locationToName.setText(locationToName);
    }

    public void setLocationToX(String locationToX) {
        this.locationToX.setText(locationToX);
    }

    public void setLocationToY(String locationToY) {
        this.locationToY.setText(locationToY);
    }

    public void setLocationToZ(String locationToZ) {
        this.locationToZ.setText(locationToZ);
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
