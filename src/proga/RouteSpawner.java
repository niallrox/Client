package proga;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class RouteSpawner {
    private String id;
    private String name;
    private String coordinatesX;
    private String coordinatesY;
    private String creationDate;
    private String locationFromX;
    private String locationFromY;
    private String locationFromZ;
    private String locationFromName;
    private String locationToX;
    private String locationToY;
    private String locationToZ;
    private String locationToName;
    private String distance;
    private String login;
    int startX;
    int startY;
    public int size;
    private Circle circle;
    public int yDefault;
    public int xDefault;
    public Color color;
    private GeneralPath path;



    public RouteSpawner(String id, String name, String coordinatesX, String coordinatesY, String creationDate, String locationFromX, String locationFromY, String locationFromZ, String locationFromName, String locationToX, String locationToY, String locationToZ, String locationToName, String distance, String login) {
        this.id = id;
        this.name = name;
        this.coordinatesX = coordinatesX;
        this.coordinatesY = coordinatesY;
        this.creationDate = creationDate;
        this.locationFromX = locationFromX;
        this.locationFromY = locationFromY;
        this.locationFromZ = locationFromZ;
        this.locationFromName = locationFromName;
        this.locationToX = locationToX;
        this.locationToY = locationToY;
        this.locationToZ = locationToZ;
        this.locationToName = locationToName;
        this.distance = distance;
        this.login = login;
        startX = Integer.parseInt(coordinatesX) + 30 ;
        startY = (int) Double.parseDouble(coordinatesY) + 30;
        size = (int) Long.parseLong(distance) + 20;
        if (size > 200) {
            size = 200;
        }
        yDefault=startY+size/2;
        xDefault=startX+size/2;
    }

    public void paintRoute(Graphics2D g) {
        circle = new Circle(startX, startY, size, size);
        path = new GeneralPath();
        color = g.getColor();
        g.setColor(color);
        path.append(circle, true);
        path.lineTo(xDefault, startY + 2 * size);
        path.lineTo(startX, yDefault);
        path.closePath();
        g.draw(path);
        g.fill(path);
    }
//| -------------------PATTERN--------------------------
//|    Circle circle = new Circle(x,y,w,w);            |
//|        path.append(circle,true);                   |
//|   Переносим текущую точку в первую вершину,        |
//|                path.lineTo(x+w/2, y+2*w);          |
//|   проводим сторону треугольника до второй вершины, |
//|                path.lineTo(x, y+w/2);              |
//| -------------------PATTERN--------------------------
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCoordinatesX() {
        return coordinatesX;
    }

    public String getCoordinatesY() {
        return coordinatesY;
    }

    public String getLocationFromX() {
        return locationFromX;
    }

    public String getLocationFromY() {
        return locationFromY;
    }

    public String getLocationFromZ() {
        return locationFromZ;
    }

    public String getLocationFromName() {
        return locationFromName;
    }

    public String getLocationToX() {
        return locationToX;
    }

    public String getLocationToY() {
        return locationToY;
    }

    public String getLocationToZ() {
        return locationToZ;
    }

    public String getLocationToName() {
        return locationToName;
    }

    public String getDistance() {
        return distance;
    }

    public String getLogin() {
        return login;
    }

    public Circle getCircle() {
        return circle;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public GeneralPath getPath() {
        return path;
    }

    public int getSize() {
        return size;
    }

}
