package proga;

import java.util.HashMap;

public class AnimationUpdate implements Runnable {
    private RouteSpawner adding;
    private RouteSpawner deleting;
    private VisualPanel visualPanel;
    private HashMap<String, RouteSpawner> elementsClient;
    private String arg;
    public AnimationUpdate(RouteSpawner adding, RouteSpawner deleting, VisualPanel visualPanel, HashMap<String, RouteSpawner> elementsClient, String arg){
       this.adding=adding;
       this.deleting=deleting;
       this.visualPanel=visualPanel;
       this.elementsClient=elementsClient;
       this.arg=arg;
    }
    @Override
    public void run() {
        try {
            final int finalcount=deleting.getSize()/2;
            while (deleting.getSize()>finalcount){
                deleting.size--;
                deleting.xDefault--;
                deleting.yDefault--;
                visualPanel.repaint();
                Thread.sleep(5);
            } visualPanel.repaint(); elementsClient.remove(arg);

            final int finalcast=adding.getSize();
            while (adding.getSize()< finalcast * 1.1){
                adding.size++;
                visualPanel.repaint();
                Thread.sleep(5);
            }
            while (adding.getSize()>finalcast){
                adding.size--;
                visualPanel.repaint();
                Thread.sleep(5);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
