package server.commands;


import common.collection.HumanBeing;
import common.exceptions.WrongArgsException;
import server.serverIO.CollectionManager;
import server.serverIO.DataBaseManager;

import java.sql.SQLException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AddServerCommand extends ServerCommand {
    @Override
    public void execute(String value, HumanBeing element, String login, String password) throws WrongArgsException {
        int userId = DataBaseManager.getUserId(login);
        if (userId == -1) throw new WrongArgsException("Заданы неверные данные!\n");
        element.setUserId(userId);
        try {
            DataBaseManager.getCollectionLock().lock();
            try {
                element.setId(DataBaseManager.insert(element, userId));
                CollectionManager.addElement(element);
            } finally {
                DataBaseManager.getCollectionLock().unlock();
            }
            addToResponse("Элемент был успешно добавлен в коллекцию!\n");
        } catch (SQLException e) {
            throw new WrongArgsException("Переданы неверные параметры объекта!\n");
        }
    }
}
