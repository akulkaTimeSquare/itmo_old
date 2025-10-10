package server.commands;

import common.collection.HumanBeing;
import common.exceptions.WrongArgsException;
import server.serverIO.CollectionManager;

public class RemoveByIdCommand extends ServerCommand {
    /** RemoveByIdCommand removes element by id */
    @Override
    public void execute(String value, HumanBeing element) throws WrongArgsException {
        try {
            long id = Long.parseLong(value);
            if (!CollectionManager.containsId(id))
                throw new WrongArgsException("id " + id + " не найден!");

            CollectionManager.removeElementById(id);
            addToResponse("Элемент был успешно удалён!");
        } catch (NumberFormatException e) {
            setErrorMessage("Аргументом должно служить число типа long");
        }
    }
}
