package server.serverIO;

import common.collection.HumanBeing;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Saver {
    public static Document getDocument() throws ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = docFactory.newDocumentBuilder();
        return builder.newDocument();
    }
    public static Document getDocumentByName(String filename) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = docFactory.newDocumentBuilder();
        return (filename != null) ?
                builder.parse(filename + (filename.matches(".*\\.xml") ? "" : ".xml")) :
                builder.newDocument();
    }

    public static void writeXml(Document doc, BufferedWriter output) throws TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);
    }

    public static void save(String filename)  {
        if (filename.length() >= 30) {
            System.out.println("Длина имени файла-аргумента не должна превышать 30 символов!");
        } else {
            try {
                Document document = getDocument();
                Element rootElement = document.createElement("HumanBeingCollection");
                rootElement.setAttribute("initDate", CollectionManager.getInitDate().toString());
                document.appendChild(rootElement);
                for (HumanBeing element : CollectionManager.getCollection()) {
                    Element el = document.createElement("element");
                    el.setAttribute("id", element.getId().toString());
                    el.setAttribute("name", element.getName());
                    el.setAttribute("coordinates", element.getCoordinates().toString());
                    el.setAttribute("creationDate", element.getCreationDate().toString());
                    el.setAttribute("realHero", element.isRealHero() ? "yes" : "no");
                    el.setAttribute("hasToothpick", element.isHasToothpick() ? "yes" : "no");
                    el.setAttribute("impactSpeed", Integer.toString(element.getImpactSpeed()));
                    el.setAttribute("minutesOfWaiting", Integer.toString(element.getMinutesOfWaiting()));
                    el.setAttribute("mood", element.getMood().toString());
                    el.setAttribute("car", element.getCar().toString());
                    el.setAttribute("soundtrackName", element.getSoundtrackName());
                    rootElement.appendChild(el);
                }
                writeXml(document, new BufferedWriter(new FileWriter(filename +
                        (filename.matches(".*\\.xml") ? "" : ".xml"))));
                System.out.println("Коллекция успешно сохранена в файл!");
            } catch (IOException | ParserConfigurationException | TransformerException e) {
                System.out.println(e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("Выход...");
            }
        }
    }
}
