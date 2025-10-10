package server.commands;

import common.collection.HumanBeing;
import common.exceptions.WrongArgsException;
import server.serverIO.CollectionManager;
import server.serverIO.DataBaseManager;

import java.sql.SQLException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class UpdateServerCommand extends ServerCommand {
    /** UpdateCommand updates element by id to current */
    @Override
    public void execute(String value, HumanBeing element, String login, String password) throws WrongArgsException {
        try {
            if (!value.matches("\\d*"))
                throw new WrongArgsException("Аргументом должно служить число типа long\n");
            long id = Long.parseLong(value);
            DataBaseManager.getCollectionLock().lock();
            try {
                if (!CollectionManager.containsId(id))
                    throw new WrongArgsException("id " + id + " не найден!\n");

                int userId = DataBaseManager.getUserId(login);
                DataBaseManager.updateId(id, element, login);
                CollectionManager.updateId(element, userId);
            } finally {
                DataBaseManager.getCollectionLock().unlock();
            }
            addToResponse("Объект успешно обновлён!\n");
        } catch (NumberFormatException | SQLException e) {
            throw new WrongArgsException("Аргументом должно служить число типа long!\n");
        }
    }
}
