package Foundation;

import java.util.Date;
import java.util.LinkedList;

public class RouteCollection {
    public LinkedList<Route> route = new LinkedList<>();
    private Date date;

    public LinkedList<Route> getCollection() {
        return route;
    }


    public static String removeLast(String s) {
        return (s == null || s.length() == 0)
                ? null
                : (s.substring(0, s.length() - 1));
    }



    @Override
    public String toString() {
        return "Тип коллекции: " + this.getCollection().getClass() +
                "\nДата инициализации: " + date +
                "\nКоличество элементов: " + this.getCollection().size();
    }


}
