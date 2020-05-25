import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.util.Scanner;

public class Client  {
    Scanner scanner = new Scanner(System.in);
    SocketAddress socketAddress = new InetSocketAddress(InetAddress.getLocalHost(),7658);
    DatagramSocket socket = new DatagramSocket();
    byte [] b = new byte[500000000];

    public Client() throws SocketException, UnknownHostException {
    }


    public void connection() throws IOException, InterruptedException {
        System.out.println("Подключение...");
        Thread.sleep(1000);
        System.out.println("Добро пожаловать, вы подключились к серверу");
        String S = "Салам";
        socket.send(new DatagramPacket(S.getBytes(),S.length(),InetAddress.getLocalHost(),7658));
    }
    public void interactiveMode() throws IOException, ClassNotFoundException {
        String command = "";
        while (!command.equals("exit")) {
            command = scanner.nextLine();
            String[] finalUserCommand = command.trim().split(" ");
            try {
                switch (finalUserCommand[0]) {
                    case "":
                        break;
                    case "help":
                    case "info":
                    case "show":
                    case "clear":
                    case "remove_head":
                    case "print_field_ascending_distance":
                    case "max_by_from":
                    case "min_by_distance":
                        socket.send(new DatagramPacket(command.getBytes(),command.length(),socketAddress));
                        System.out.println("sss");
                        DatagramPacket dt = new DatagramPacket(b,b.length);
                        socket.receive(dt);
                        ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(dt.getData());
                        ObjectInputStream fromServer=new ObjectInputStream(byteArrayInputStream);
                        Object object=fromServer.readObject();
                        System.out.println(object.toString());
                        fromServer.close();
                        break;
                    case "add":
//                        toServer.writeObject(command);
//                        add();
//                        System.out.println((String) fromServer.readObject());
                        break;
//                case "update":
//                    implement.update(finalUserCommand[1]);
//                    break;
                    case "remove_by_id":
                    case "remove_greater":
                    case "remove_any_by_students_count":
                        try {
                            Integer.parseInt(finalUserCommand[1]);
                            socket.send(new DatagramPacket(command.getBytes(),command.length()));
                            dt = new DatagramPacket(b,b.length);
                            socket.receive(dt);
                            byteArrayInputStream=new ByteArrayInputStream(dt.getData());
                            fromServer=new ObjectInputStream(byteArrayInputStream);
                            object=fromServer.readObject();
                            System.out.println(object.toString());
                        } catch (NumberFormatException e) {
                            System.out.println("Вы ввели строку или число выходит за пределы int. Введите снова");
                        }
                        break;
//                case "execute_script":
//                    implement.execute_script(finalUserCommand[1]);
//                    break;
//                case "add_if_max":
//                    implement.add_if_max();
//                    break;
//                case "add_if_min":
//                    implement.add_if_min();
//                    break;
                    case "exit":
                        System.out.println("Отключение клиента");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Неизвестная команда. Введите снова");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Отсутствует аргумент");
            }
        }
    }
}
