package proga;

public class AnimationAdd implements Runnable {
private RouteSpawner routeSpawner;
private VisualPanel visualPanel;
    public AnimationAdd(RouteSpawner routeSpawner, VisualPanel visualPanel){
        this.routeSpawner=routeSpawner;
        this.visualPanel=visualPanel;
    }
    @Override
    public void run() {
        try{
            final int finalcast=routeSpawner.getSize();
            while (routeSpawner.getSize()< finalcast * 1.1){
                routeSpawner.size++;
                visualPanel.repaint();
                Thread.sleep(5);
            }
            while (routeSpawner.getSize()>finalcast){
                routeSpawner.size--;
                visualPanel.repaint();
                Thread.sleep(5);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
