import Foundation.Coordinates;
import Foundation.Location;
import Foundation.Route;

public class RouteIKEA {
    long id = 0;
    TextInput commandd = new TextInput();
    Route route;

    public String readName() {
        String name;
        do {
            commandd.output("Введите имя:");
            name = commandd.getNextInput().trim();

        } while (name.equals(""));
        return name;
    }

    public Integer readX() {
        String x1;
        Integer x = null;
        do {
            commandd.output("Coordinates:Введите координаты, x:");
            x1 = commandd.getNextInput().trim();
            try {
                x = Integer.parseInt(x1);
                if (x < -310) {
                    x = null;
                    System.out.println("Поле должно быть больше -310");
                }
            } catch (NumberFormatException n) {
                System.out.println("Это не число");
            }
        } while (x == null);
        return x;
    }

    public Integer readY() {
        Integer y = null;
        String y1;
        do {
            commandd.output("Введите координаты y:");
            y1 = commandd.getNextInput();
            try {
                y = Integer.parseInt(y1);
                if (y < -921) {
                    y = null;
                    System.out.println("Поле должно быть больше -921");
                }
            } catch (NumberFormatException n) {
                System.out.println("Это не число");
            }
        } while (y == null);
        return y;
    }

    public Float readX2() {
        String x2;
        Float xl = null;
        do {
            commandd.output("Location from:Введите координаты, x:");
            x2 = commandd.getNextInput().trim();
            if (x2 == "") {
                xl = null;
            } else {
                try {
                    xl = Float.parseFloat(x2);
                    float p = xl;
                    int o = (int) p;
                    int i = x2.length() - String.valueOf(o).length() - 1;
                    if (i > 6) {
                        System.out.println("Длина дробной части-" + i + ".Происходит округление, чтобы число не округлялось длина дробной части должна быть меньше 7");
                    }
                } catch (NumberFormatException n) {
                    System.out.println("Это не число");
                }
            }
        }
        while (xl == null);
        return xl;
    }

    public Double readY2() {
        String y2;
        Double yl = null;
        do {
            commandd.output("Введите координаты, y:");
            y2 = commandd.getNextInput().trim();
            if (y2 == "") {
                yl = null;
            } else {
                try {
                    yl = Double.parseDouble(y2);
                    double p = yl;
                    int o = (int) p;
                    int i = y2.length() - String.valueOf(o).length() - 1;
                    if (i > 15) {
                        System.out.println("Длина дробной части-" + i + ".Происходит округление, чтобы число не округлялось длина дробной части должна быть меньше 16");
                    }
                } catch (NumberFormatException n) {
                    System.out.println("Это не число");
                }
            }
        } while (yl == null);
        return yl;
    }

    public Integer readZ() {
        String z;
        Integer zl = null;
        do {
            commandd.output("Введите координаты, z:");
            z = commandd.getNextInput().trim();
            if (z == "") {
                zl = null;
            } else {
                try {
                    zl = Integer.parseInt(z);
                } catch (NumberFormatException n) {
                    System.out.println("Это не число");
                }
            }
        } while (zl == null);
        return zl;
    }

    public String readName1() {
        String namel;
        do {
            commandd.output("Введите имя локации:");
            namel = commandd.getNextInput().trim();
        } while (namel.equals(""));
        return namel;
    }

    public Float readX3() {

        String x3;
        Float xl1 = null;
        do {
            commandd.output("Location to:Введите координаты, x:");
            x3 = commandd.getNextInput().trim();
            if (x3 == "") {
                xl1 = null;
            } else {
                try {
                    xl1 = Float.parseFloat(x3);
                    float p = xl1;
                    int o = (int) p;
                    int i = x3.length() - String.valueOf(o).length() - 1;
                    if (i > 6) {
                        System.out.println("Длина дробной части-" + i + ".Происходит округление, чтобы число не округлялось длина дробной части должна быть меньше 7");
                    }
                } catch (NumberFormatException n) {
                    System.out.println("Это не число");
                }
            }
        } while (xl1 == null);
        return xl1;
    }

    public Double readY3() {
        String y3;
        Double yl1 = null;
        do {
            commandd.output("Введите координаты, y:");
            y3 = commandd.getNextInput().trim();
            if (y3 == "") {
                yl1 = null;
            } else {
                try {
                    yl1 = Double.parseDouble(y3);
                    double p = yl1;
                    int o = (int) p;
                    int i = y3.length() - String.valueOf(o).length() - 1;
                    if (i > 15) {
                        System.out.println("Длина дробной части-" + i + ".Происходит округление, чтобы число не округлялось длина дробной части должна быть меньше 16");
                    }
                } catch (NumberFormatException n) {
                    System.out.println("Это не число");
                }
            }
        } while (yl1 == null);
        return yl1;
    }

    public Integer readZ2() {
        String z1;
        Integer zl1 = null;
        do {
            commandd.output("Введите координаты, z:");
            z1 = commandd.getNextInput().trim();
            if (z1 == "") {
                zl1 = null;
            } else {
                try {
                    zl1 = Integer.parseInt(z1);
                } catch (NumberFormatException n) {
                    System.out.println("Это не число");
                }
            }
        } while (zl1 == null);
        return zl1;
    }

    public String readName2() {
        String namel1;
        do {
            commandd.output("Введите имя локации:");
            namel1 = commandd.getNextInput().trim();
        } while (namel1.equals(""));
        return namel1;
    }

    public Long readDistance() {

        String distance;
        Long distance1 = null;
        do {
            commandd.output("Введите расстояние ");
            distance = commandd.getNextInput().trim();
            try {
                distance1 = Long.parseLong(distance);
                if (distance1 <= 1) {
                    distance1 = null;
                    System.out.println("Поле должно быть больше 1");
                }
            } catch (NumberFormatException n) {
                System.out.println("Это не число");
            }
        } while (distance1 == null);
        return distance1;
    }

    public Object readObject() {
        String name = readName();
        Integer x = readX();
        Integer y = readY();
        Float xl = readX2();
        Double yl = readY2();
        Integer zl = readZ();
        String namel = readName1();
        Float xl1 = readX3();
        Double yl1 = readY3();
        Integer zl1 = readZ2();
        String namel1 = readName2();
        Long distance1 = readDistance();
        route = new Route(id, name, new Coordinates(x, y), new Location(xl, yl, zl, namel), new Location(xl1, yl1, zl1, namel1), distance1);
        return route;
    }
}
