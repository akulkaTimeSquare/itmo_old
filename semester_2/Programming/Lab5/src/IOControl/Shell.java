package IOControl;

import Collection.HumanBeing;
import Exception.IncorrectCommandException;
import Exception.WrongArgsException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Stack;

import Exception.InvalidDataException;

public class Shell {
    /** errorTolerance is false = error in controller's output */
    private boolean errorTolerance;
    private Manager manager;

    public Shell(Manager manager) {
        this.manager = manager;
        this.errorTolerance = false;
    }

    /** Run: runs command in a cycle*/
    public boolean run() {
        String userInp;
        try {
            while ((userInp = manager.getLine()) != null) {
                String[] parsedInp = manager.parseCommand(userInp);
                try {
                    manager.runCommand(parsedInp);
                } catch (IncorrectCommandException e) {
                    if (!errorTolerance) manager.println("Команда не распознана!");
                } catch (WrongArgsException e) {
                    if (!errorTolerance) manager.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return true;
    }

    /** UpdateCollection: UpdateCollection by reading the file from program's arguments*/
    public void updateCollection(String filename) {
        try {
            Document document = manager.getDocumentByName(filename);
            Element rootElement = document.getDocumentElement();

            LocalDateTime initDate = LocalDateTime.parse(rootElement.getAttribute("initDate"),
                    DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            if (initDate.isBefore(LocalDateTime.parse("2023-01-01T00:00:00")) ||
                    initDate.isAfter(LocalDateTime.now())) {
                manager.println("Некорректное время создания коллекции," +
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
                            element.setArgument(arg,
                                    at != null ? at .getNodeValue() : null);
                        }
                        manager.addElement(element);
                    } catch (NumberFormatException | InvalidDataException ignored) {}
                }
            }
        } catch (FileNotFoundException e) {
            manager.println("Файл-аргумент не найден, продолжение работы без его использования...");
        } catch (IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public void setErrorTolerance(boolean errorTolerance) {
        this.errorTolerance = errorTolerance;
    }
}
