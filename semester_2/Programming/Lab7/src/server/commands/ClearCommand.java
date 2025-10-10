package server.commands;

import common.collection.HumanBeing;
import server.serverIO.CollectionManager;
import server.serverIO.DataBaseManager;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ClearCommand extends ServerCommand {
    @Override
    public void execute(String value, HumanBeing element, String login, String password) {
        DataBaseManager.getCollectionLock().lock();
        try {
            int userId = DataBaseManager.getUserId(login);
            DataBaseManager.clearDataBase(userId);
            CollectionManager.clearCollection(userId);
        } finally {
            DataBaseManager.getCollectionLock().unlock();
        }
        addToResponse("Коллекция была успешно очищена!\n");
    }
}
