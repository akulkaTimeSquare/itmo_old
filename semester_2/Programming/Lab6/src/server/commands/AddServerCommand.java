package server.commands;


import common.collection.HumanBeing;
import server.serverIO.CollectionManager;

public class AddServerCommand extends ServerCommand {
    @Override
    public void execute(String value, HumanBeing element) {
        CollectionManager.getCollection().add(element);
        addToResponse("Элемент был успешно добавлен в коллекцию!");
    }
}
