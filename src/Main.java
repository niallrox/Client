import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        System.out.println("хай");
        Client client = new Client();
        client.connection();
        client.interactiveMode();

    }
}
