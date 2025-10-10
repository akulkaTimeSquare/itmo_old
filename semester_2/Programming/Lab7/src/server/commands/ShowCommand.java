package server.commands;

import common.collection.HumanBeing;
import server.serverIO.CollectionManager;
import server.serverIO.DataBaseManager;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ShowCommand extends ServerCommand {
    /** ShowCommand show the string view of a collection */
    @Override
    public void execute(String value, HumanBeing element, String login, String password) {
        DataBaseManager.getCollectionLock().lock();
        try {
            if (CollectionManager.isEmptyCollection()) addToResponse("Коллекция пуста!");
            else {
                ArrayList<HumanBeing> collection = CollectionManager.getSortedCollection();
                for (HumanBeing el : collection)
                    addToResponse("\t" + el + ((collection.indexOf(el) == (collection.size() - 1)) ? "" : "\n"));
            }
        } finally {
            DataBaseManager.getCollectionLock().unlock();
        }
    }
}
