import java.io.IOException;
import java.io.Serializable;
import java.net.BindException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;

public class Client extends TextInput {
    public static void main(String[] args) {
        System.out.println("hi");
        class Pokid implements Serializable {
            Object a,b;
            public Pokid(Object a){
                this.a = a;
            }
            public Pokid(Object a, Object b){
                this.a = a;
                this.b = b;
            }
        }
        Scanner scanner = new Scanner(System.in);
        String command = "";
        try(DatagramChannel datagramChannel = DatagramChannel.open()) {
            SocketAddress socketAddress = new InetSocketAddress(InetAddress.getLocalHost(),3961);
            datagramChannel.connect(socketAddress);
            datagramChannel.bind(socketAddress);
            datagramChannel.configureBlocking(false);
            Send s = new Send();
            Receiver receiver = new Receiver();
            while (!command.equals("exit")) {
                command = scanner.nextLine();
                String[] finalUserCommand = command.trim().split(" ");
                switch (finalUserCommand[0]) {
                    case "":
                        break;
                    case "remove_lower":
                    case "add_if_max":
                    case "help":
                    case "info":
                    case "show":
                    case "clear":
                    case "save":
                    case "remove_head":
                    case "print_field_ascending_distance":
                    case "max_by_from":
                    case "min_by_distance":
                        Pokid pokid1 = new Pokid(command);
                        s.send(pokid1, socketAddress, datagramChannel);
                        receiver.receive(datagramChannel);
                        break;
                    case "update":
                    case "remove_by_id":
                    case "execute_script file_name":
                        Pokid pokid2 = new Pokid(finalUserCommand[0], finalUserCommand[1]);
                        s.send(pokid2, socketAddress, datagramChannel);
                        receiver.receive(datagramChannel);
                        break;
                    case "add":
                        TextInput element = new TextInput();
                        element.readElement();
                        s.send(element, socketAddress, datagramChannel);
                        receiver.receive(datagramChannel);
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