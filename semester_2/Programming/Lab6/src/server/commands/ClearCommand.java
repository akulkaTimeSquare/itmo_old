package server.commands;

import common.collection.HumanBeing;
import server.serverIO.CollectionManager;

public class ClearCommand extends ServerCommand {
    @Override
    public void execute(String value, HumanBeing element) {
        CollectionManager.clearCollection();
        addToResponse("Коллекция была успешно очищена!");
    }
}
