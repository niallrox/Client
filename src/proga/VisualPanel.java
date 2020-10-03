package proga;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class VisualPanel extends JPanel {
    private HashMap<String, RouteSpawner> routes = new HashMap<>();
    private HashMap<String, Color> colors = new HashMap<>();
    private Random random = new Random();


    public HashMap<String, RouteSpawner> getRoutes() {
        return routes;
    }

    public HashMap<String, Color> getColors() {
        return colors;
    }

    public VisualPanel(JTextArea output) {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                for (Map.Entry<String, RouteSpawner> route : routes.entrySet()) {
                    if (route.getValue().getCircle().contains(e.getX(), e.getY())||(e.getY()<=route.getValue().startY + 2*route.getValue().getSize() && e.getY()>=route.getValue().startY && e.getX()>=route.getValue().startX && e.getX()<=route.getValue().startX+route.getValue().getSize())) {
                        output.setText("id: " + route.getValue().getId() + "\n" +
                                "name: " + route.getValue().getName() + "\n" +
                                "coordinatesX: " + route.getValue().getCoordinatesX() + "\n" +
                                "coordinatesY: " + route.getValue().getCoordinatesY() + "\n" +
                                "creationDate: " + route.getValue().getCreationDate() + "\n" +
                                "locationFromX: " + route.getValue().getLocationFromX() + "\n" +
                                "locationFromY: " + route.getValue().getLocationFromY() + "\n" +
                                "locationFromZ: " + route.getValue().getLocationFromZ() + "\n" +
                                "locationFromName: " + route.getValue().getLocationFromName() + "\n" +
                                "locationToX: " + route.getValue().getLocationToX() + "\n" +
                                "locationToY: " + route.getValue().getLocationToY() + "\n" +
                                "locationToZ: " + route.getValue().getLocationToZ() + "\n" +
                                "locationToName: " + route.getValue().getLocationToName() + "\n" +
                                "distance: " + route.getValue().getDistance() + "\n" +
                                "login: " + route.getValue().getLogin());
                    }

                }
            }
        });
    }
//| -------------------PATTERN--------------------------
//|    Circle circle = new Circle(x,y,w,w);            |
//|        path.append(circle,true);                   |
//|   Переносим текущую точку в первую вершину,        |
//|                path.lineTo(x+w/2, y+2*w);          |
//|   проводим сторону треугольника до второй вершины, |
//|                path.lineTo(x, y+w/2);              |
//| -------------------PATTERN--------------------------

    public float[] setColor() {
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();
        return new float[]{r, g, b};
    }

    public void updateElement(HashMap<String, RouteSpawner> elementsServer) {
        try {
            for (Map.Entry<String, RouteSpawner> elementServer : elementsServer.entrySet()) {
                if (!routes.containsKey(elementServer.getKey())) {
                    routes.put(elementServer.getKey(), elementServer.getValue());
                    new Thread(new AnimationAdd(elementServer.getValue(), this)).start();
                }
            }
            for (Map.Entry<String, RouteSpawner> elementClient : routes.entrySet()) {
                if (!elementsServer.containsKey(elementClient.getKey())) {
                    new Thread(new AnimationDelete(elementClient.getValue(), this, routes, elementClient.getKey())).start();
                }
            }

            for (Map.Entry<String, RouteSpawner> elementServer : elementsServer.entrySet()) {
                if (!routes.get(elementServer.getKey()).getName().equals(elementServer.getValue().getName()) ||
                        !routes.get(elementServer.getKey()).getCoordinatesX().equals(elementServer.getValue().getCoordinatesX()) ||
                        !routes.get(elementServer.getKey()).getCoordinatesY().equals(elementServer.getValue().getCoordinatesY()) ||
                        !routes.get(elementServer.getKey()).getLocationFromX().equals(elementServer.getValue().getLocationFromX()) ||
                        !routes.get(elementServer.getKey()).getLocationFromY().equals(elementServer.getValue().getLocationFromY()) ||
                        !routes.get(elementServer.getKey()).getLocationFromZ().equals(elementServer.getValue().getLocationFromZ()) ||
                        !routes.get(elementServer.getKey()).getLocationFromName().equals(elementServer.getValue().getLocationFromName()) ||
                        !routes.get(elementServer.getKey()).getLocationToX().equals(elementServer.getValue().getLocationToX()) ||
                        !routes.get(elementServer.getKey()).getLocationToY().equals(elementServer.getValue().getLocationToY()) ||
                        !routes.get(elementServer.getKey()).getLocationToZ().equals(elementServer.getValue().getLocationToZ()) ||
                        !routes.get(elementServer.getKey()).getLocationToName().equals(elementServer.getValue().getLocationToName()) ||
                        !routes.get(elementServer.getKey()).getDistance().equals(elementServer.getValue().getDistance())) {
                    new Thread(new AnimationUpdate(routes.get(elementServer.getKey()), elementServer.getValue(), this, routes, elementServer.getKey())).start();
                }
                repaint();
            }
        } catch (ConcurrentModificationException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        try {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            for (Map.Entry<String, RouteSpawner> element : routes.entrySet()) {
                g2.setColor(colors.get(element.getValue().getLogin()));
                g2.setBackground(colors.get(element.getValue().getLogin()));
                element.getValue().paintRoute(g2);
            }
        } catch (ConcurrentModificationException e) {
            e.printStackTrace();
        }

    }
}

//| -------------------PATTERN--------------------------
//|    Circle circle = new Circle(x,y,w,w);            |
//|        path.append(circle,true);                   |
//|   Переносим текущую точку в первую вершину,        |
//|                path.lineTo(x+w/2, y+2*w);          |
//|   проводим сторону треугольника до второй вершины, |
//|                path.lineTo(x, y+w/2);              |
//| -------------------PATTERN--------------------------