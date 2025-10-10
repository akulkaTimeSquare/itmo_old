package Command;

import Collection.HumanBeing;
import Exception.WrongArgsException;
import IOControl.Manager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveCommand implements Command {
    /** SaveCommand saves collection in a xml file*/
    @Override
    public void execute(Manager manager, String[] args) throws WrongArgsException {
        if (args[1].length() >= 30)
            throw new WrongArgsException("Длина имени файла не должна превышать 30 символов");
        String filename = args[1];
        try {
            Document document = manager.getDocument();
            Element rootElement = document.createElement("HumanBeingCollection");
            rootElement.setAttribute("initDate", manager.getInitDate().toString());
            document.appendChild(rootElement);
            for (HumanBeing element: manager.getCollection()) {
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
            manager.writeXml(document, new BufferedWriter(new FileWriter(filename +
                    (filename.matches(".*\\.xml") ? "" : ".xml"))));
            manager.println("Коллекция успешно сохранена в файл!");
        } catch (IOException e) {
            manager.println("IOException found");
        } catch (NullPointerException e) {
            manager.println("Выход...");
        }
    }

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getDescr() {
        return "сохраняет коллекцию в xml файл";
    }

    @Override
    public String[] getArgs() {
        return new String[]{"[filename]"};
    }
}
