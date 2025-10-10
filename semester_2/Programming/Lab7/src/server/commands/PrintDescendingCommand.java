package server.commands;

import common.collection.HumanBeing;
import server.serverIO.CollectionManager;
import server.serverIO.DataBaseManager;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintDescendingCommand extends ServerCommand {
    /** PrintDescending shows elements lower than current */
    @Override
    public void execute(String value, HumanBeing element, String login, String password){
        DataBaseManager.getCollectionLock().lock();
        try {
            if (CollectionManager.isEmptyCollection()) addToResponse("Коллекция пуста!\n");
            else {
                CollectionManager.getReversedCollection().stream().
                        forEachOrdered(el -> addToResponse("\t" + el.simpleView() + "\n"));
            }
        } finally {
            DataBaseManager.getCollectionLock().unlock();
        }
    }
}
