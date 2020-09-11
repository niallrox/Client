package proga;

import Foundation.Coordinates;
import Foundation.Location;
import Foundation.Route;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Manager {
    private Scanner scanner = new Scanner(System.in);
    public boolean access;
    private ArrayList<File> scriptRepeat = new ArrayList<>();
    private BufferedReader commandReader;
    public boolean scriptOn;
    private DatagramChannel datagramChannel;
    private byte [] buf = new byte[4096];


    public void work(DatagramChannel datagramChannel, SocketAddress socket, String command, String login, String password) throws IOException, ClassNotFoundException {
        if (command.equals("reg")) {
            Command request = new Command("reg", login, password);
            sendCommand(datagramChannel, socket, request);
            getAnswer(datagramChannel,buf);
        } else if (command.equals("sign")) {
            Command request = new Command("sign", login, password);
            sendCommand(datagramChannel, socket, request);
            getAnswer(datagramChannel,buf);
        }
        if (access) {
            while (true) {
                command = scanner.nextLine();
                choose(datagramChannel, socket, command, login, password);
            }
        }
    }


    public void choose(DatagramChannel datagramChannel, SocketAddress socket, String command, String login, String password) throws IOException, ClassNotFoundException {
        String[] finalUserCommand = command.trim().split(" ");
        if (finalUserCommand.length == 1) {
            switch (finalUserCommand[0]) {
                case "":
                    break;
                case "clear":
                case "help":
                case "info":
                case "show":
                case "print_field_ascending_distance":
                case "min_by_distance":
                case "max_by_from":
                case "remove_head":    {
                    Command request = new Command(finalUserCommand[0], login, password);
                    sendCommand(datagramChannel, socket, request);
                    getAnswer(datagramChannel,buf);
                }
                break;
                case "add_if_max":
                case "add":
                case "remove_lower": {
                    Command request = new Command(finalUserCommand[0], add(), login, password);
                    sendCommand(datagramChannel, socket, request);
                    getAnswer(datagramChannel,buf);
                }
                break;
                case "exit":
                    System.exit(0);
                default:
                    System.out.println("Неизвестная команда или команда введена без аргументов. Введите снова");
            }
        } else if (finalUserCommand.length == 2) {
            switch (finalUserCommand[0]) {
                case "remove_by_id":
                    try {
                        Integer.parseInt(finalUserCommand[1]);
                        Command request = new Command(finalUserCommand[0], finalUserCommand[1], login, password);
                        sendCommand(datagramChannel, socket, request);
                        getAnswer(datagramChannel,buf);
                    } catch (NumberFormatException e) {
                        System.out.println("Вы ввели строку или число выходит за пределы int. Введите снова");
                    }
                    break;
                case "update":
                    try {
                        Integer.parseInt(finalUserCommand[1]);
                        Command request = new Command(finalUserCommand[0], finalUserCommand[1], add(), login, password);
                        sendCommand(datagramChannel, socket, request);
                        getAnswer(datagramChannel,buf);
                    } catch (NumberFormatException e) {
                        System.out.println("Вы ввели строку или число выходит за пределы int. Введите снова");
                    }
                    break;
                case "execute_script":
                    scriptOn = true;
                    File file = new File(finalUserCommand[1]);
                    if (!file.exists())
                        System.out.println("Файла с таким именем не существует.");
                    else if (!file.canRead())
                        System.out.println("Файл защищён от чтения. Невозможно выполнить скрипт.");
                    else if (scriptRepeat.contains(file)) {
                        System.out.println("Могло произойти зацикливание при исполнении скрипта: "
                                + finalUserCommand[1] + "\nКоманда не будет выполнена. Переход к следующей команде");
                    } else {
                        scriptRepeat.add(file);
                        try {
                            commandReader = new BufferedReader(new FileReader(file));
                            String line = commandReader.readLine();
                            while (line != null) {
                                choose(datagramChannel, socket, line, login, password);
                                System.out.println();
                                line = commandReader.readLine();
                            }
                            System.out.println("Скрипт исполнен");
                        } catch (IOException ex) {
                            System.out.println("Невозможно считать скрипт");
                        }
                        scriptRepeat.remove(scriptRepeat.size() - 1);
                    }
                    scriptOn = false;
                    break;
                default:
                    System.out.println("Неизвестная команда или команда введена без аргументов. Введите снова");
            }
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


    public void getAnswer(DatagramChannel datagramChannel, byte [] codedPacket) throws IOException, ClassNotFoundException {
        String answer;
        ByteBuffer buffer = ByteBuffer.wrap(codedPacket);
        buffer.clear();
        System.out.println("ssss");
        try {
            SocketAddress address;
            do {
                address = datagramChannel.receive(buffer);
            } while (address == null);
            System.out.println("sssrwqrqrrqw");
            ByteArrayInputStream byteArrayStream = new ByteArrayInputStream(codedPacket);
        ObjectInputStream fromServer = new ObjectInputStream(byteArrayStream);
        answer = (String) fromServer.readObject();
        switch (answer) {
            case "exit":
                System.exit(0);
            case "Авторизация прошла успешно":
                access = true;
                System.out.println("Вы успешно авторизованы. Введите help чтобы узнать список доступных команд.");
                break;
            default:
                System.out.println(answer);
                break;
        }
    } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    long id = 0;
    TextInput commandd = new TextInput();
    Route route;
    Location from = new Location((float) 0.0,0.0,0,null);

    public String readString(String type){
        String smth;
        do {
            commandd.output("Введите " + type);
            smth = commandd.getNextInput().trim();

        } while (smth.equals(""));
        return smth;
    }
    public void nullLocation(){
        System.out.println("Хочешь локацию null?");
        String com = "";
        while(!(com.equals("Да")) || !(com.equals("Нет"))) {
            com = commandd.getNextInput();
            switch (com) {
                case "Да":
                    from = null;
                    return;
                case "Нет":
                    System.out.println("Ну тогда вводи");
                    return;
                default:
                    System.out.println("Введите 'Да' или 'Нет', пж");
                    break;
            }
        }
    }
    public Integer readInteger(String type,Integer odz){
        String introduced;
        Integer general = null;
        do {
            commandd.output("Введите " + type);
            introduced = commandd.getNextInput().trim();
            String a[] = type.split("");
            if(!(a[0].equals("Location") && a[1].equals("from"))) {
                try {
                    general = Integer.parseInt(introduced);
                    if (general < odz) {
                        general = null;
                        System.out.println("Поле должно быть больше " + odz);
                    }
                } catch (NumberFormatException n) {
                    System.out.println("Это не число");
                }
            }else{
                if (introduced.equals("")) {
                    general = null;
                    return general;
                }
            }
        } while (general == null);
        return general;
    }
    public Float readFloat (String type){
        String introduced;
        Float general = null;
        do {
            commandd.output("Введите " + type );
            introduced = commandd.getNextInput().trim();
            if (introduced == "") {
                general = null;
            } else {
                try {
                    general = Float.parseFloat(introduced);
                    float p = general;
                    int o = (int) p;
                    int i = introduced.length() - String.valueOf(o).length() - 1;
                    if (i > 6) {
                        System.out.println("Длина дробной части-" + i + ".Происходит округление, чтобы число не округлялось длина дробной части должна быть меньше 7");
                    }
                } catch (NumberFormatException n) {
                    System.out.println("Это не число");
                }
            }
        }
        while (general == null);
        return general;
    }
    public Double readDouble(String type){
        String introcuced;
        Double general = null;
        do {
            commandd.output("Введите "+type);
            introcuced = commandd.getNextInput().trim();
            if (introcuced == "") {
                general = null;
            } else {
                try {
                    general = Double.parseDouble(introcuced);
                    double p = general;
                    int o = (int) p;
                    int i = introcuced.length() - String.valueOf(o).length() - 1;
                    if (i > 15) {
                        System.out.println("Длина дробной части-" + i + ".Происходит округление, чтобы число не округлялось длина дробной части должна быть меньше 16");
                    }
                } catch (NumberFormatException n) {
                    System.out.println("Это не число");
                }
            }
        } while (general == null);
        return general;
    }
    public Long readDistance() {

        String distance;
        Long distance1 = null;
        do {
            commandd.output("Введите расстояние ");
            distance = commandd.getNextInput().trim();
            try {
                distance1 = Long.parseLong(distance);
                if (distance1 <= 1) {
                    distance1 = null;
                    System.out.println("Поле должно быть больше 1");
                }
            } catch (NumberFormatException n) {
                System.out.println("Это не число");
            }
        } while (distance1 == null);
        return distance1;
    }

    public Route add() {
        String name = readString("имя:");
        Integer coordiantesX = readInteger("Coordinates x:",-310);
        Integer coordinatesY = readInteger("Coordinates y:",-921);
        nullLocation();
        if(!(from == null)){
            Float locationFromX = readFloat("Location from x: ");
            Double locationFromY = readDouble("Location from y:");
            Integer locationFromZ = readInteger("Location from z:", -623);
            String locationFromName = readString("Location from имя локации:");
            from = new Location(locationFromX, locationFromY, locationFromZ, locationFromName);
        }
        Float locationToX = readFloat("Location to x:");
        Double locationToY = readDouble("Location to y:");
        Integer locationToZ = readInteger("Location to z:", -623);
        String locationToName = readString("Location to имя локации:");
        Long distance = readDistance();
        route = new Route(id, name, new Coordinates(coordiantesX, coordinatesY),from , new Location(locationToX, locationToY, locationToZ, locationToName), distance,"");
        return route;
    }
}