import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;

public class Client extends TextInput {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("\nВыход...")));
        byte[] sendbuf = new byte[5000];
        System.out.println("hi");
        Scanner scanner = new Scanner(System.in);
        String command = "";
        try (DatagramChannel datagramChannel = DatagramChannel.open()) {
            datagramChannel.configureBlocking(false);
            SocketAddress socketAddress = new InetSocketAddress(InetAddress.getLocalHost(), 7985);
            Send s = new Send(datagramChannel);
            Receiver receiver = new Receiver(datagramChannel);
            while (!command.equals("exit")) {
                command = scanner.nextLine();
                String[] finalUserCommand = command.trim().split(" ");
                switch (finalUserCommand[0]) {
                    case "":
                        break;
                    case "help":
                    case "info":
                    case "show":
                    case "clear":
                    case "save":
                    case "remove_head":
                    case "print_field_ascending_distance":
                    case "max_by_from":
                    case "min_by_distance":
                        s.sendobj(finalUserCommand[0], socketAddress);
                        System.out.println(receiver.receiveob(sendbuf));
                        break;
                    case "update":
                        s.sendobj(command, socketAddress);
                        RouteIKEA element1 = new RouteIKEA();
                        Object r1 = element1.readObject();
                        s.sendobj(r1, socketAddress);
                        System.out.println(receiver.receiveob(sendbuf));
                        break;
                    case "remove_by_id":
                    case "execute_script":
                        s.sendobj(command, socketAddress);
                        System.out.println(receiver.receiveob(sendbuf));
                        break;
                    case "add":
                    case "remove_lower":
                    case "add_if_max":
                        s.sendobj(finalUserCommand[0], socketAddress);
                        RouteIKEA element = new RouteIKEA();
                        Object r = element.readObject();
                        s.sendobj(r, socketAddress);
                        System.out.println(receiver.receiveob(sendbuf));
                        break;
                    case "exit":
                        System.out.println("Отключение клиента");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Неизвестная команда. Введите снова");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}