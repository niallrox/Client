package proga;

import Foundation.Route;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager {
    private Scanner scanner = new Scanner(System.in);
    public boolean access;
    private ArrayList<File> scriptRepeat = new ArrayList<>();
    private BufferedReader commandReader;
    public boolean scriptOn;
    private DatagramChannel datagramChannel;
    private byte[] buf = new byte[4096];
    private Route route;
    private JTextArea output;
    private String element;
    private String answer;


    public void work(DatagramChannel datagramChannel, SocketAddress socket, String command, String login, String password, JTextArea output) throws IOException, ClassNotFoundException {
        if (command.equals("reg")) {
            Command request = new Command("reg", login, password);
            sendCommand(datagramChannel, socket, request);
            getAnswer(datagramChannel, socket, buf, output, "reg");
        } else if (command.equals("sign")) {
            Command request = new Command("sign", login, password);
            sendCommand(datagramChannel, socket, request);
            getAnswer(datagramChannel, socket, buf, output, "sign");
            CommandFrame commandFrame = new CommandFrame(datagramChannel, socket, login, password);
            commandFrame.createFrame();
        }
    }


    public void choose(DatagramChannel datagramChannel, SocketAddress socket, String command, String login, String password, JFrame jFrame, JTextArea output, DefaultTableModel defaultTableModel) throws IOException, ClassNotFoundException {
        StringBuilder stringBuilder = new StringBuilder();
        switch (command) {
            case "":
                break;
            case "clear":
            case "help":
            case "info":
            case "show":
            case "print_field_ascending_distance":
            case "min_by_distance":
            case "max_by_from":
            case "remove_head": {
                Command request = new Command(command, login, password);
                sendCommand(datagramChannel, socket, request);
                getAnswer(datagramChannel, socket, buf, output, command);
            }
            break;
            case "add_if_max":
            case "add": {
                Command request = new Command(command, getRoute(), login, password);
                sendCommand(datagramChannel, socket, request);
                getAnswer(datagramChannel, socket, buf, output, command);
            }
            break;
            case "remove_by_id":
                try {
                    String id = JOptionPane.showInputDialog(jFrame, "Введите id");
                    if (id.equals("")) throw new NumberFormatException();
                    Command request = new Command(command, id, login, password);
                    sendCommand(datagramChannel, socket, request);
                    getAnswer(datagramChannel, socket, buf, output, command);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(jFrame, "Вы ввели строку или число выходит за пределы int. Введите снова");
                }
                break;
            case "update":
                try {
                    String id = JOptionPane.showInputDialog(jFrame, "Введите id");
                    jFrame.setVisible(false);
                    Command request = new Command(command, id, getRoute(), login, password);
                    sendCommand(datagramChannel, socket, request);
                    getAnswer(datagramChannel, socket, buf, output, command);
                } catch (NumberFormatException e) {
                    System.out.println("Вы ввели строку или число выходит за пределы int. Введите снова");
                }
                break;
            case "execute_script":
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    if (!file.exists())
                        System.out.println("Файла с таким именем не существует.");
                    else if (!file.canRead())
                        System.out.println("Файл защищён от чтения. Невозможно выполнить скрипт.");
                    else if (scriptRepeat.contains(file)) {
                        System.out.println("Могло произойти зацикливание при исполнении скрипта: "
                                + file.getName() + "\nКоманда не будет выполнена. Переход к следующей команде");
                    } else {
                        scriptRepeat.add(file);
                        try {
                            scriptOn=true;
                            commandReader = new BufferedReader(new FileReader(file));
                            String line = commandReader.readLine();
                            while (line != null) {
                                choose(datagramChannel, socket, line, login, password, jFrame, output, defaultTableModel);
                                System.out.println(line);
                                stringBuilder.append(getAnswerCommand()).append("\n\n");
                                line = commandReader.readLine();
                            }
                            System.out.println("Скрипт исполнен");
                            output.setText(stringBuilder.toString());
                        } catch (IOException ex) {
                            System.out.println("Невозможно считать скрипт");
                        }
                        scriptRepeat.remove(scriptRepeat.size() - 1);
                    }
                    scriptOn = false;
                }
                break;
            case "exit":
                System.exit(0);
            default:
                System.out.println("Неизвестная команда или команда введена без аргументов. Введите снова");
        }
    }


    public void sendCommand(DatagramChannel datagramChannel, SocketAddress socketAddress, Command answer) throws IOException {
        try (ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream();
             ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayStream)) {
            outputStream.writeObject(answer);
            outputStream.flush();
            byte[] sendbuf = byteArrayStream.toByteArray();
            ByteBuffer buffer = ByteBuffer.wrap(sendbuf);
            buffer.clear();
            datagramChannel.send(buffer, socketAddress);
        }
    }


    public void getAnswer(DatagramChannel datagramChannel, SocketAddress socketAddress, byte[] codedPacket, JTextArea output, String command) throws IOException, ClassNotFoundException {
        this.output = output;
        ByteBuffer buffer = ByteBuffer.wrap(codedPacket);
        buffer.clear();
        try {
            do {
                socketAddress = datagramChannel.receive(buffer);
            } while (socketAddress == null);
            ByteArrayInputStream byteArrayStream = new ByteArrayInputStream(codedPacket);
            ObjectInputStream fromServer = new ObjectInputStream(byteArrayStream);
            answer = (String) fromServer.readObject();
            switch (answer) {
                case "exit":
                    System.exit(0);
                case "Авторизация прошла успешно":
                    access = true;
                    output.setText("Вы успешно авторизованы. Введите help чтобы узнать список доступных команд.");
                    break;
                default:
                    if (!command.equals("show") && !scriptOn) {
                        output.setText(answer);
                    }
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public JTextArea getOutput() {
        return output;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Route getRoute() {
        return route;
    }


    public String getAnswerCommand() {
        return answer;
    }
}