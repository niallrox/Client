package proga;

import java.awt.*;
import java.util.HashMap;

public class AnimationDelete implements Runnable {
    private RouteSpawner routeSpawner;
    private VisualPanel visualPanel;
    private HashMap<String, RouteSpawner> elementsClient;
    private String arg;

    public AnimationDelete(RouteSpawner routeSpawner, VisualPanel visualPanel, HashMap<String, RouteSpawner> elementsClient, String arg){
        this.routeSpawner=routeSpawner;
        this.visualPanel=visualPanel;
        this.elementsClient=elementsClient;
        this.arg=arg;
    }
    @Override
    public void run() {
        try {
            int finalcount=routeSpawner.getSize()/2;
         while (routeSpawner.getSize()>finalcount){
             routeSpawner.size--;
             routeSpawner.xDefault--;
             routeSpawner.yDefault--;
             visualPanel.repaint();
             Thread.sleep(5);
             } visualPanel.repaint();
         } catch (InterruptedException e) {
            e.printStackTrace();
        }
         elementsClient.remove(arg);
    }
}
