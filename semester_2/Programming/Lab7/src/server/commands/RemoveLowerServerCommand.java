package server.commands;

import common.collection.HumanBeing;
import server.serverIO.CollectionManager;
import server.serverIO.DataBaseManager;

import java.util.HashSet;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class RemoveLowerServerCommand extends ServerCommand {
    /** RemoveGreater removes all elements lower than current */
    @Override
    public void execute(String value, HumanBeing element, String login, String password) {
        DataBaseManager.getCollectionLock().lock();
        try {
            int userId = DataBaseManager.getUserId(login);
            CollectionManager.removeGreater(element, userId, false);
        } finally {
            DataBaseManager.getCollectionLock().unlock();
        }
        addToResponse("Все элементы, меньшие заданного, были удалены!\n");
    }
}
