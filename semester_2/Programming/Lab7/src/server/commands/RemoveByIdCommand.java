package server.commands;

import common.collection.HumanBeing;
import common.exceptions.WrongArgsException;
import server.serverIO.CollectionManager;
import server.serverIO.DataBaseManager;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class RemoveByIdCommand extends ServerCommand {
    /** RemoveByIdCommand removes element by id */
    @Override
    public void execute(String value, HumanBeing element, String login, String password) throws WrongArgsException {
        try {
            long id = Long.parseLong(value);
            DataBaseManager.getCollectionLock().lock();
            try {
                if (!CollectionManager.containsId(id))
                    throw new WrongArgsException("id " + id + " не найден!\n");

                int userId = DataBaseManager.getUserId(login);
                if (DataBaseManager.removeById(id, userId) == 0) addToResponse("Нет прав для удаления этого объекта!\n");
                else {
                    CollectionManager.removeElementById(id, userId);
                    addToResponse("Элемент был успешно удалён!\n");
                }
            } finally {
                DataBaseManager.getCollectionLock().unlock();
            }
        } catch (NumberFormatException e) {
            setErrorMessage("Аргументом должно служить число типа long\n");
        }
    }
}
