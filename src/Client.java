import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Scanner;

public class Client extends TextInput {
    public static void main(String[] args) {
        byte [] sendbuf= new byte[5000];
        System.out.println("hi");
        Scanner scanner = new Scanner(System.in);
        String command = "";
        try {
            SocketAddress socketAddress = new InetSocketAddress(InetAddress.getLocalHost(),7985);
            DatagramSocket datagramSocket = new DatagramSocket();
            Send s = new Send(datagramSocket, socketAddress);
            Receiver receiver = new Receiver(datagramSocket);
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
                        s.sendobj(finalUserCommand[0]);
                        System.out.println(receiver.receiveob(sendbuf));
                        break;
                    case "update":
                        s.sendobj(command);
                        TextInput element1 = new TextInput();
                        Object r1 = element1.readElement();
                        s.sendobj(r1);
                    case "remove_by_id":
                    case "execute_script":
                        s.sendobj(command);
                        System.out.println(receiver.receiveob(sendbuf));
                        break;
                    case "add":
                    case "remove_lower":
                    case "add_if_max":
                        s.sendobj(finalUserCommand[0]);
                        TextInput element = new TextInput();
                        Object r = element.readElement();
                        s.sendobj(r);
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
        } catch (IOException | ClassNotFoundException e ) {
            e.printStackTrace();

        }
    }
}