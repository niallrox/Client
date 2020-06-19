import Foundation.Coordinates;
import Foundation.Location;
import Foundation.Route;

public class RouteIKEA {
    long id = 0;
    TextInput commandd = new TextInput();
    Route route;
    Location from = new Location((float) 0.0,0.0,0,null);

    public String readString(String type){
        String smth;
        do {
            commandd.output("Введите " + type);
            smth = commandd.getNextInput().trim();

        } while (smth.equals(""));
        return smth;
    }
    public void nullLocation(){
        System.out.println("Хочешь локацию null?");
        String com = "";
        while(!(com.equals("Да")) || !(com.equals("Нет"))) {
            com = commandd.getNextInput();
            switch (com) {
                case "Да":
                    from = null;
                    return;
                case "Нет":
                    System.out.println("Ну тогда вводи");
                    return;
                default:
                    System.out.println("Введите 'Да' или 'Нет', пж");
                    break;
            }
        }
    }
    public Integer readInteger(String type,Integer odz){
        String introduced;
        Integer general = null;
        do {
            commandd.output("Введите " + type);
            introduced = commandd.getNextInput().trim();
            String a[] = type.split("");
            if(!(a[0].equals("Location") && a[1].equals("from"))) {
                try {
                    general = Integer.parseInt(introduced);
                    if (general < odz) {
                        general = null;
                        System.out.println("Поле должно быть больше " + odz);
                    }
                } catch (NumberFormatException n) {
                    System.out.println("Это не число");
                }
            }else{
                if (introduced.equals("")) {
                    general = null;
                    return general;
                }
            }
        } while (general == null);
        return general;
    }
    public Float readFloat (String type){
        String introduced;
        Float general = null;
        do {
            commandd.output("Введите " + type );
            introduced = commandd.getNextInput().trim();
            if (introduced == "") {
                general = null;
            } else {
                try {
                    general = Float.parseFloat(introduced);
                    float p = general;
                    int o = (int) p;
                    int i = introduced.length() - String.valueOf(o).length() - 1;
                    if (i > 6) {
                        System.out.println("Длина дробной части-" + i + ".Происходит округление, чтобы число не округлялось длина дробной части должна быть меньше 7");
                    }
                } catch (NumberFormatException n) {
                    System.out.println("Это не число");
                }
            }
        }
        while (general == null);
        return general;
    }
    public Double readDouble(String type){
        String introcuced;
        Double general = null;
        do {
            commandd.output("Введите "+type);
            introcuced = commandd.getNextInput().trim();
            if (introcuced == "") {
                general = null;
            } else {
                try {
                    general = Double.parseDouble(introcuced);
                    double p = general;
                    int o = (int) p;
                    int i = introcuced.length() - String.valueOf(o).length() - 1;
                    if (i > 15) {
                        System.out.println("Длина дробной части-" + i + ".Происходит округление, чтобы число не округлялось длина дробной части должна быть меньше 16");
                    }
                } catch (NumberFormatException n) {
                    System.out.println("Это не число");
                }
            }
        } while (general == null);
        return general;
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
        String name = readString("имя:");
        Integer coordiantesX = readInteger("Coordinates x:",-310);
        Integer coordinatesY = readInteger("Coordinates y:",-921);
        nullLocation();
        if(!(from == null)){
            Float locationFromX = readFloat("Location from x: ");
            Double locationFromY = readDouble("Location from y:");
            Integer locationFromZ = readInteger("Location from z:", -623);
            String locationFromName = readString("Location from имя локации:");
            from = new Location(locationFromX, locationFromY, locationFromZ, locationFromName);
        }
        Float locationToX = readFloat("Location to x:");
        Double locationToY = readDouble("Location to y:");
        Integer locationToZ = readInteger("Location to z:", -623);
        String locationToName = readString("Location to имя локации:");
        Long distance = readDistance();
        route = new Route(id, name, new Coordinates(coordiantesX, coordinatesY),from , new Location(locationToX, locationToY, locationToZ, locationToName), distance);
        return route;
    }
}