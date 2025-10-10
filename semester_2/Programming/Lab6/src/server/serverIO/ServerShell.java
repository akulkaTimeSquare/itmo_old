package server.serverIO;


import common.Request;
import common.Response;
import common.Serializer;
import common.collection.HumanBeing;
import common.exceptions.IncorrectCommandException;
import common.exceptions.InvalidDataException;
import common.exceptions.WrongArgsException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.System.exit;


public class ServerShell {
    private final ServerExecutor executor = new ServerExecutor();
    private final Serializer serializer = new Serializer();

    /** Run: runs command in a cycle*/
    public void run() {
        try (DatagramSocket ds = new DatagramSocket(7446);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Сервер запущен! Для сохранения коллекции введите save.");

            while (true) {
                if (bufferedReader.ready()) {
                    String[] input = bufferedReader.readLine().strip().split(" ");
                    if (input[0].equals("save")) {
                        if (input.length == 1) Saver.save("HumanBeing");
                        else if (input.length == 2) Saver.save(input[1]);
                        else System.out.println("Укажите только имя файла!");
                    } else if (input[0].equals("exit")) {
                        if (input.length == 1) exit(0);
                        else System.out.println("Введены лишние данные!");
                    } else {
                        System.out.println("Команда не распознана!");
                    }
                }

                byte[] receiveBytes = new byte[10000];
                DatagramPacket receivedDP = new DatagramPacket(receiveBytes, receiveBytes.length);
                ds.setSoTimeout(500);
                try {
                    ds.receive(receivedDP);
                } catch (SocketTimeoutException ignored) {ds.setSoTimeout(0);
                continue;}

                Request request = (Request) serializer.deserialize(receivedDP.getData());

                Response response;
                try {
                    response = new ServerExecutor().execute(request.getCommand(),
                            request.getValue(), request.getElement());
                } catch (IncorrectCommandException e) {
                    response = new Response();
                    response.setAnswer("Задана неверная команда!");
                } catch (WrongArgsException e) {
                    response = new Response();
                    response.setAnswer(e.getMessage());
                }
                byte[] byteResponse = serializer.serialize(response);
                InetAddress address = receivedDP.getAddress();
                int port = receivedDP.getPort();
                DatagramPacket sendDP = new DatagramPacket(byteResponse, byteResponse.length, address, port);
                ds.send(sendDP);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /** UpdateCollection: UpdateCollection by reading the file from program's arguments*/
    public void updateCollection(String filename) {
        try {
            Document document = Saver.getDocumentByName(filename);
            Element rootElement = document.getDocumentElement();

            LocalDateTime initDate = LocalDateTime.parse(rootElement.getAttribute("initDate"),
                    DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            if (initDate.isBefore(LocalDateTime.parse("2023-01-01T00:00:00")) ||
                    initDate.isAfter(LocalDateTime.now())) {
                System.out.println("Некорректное время создания коллекции," +
                        " продолжение работы программы без считывания файла");
            } else {
                NodeList nodeList = document.getElementsByTagName("element");
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);
                    NamedNodeMap attributes = node.getAttributes();
                    try {
                        LocalDateTime creationDate = LocalDateTime.parse(
                                attributes.getNamedItem("creationDate").getNodeValue(),
                                DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                        long id = Long.parseLong(attributes.getNamedItem("id").getNodeValue());
                        HumanBeing element = new HumanBeing(id, creationDate);
                        for (String arg: element.getArgs()) {
                            Node at = attributes.getNamedItem(arg);
                            element.setArgumentSwitch(arg,
                                    at != null ? at .getNodeValue() : null);
                        }
                        CollectionManager.addElement(element);
                    } catch (NumberFormatException | InvalidDataException ignored) {System.out.println(11);}
                }
            }
            System.out.println("Коллекция успешно обновлена!");
        } catch (FileNotFoundException e) {
            System.out.println("Файл-аргумент не найден, продолжение работы без его использования...");
        } catch (IOException | SAXException | ParserConfigurationException e) {
            System.out.println(e.getMessage());
        }
    }
}
