import java.io.Serializable;
import java.util.Scanner;
import java.lang.Math;
import Foundation.*;

public class TextInput implements Serializable {
    private String currentInput;

    public String getNextInput() {
        Scanner sc = new Scanner(System.in);
        currentInput = sc.nextLine().trim();
        return currentInput;
    }
    public void output(String s) {
        System.out.println(s);
    }
    public Float getNextFloatInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextFloat();
    }
    public Long getNextLongInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLong();
    }
    public String getCurrentinput() {
        return currentInput;
    }
    public Object readElement(){
        int id;
        id = (int) (Math.random() * 10);
        TextInput commandd = new TextInput();
        Route route;
        String name;
        do {
            commandd.output("Введите имя:");
            name = commandd.getNextInput().trim();

        } while (name.equals(""));

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

        String namel;
        do {
            commandd.output("Введите имя локации:");
            namel = commandd.getNextInput().trim();
        } while (namel.equals(""));

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

        String namel1;
        do {
            commandd.output("Введите имя локации:");
            namel1 = commandd.getNextInput().trim();
        } while (namel1.equals(""));

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
        route = new Route(id, name, new Coordinates(x, y), new Location(xl, yl, zl, namel), new Location(xl1, yl1, zl1, namel1), distance1);
        return route;
    }
}