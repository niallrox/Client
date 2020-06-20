import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;

public class Client extends TextInput {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("\nВыход...")));
        byte[] sendbuf = new byte[5000];
        System.out.println("hi, type port");
        Scanner scanner = new Scanner(System.in);
        int port = Integer.parseInt(scanner.nextLine());
        System.out.println("type host");
        String host = String.valueOf(scanner.nextLine());
        System.out.println("Добро пожаловать, напишите help");
        String command = "";
        try (DatagramChannel datagramChannel = DatagramChannel.open()) {
            datagramChannel.configureBlocking(false);
            SocketAddress socketAddress = new InetSocketAddress(host, port);
            datagramChannel.connect(socketAddress);
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
                        s.sendobj(command, socketAddress);
                        System.out.println(receiver.receiveob(sendbuf));
                    case "execute_script":
                        StringBuilder g= new StringBuilder();
                        try(FileReader reader = new FileReader(finalUserCommand[1]))
                        {
                            int c;
                            while((c=reader.read())!=-1){
                                g.append((char)c);
                            }
                        }
                        s.sendobj(command, socketAddress);
                        System.out.println(g);
                        s.sendobj(g.toString(), socketAddress);
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