package server.commands;

import common.collection.HumanBeing;
import common.exceptions.WrongArgsException;
import server.serverIO.CollectionManager;

public class UpdateServerCommand extends ServerCommand {
    /** UpdateCommand updates element by id to current */
    @Override
    public void execute(String value, HumanBeing element) throws WrongArgsException {
        try {
            if (!value.matches("\\d*"))
                throw new WrongArgsException("Аргументом должно служить число типа long");
            long id = Long.parseLong(value);
            if (!CollectionManager.containsId(id))
                throw new WrongArgsException("id " + id + " не найден!");

            CollectionManager.updateId(element);
            addToResponse("Объект успешно обновлён!");
        } catch (NumberFormatException e) {
            throw new WrongArgsException("Аргументом должно служить число типа long!");
        }
    }
}
