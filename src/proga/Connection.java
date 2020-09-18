package proga;

import java.io.*;
import java.net.*;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;

public class Connection {
    private Manager client = new Manager();
    private Scanner scanner = new Scanner(System.in);
    private String login;
    private String password;
    private byte [] buf =new  byte [4096];


    public void connection() throws ClassNotFoundException {
        while (true) {
            try {
                System.out.println("Введите порт");
                int port = Integer.parseInt(scanner.nextLine());
                System.out.println("Введите хост");
                String host = scanner.nextLine();
                System.out.println("Соединение... Ожидайте");
                try (DatagramChannel datagramChannel = DatagramChannel.open()) {
                    datagramChannel.configureBlocking(false);
                    SocketAddress socketAddress = new InetSocketAddress(host, port);
                    datagramChannel.connect(socketAddress);
                    System.out.println("Соединение установленно");
                    while (true) {
                        sign(datagramChannel, socketAddress);
                    }
                }
            } catch (SocketTimeoutException e) {
                System.out.println("Время подключения вышло. Хост указан неверно или сервер недоступен");
            } catch (NumberFormatException e) {
                System.out.println("Порт не число или выходит за пределы");
            } catch (ConnectException e) {
                System.out.println("Порт не найден или недоступен");
            } catch (UnknownHostException e) {
                System.out.println("Хост введен неверно");
            } catch (PortUnreachableException e){
                System.out.println("Подключится к этому порту нельзя");
            }
            catch (IllegalArgumentException e) {
                System.out.println("Порт должен принимать значения от 1 до 65535");
            } catch (IOException e) {
                client.access = false;
                client.scriptOn = false;
                System.out.println("Нет связи с сервером. Подключиться ещё раз введите (да) или (нет)?");
                String answer;
                while (!(answer = scanner.nextLine()).equals("да")) {
                    switch (answer) {
                        case "":
                            break;
                        case "нет":
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Введите корректное значение.");
                    }
                }
            }
        }
    }


    public void sign(DatagramChannel datagramChannel, SocketAddress socket) throws IOException, ClassNotFoundException {
        String command;
        while (true) {
            System.out.println("Вы зарегестрированы?. Введите (yes) или (no)");
            String regist = scanner.nextLine();
            if (regist.equals("no")) {
                System.out.println("Тогда зарегистрируемся.");
                checkLoginPassword();
                command = "reg";
                break;
            } else if (regist.equals("yes")) {
                System.out.println("Тогда авторизуемся.");
                checkLoginPassword();
                command = "sign";
                break;
            }
        }
        client.work(datagramChannel, socket, command, login, password);
    }


    private void checkLoginPassword() {
        while (true) {
            System.out.println("Введите логин");
            login = scanner.nextLine();
            if (login.equals("")) {
                System.out.println("Логин не может быть пустой строкой");
            } else {
                break;
            }
        }
        while (true) {
            System.out.println("Введите пароль");
            password = scanner.nextLine();
            if (password.equals("")) {
                System.out.println("Пароль не может быть пустой строкой");
            } else {
                break;
            }
        }
    }
}